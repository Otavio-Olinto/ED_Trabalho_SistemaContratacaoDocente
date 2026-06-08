package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Processo;
import otavioolinto.Lista;
import view.Tela;

public class ProcessoController implements ActionListener {

	// Lista de Processos
	
	private Lista<Processo> listaProcessos = new Lista<>(Processo.class);
	
	// Campos da Tela
	
	private JTextField tfProcessoCodigo;
	private JComboBox<String> cboxProcessoStatus;
	private JComboBox<String> cboxDisciplina;
	private JTextArea taProcesso;
	private Tela tela;

	public ProcessoController(JTextField tfProcessoCodigo, JComboBox<String> cboxProcessoStatus,
		JComboBox<String> cboxDisciplina, JTextArea taProcesso, Tela tela) {
			this.tfProcessoCodigo = tfProcessoCodigo;
			this.cboxProcessoStatus = cboxProcessoStatus;
			this.cboxDisciplina = cboxDisciplina;
			this.taProcesso = taProcesso;
			this.tela = tela;

		// Carrega os dados
			
		try {
			carregarProcessos();
			//preencherComboDisciplinas(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Salvar")) {
			try {
				salvar();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Buscar")) {
			buscar();
		}
	}

	private void carregarProcessos() throws Exception {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "Processos.csv");
		
		// Verifica se o arquivo existe e faz a leitura
		
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);

			String linha = buffer.readLine();

			while (linha != null) {
				
				if(linha.equals("")) {
					break;
				}
				String[] dados = linha.split(";");

				Processo processo = new Processo();
					
				processo.setCodigoProcesso(Integer.parseInt(dados[0]));
				processo.setNomeDisciplina(dados[1]);
				processo.setStatus(dados[2]);
		

				listaProcessos.addLast(processo);
				linha = buffer.readLine();
			}
			fis.close();
			isr.close();
			buffer.close();
		}
	}

	private void buscar() {
		taProcesso.setText("");

		try {
			if (!tfProcessoCodigo.getText().isBlank()) {
				int codigo = Integer.parseInt(tfProcessoCodigo.getText());
				
				for (int i = 0; i < listaProcessos.size(); i++) {
					Processo processo = listaProcessos.get(i);

					if (processo.getCodigoProcesso() == codigo) {
						taProcesso.setText("Processo encontrado!");
						
						// Preenche os campos da tela com os dados atuais para permitir alteração
						
						cboxProcessoStatus.setSelectedItem(processo.getStatus());
						cboxDisciplina.setSelectedItem(processo.getNomeDisciplina());
						return;
					}
				}
				taProcesso.setText("Processo não encontrado.");
			} else {
				taProcesso.setText("Informe o código do processo para buscar.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void salvar() throws Exception {
		
		//Verifica se o campo Disciplina foi preenchido corretamente
		
		if (cboxDisciplina.getSelectedIndex() == -1 || 
				cboxDisciplina.getSelectedItem() == null ||
				cboxDisciplina.getSelectedItem().toString().contains("Nenhuma")) {
				
				taProcesso.setText("Preencha todos os campos corretamente antes de salvar ou alterar.");
				return;
			}
		
		try {
			
			// Verifica se os demais campos também foram preenchidos corretamente
			
			int codigo = Integer.parseInt(tfProcessoCodigo.getText());
			String status = cboxProcessoStatus.getSelectedItem().toString();
			String nomeDisciplina = cboxDisciplina.getSelectedItem().toString();
			
			Processo p = new Processo(codigo, nomeDisciplina, status);
			
			// Procura se esse processo já existe na lista encadeada
			
			int posicaoNaLista = -1;
			for (int i = 0; i < listaProcessos.size(); i++) {
				if (listaProcessos.get(i).equals(p)) {
					posicaoNaLista = i;
					break;
				}
			}

			// Verifica se a posição da lista é válida para saber se o Código do Processo existe
			
			Processo processoAtual;
			
			if (posicaoNaLista != -1) {
				
				// Captura a referência do objeto existente na lista encadeada e muda seus dados
				
				Processo processoExistente = listaProcessos.get(posicaoNaLista);
				processoExistente.setStatus(status);
				processoExistente.setNomeDisciplina(nomeDisciplina);
				taProcesso.setText("Processo ATUALIZADO com sucesso:\n" + processoExistente.toString());
				
				processoAtual = processoExistente;
				
				if(status.equals("Finalizado")) {
					
					listaProcessos.remove(posicaoNaLista);
				}

				arqAtualiza();
				
			} else {
				
				// Se não existia, insere no fim da lista e anexa no arquivo
				
				Processo novoProcesso = new Processo(codigo, nomeDisciplina, status);
				listaProcessos.addLast(novoProcesso);
				cadastrarProcesso(novoProcesso);
				taProcesso.setText("Processo CADASTRADO com sucesso:\n" + novoProcesso.toString());
				processoAtual = novoProcesso;
			}

			// Limpa a tela após concluir qualquer uma das operações
			
			tfProcessoCodigo.setText("");
			cboxProcessoStatus.setSelectedIndex(-1);
			cboxDisciplina.setSelectedIndex(-1);
			
			String processo = String.valueOf(processoAtual.getCodigoProcesso())+" - "+processoAtual.getNomeDisciplina();
			String nome = processoAtual.getNomeDisciplina();
			
			if(processoAtual.getStatus().equals("Aberto")) {
				
				tela.adicionarInscriçãoProcesso(processo);
				tela.adicionarInscriçãoConsultaDisciplina(nome);
				
			}else {
				
				tela.removerInscriçãoProcesso(processo);
				tela.removerInscriçãoConsultaDisciplina(nome);
			}
			
			
		} catch (Exception e) {
			taProcesso.setText("Preencha todos os campos corretamente antes de salvar ou alterar.");
		}
	}
	

	// Sobrescreve o arquivo CSV com a lista encadeada atualizada da memória
	
	private void arqAtualiza() throws Exception {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "Processos.csv");
		
		FileWriter fw = new FileWriter(arq, false); 
		PrintWriter pw = new PrintWriter(fw);

		for (int i = 0; i < listaProcessos.size(); i++) {
			Processo processo = listaProcessos.get(i);
			pw.println(processo.toString());
		}

		pw.flush();
		pw.close();
		fw.close();
	}
	
	private void cadastrarProcesso(Processo csvProcesso) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File dir = new File(path);

		if (!dir.exists()) {
			dir.mkdir();
		}
		
		File arq = new File(path, "Processos.csv");
		boolean existe = false;

		if (arq.exists()) {

			existe = true;

		}

		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);

		pw.write(csvProcesso + "\r\n");
		pw.flush();
		pw.close();
		fw.close();
	}

	// Retorna um Array de String com os códigos dos Processos Abertos e suas Disciplinas
	
	public String[] buscarProcessosAbertos() {
		int quantidadeAbertos = 0;
		try {
			for (int i = 0; i < listaProcessos.size(); i++) {
				if (listaProcessos.get(i).getStatus().equalsIgnoreCase("Aberto")) {
					quantidadeAbertos++;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		String[] vetorProcessos = new String[quantidadeAbertos];
		int posicaoVetor = 0;

		try {
			for (int i = 0; i < listaProcessos.size(); i++) {
				Processo p = listaProcessos.get(i);
				
				if (p.getStatus().equalsIgnoreCase("Aberto")) {
					vetorProcessos[posicaoVetor] = String.valueOf(p.getCodigoProcesso())+ " - "+p.getNomeDisciplina();
					posicaoVetor++; 
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return vetorProcessos;    
	}
	
	public String[] buscarDisciplinasAbertos() {
		int quantidadeAbertos = 0;
		try {
			for (int i = 0; i < listaProcessos.size(); i++) {
				if (listaProcessos.get(i).getStatus().equalsIgnoreCase("Aberto")) {
					quantidadeAbertos++;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		String[] vetorProcessos = new String[quantidadeAbertos];
		int posicaoVetor = 0;

		try {
			for (int i = 0; i < listaProcessos.size(); i++) {
				Processo p = listaProcessos.get(i);
				
				if (p.getStatus().equalsIgnoreCase("Aberto")) {
					vetorProcessos[posicaoVetor] = p.getNomeDisciplina();
					posicaoVetor++; 
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return vetorProcessos;    
	}
	
}