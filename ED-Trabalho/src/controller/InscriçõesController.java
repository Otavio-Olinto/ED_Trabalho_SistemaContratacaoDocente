package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import otavioolinto.Lista;
import model.Inscrições;

public class InscriçõesController implements ActionListener {
	
	// Lista de inscritos
	private Lista<String> listaInscritos = new Lista<>(String.class);
	
	private JComboBox<String> cboxInscriçõesCpf;
	private JComboBox<String> cboxInscriçõesCodigoProcesso;
	private JComboBox<String> cboxInscriçõesCodigoDisciplina;
	private JTextArea taInscrição;
	private Inscrições inscrever = new Inscrições();
	
	public InscriçõesController(JComboBox<String> cboxInscriçõesCpf, JComboBox<String> cboxInscriçõesCodigoProcesso, 
			JComboBox<String> cboxInscriçõesCodigoDisciplina, JTextArea taInscrição) {
		
		this.cboxInscriçõesCpf = cboxInscriçõesCpf;
		this.cboxInscriçõesCodigoProcesso = cboxInscriçõesCodigoProcesso;
		this.cboxInscriçõesCodigoDisciplina = cboxInscriçõesCodigoDisciplina;
		this.taInscrição = taInscrição;
		
		// Carrega os dados
		try {
			carregarInscrições();
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
		if(cmd.equals("Remover")) {
			remover();
		}
	}
	
	private void carregarInscrições() throws Exception {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File arq = new File(path, "Inscrições.csv");

		// Verifica se o arquivo existe e faz a leitura
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);

			String linha = buffer.readLine();

			while (linha != null) {
				
				listaInscritos.addLast(linha);
				linha = buffer.readLine();

			}
			fis.close();
			isr.close();
			buffer.close();
		}
	}
	
	private void buscar() {
		
		
		taInscrição.setText("Pamonha");

		int tamanho = listaInscritos.size();
		

		try {

			// Busca por codigo

			if (cboxInscriçõesCpf.getSelectedItem()!=null) {
				
				String  cpf = (String) cboxInscriçõesCpf.getSelectedItem();
				
				taInscrição.setText(cpf);
				
				for (int i = 0; i < tamanho ; i++) {

					String inscrito = listaInscritos.get(i);
					
					if (inscrito.contains(cpf)) {

						taInscrição.setText(inscrito);

						return;
					}
				}

				taInscrição.setText("Inscrição não encontrada.");
			}

			// Busca por nome

			else if (cboxInscriçõesCodigoProcesso.getSelectedItem()!=null) {

				String codigoProcesso = (String) cboxInscriçõesCodigoProcesso.getSelectedItem();
				codigoProcesso = ";"+codigoProcesso;

				StringBuilder resultado = new StringBuilder();

				for (int i = 0; i < tamanho; i++) {

					String inscrito = listaInscritos.get(i);

					if (inscrito.contains(codigoProcesso)) {

						resultado.append(inscrito).append("\n");

					}

				}

				if (resultado.length() > 0) {

					taInscrição.setText(resultado.toString());

				} else {

					taInscrição.setText("Nenhuma Inscrição encontrada.");

				}
			}

			// Busca por nome

			else if (cboxInscriçõesCodigoDisciplina.getSelectedItem()!=null) {

				String codigoDisciplina = (String) cboxInscriçõesCodigoDisciplina.getSelectedItem();
				codigoDisciplina = ";"+codigoDisciplina+";";

				StringBuilder resultado = new StringBuilder();

				for (int i = 0; i < tamanho; i++) {

					String inscrito = listaInscritos.get(i);

					if (inscrito.contains(codigoDisciplina)) {

						resultado.append(inscrito).append("\n");

					}

				}

				if (resultado.length() > 0) {

					taInscrição.setText(resultado.toString());

				} else {

					taInscrição.setText("Nenhuma Inscrição encontrada.");

				}
				
			} else {
				
				taInscrição.setText("Selecione um CPF, Código de concurso ou disciplina para realizar a Busca");
			}


		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
	private void salvar() throws Exception {
		
		taInscrição.setText("");

		String cpf = (String) cboxInscriçõesCpf.getSelectedItem();
		
		String cboxCodigoProcesso = (String) cboxInscriçõesCodigoProcesso.getSelectedItem();
		String[] vetCodigoProcesso = cboxCodigoProcesso.split("-");
		String codigoProcesso = vetCodigoProcesso[0].trim();
		
		String cboxCodigoDisciplina = (String) cboxInscriçõesCodigoDisciplina.getSelectedItem();
		String[] vetCodigoDisciplina = cboxCodigoDisciplina.split("-");
		String codigoDisciplina = vetCodigoDisciplina[0].trim();
		
		String inscrito = inscrever.toString(cpf, codigoDisciplina, codigoProcesso);
		
		int posição = -1;
		
		try {
			
			int tamanho = listaInscritos.size();
			
			for(int i=0; i<tamanho; i++) {
				
				String lista = listaInscritos.get(i);
				
				if(inscrito.equals(lista)) posição = i;
			}
			
			if(posição==-1) listaInscritos.addLast(inscrito);

		} catch (Exception e) {

			e.printStackTrace();
		}

		if(posição==-1) {
			
			cadastraInscrito(inscrito);
			taInscrição.setText(inscrito + "\n\nInscrito!");
			
		}else {
			
			taInscrição.setText("Professor já Inscrito neste processo");
		}

		cboxInscriçõesCodigoDisciplina.setSelectedIndex(-1);

		cboxInscriçõesCodigoProcesso.setSelectedIndex(-1);

		cboxInscriçõesCpf.setSelectedIndex(-1);
		
	}

	private void cadastraInscrito(String csvInscrito) throws IOException {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File dir = new File(path);

		if (!dir.exists()) {

			dir.mkdir();

		}
		File arq = new File(path, "Inscrições.csv");

		boolean existe = false;

		if (arq.exists()) {

			existe = true;

		}
		FileWriter fw = new FileWriter(arq, existe);

		PrintWriter pw = new PrintWriter(fw);

		pw.write(csvInscrito + "\r\n");

		pw.flush();

		pw.close();

		fw.close();

	}
	
	private void remover() {
		
		taInscrição.setText("");

		try {

			String cpf = (String) cboxInscriçõesCpf.getSelectedItem();
			String codigoProcesso = (String) cboxInscriçõesCodigoProcesso.getSelectedItem();
			String codigoDisciplina = (String) cboxInscriçõesCodigoDisciplina.getSelectedItem();
			
			String remover = inscrever.toString(cpf,codigoDisciplina,codigoProcesso);

			int posicao = -1;
			int tamanho = listaInscritos.size();

			for (int i = 0; i < tamanho; i++) {

				String inscrito = listaInscritos.get(i);

				if (inscrito.equalsIgnoreCase(remover)) {
					
					posicao = i;

					break;
				}

			}

			if (posicao == -1) {

				taInscrição.setText("Inscrição não encontrada.");

				return;

			}

			listaInscritos.remove(posicao);

			arqatualiza();

			taInscrição.setText("Curso removido com sucesso.");

			cboxInscriçõesCodigoDisciplina.setSelectedIndex(-1);

			cboxInscriçõesCodigoProcesso.setSelectedIndex(-1);

			cboxInscriçõesCpf.setSelectedIndex(-1);
			
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	private void arqatualiza() throws Exception {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File arq = new File(path, "Inscrições.csv");

		FileWriter fw = new FileWriter(arq, false);

		PrintWriter pw = new PrintWriter(fw);
		
		int tamanho = listaInscritos.size();
		
		for (int i = 0; i < tamanho; i++) {

			String inscrito = listaInscritos.get(i);

			pw.println(inscrito);
		}

		pw.flush();

		pw.close();

		fw.close();

	}

}
