package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import model.Processo;
import model.Disciplina;
import model.Professor;
import otavioolinto.Lista;

public class ConsultaController implements ActionListener {
	
	private JComboBox<String> cboxConsultaInscritos;
	private JButton btnConsultaBuscarInscritos;
	private JButton btnConsultaBuscarProcessos;
	private JTextArea taConsulta;
	
	Lista<Disciplina> listaDisciplinas = new Lista<>(Disciplina.class);
	Lista<String> listaInscritos = new Lista<>(String.class);
	Lista<Processo> listaProcessos = new Lista<>(Processo.class);
	Lista<Professor> listaProfessores = new Lista<>(Professor.class);
	
	public ConsultaController(JComboBox<String> cboxConsultaInscritos, JButton btnConsultaBuscarInscritos, 
			JButton btnConsultaBuscarProcessos, JTextArea taConsulta) {
		
		this.cboxConsultaInscritos = cboxConsultaInscritos;
		this.btnConsultaBuscarInscritos = btnConsultaBuscarInscritos;
		this.btnConsultaBuscarProcessos = btnConsultaBuscarProcessos;
		this.taConsulta = taConsulta;
		
		try {
			carregarDisciplinas();
			carregarInscrições();
			carregarProcessos();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			
			if (e.getSource() == btnConsultaBuscarInscritos) {
				
				listaProfessores.clear();
				listaInscritos.clear();
				
				carregarInscrições();
				carregarProfessores();
				
				buscarDisciplina();
			}
			if (e.getSource() == btnConsultaBuscarProcessos) {
				
				listaDisciplinas.clear();
				listaProcessos.clear();
				
				carregarDisciplinas();
				carregarProcessos();
				
				buscarProcesso();
			}
			
		}catch(Exception exc) {
			
			System.err.println(exc.getMessage());
		}

	}
	
	private void carregarDisciplinas() throws Exception {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File arq = new File(path, "Disciplinas.csv");

		// Verifica se o arquivo existe e faz a leitura
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);

			String linha = buffer.readLine();

			while (linha != null) {
				String[] dados = linha.split(";");

				Disciplina disciplina = new Disciplina();

				disciplina.setCodigoDisciplina(Integer.parseInt(dados[0]));
				disciplina.setNome(dados[1]);
				disciplina.setDiaSemana(dados[2]);
				disciplina.setHoraInicio(dados[3]);
				disciplina.setQtdHoras(Integer.parseInt(dados[4]));
				disciplina.setCodigoCurso(Integer.parseInt(dados[5]));

				listaDisciplinas.addLast(disciplina);
				linha = buffer.readLine();

			}
			fis.close();
			isr.close();
			buffer.close();
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
	
	private void buscarProcesso()throws Exception {
		
		taConsulta.setText("");
		
		int tamanhoDisciplina = listaDisciplinas.size();
		int tamanhoProcesso = listaProcessos.size();
		
		StringBuffer resultado = new StringBuffer();
		resultado.append("Disciplinas com processos abertos: \n\n");
		
		for(int i = 0; i<tamanhoProcesso; i++) {
			
			Processo p = listaProcessos.get(i);
			
			for(int j=0; j<tamanhoDisciplina; j++) {
				
				Disciplina disciplina = listaDisciplinas.get(j);
				
				String nome = disciplina.getCodigoDisciplina()+" - "+disciplina.getNome()+" - "+
				disciplina.getHoraInicio()+" - "+disciplina.getDiaSemana();
				
				if(p.getStatus().equals("Aberto")) {
					
					if(p.getNomeDisciplina().equals(nome)) {
						
						resultado.append(disciplina.getCodigoDisciplina()+" - "+disciplina.getNome()+"\n");
						resultado.append(disciplina.getHoraInicio()+" - "+disciplina.getDiaSemana()+"\n\n");
					}
				}
			}
		}
		
		String processosAberto = resultado.toString();
		
		taConsulta.setText(processosAberto);
	}
	
	private void carregarProfessores() throws Exception {

		String path =
				System.getProperty("user.home")
				+ File.separator +
				"SistemaCadastro";

		File arq =
				new File(path,
				"Professor.csv");

		if (arq.exists() && arq.isFile()) {

			FileInputStream fis =
					new FileInputStream(arq);

			InputStreamReader isr =
					new InputStreamReader(fis);

			BufferedReader buffer =
					new BufferedReader(isr);

			String linha =
					buffer.readLine();

			while (linha != null) {

				String[] dados =
						linha.split(";");

				Professor professor =
						new Professor();

				professor.cpf = dados[0];
				professor.nome = dados[1];
				professor.area = dados[2];
				professor.pontos =
						Integer.parseInt(dados[3]);

				listaProfessores.addLast(professor);

				linha = buffer.readLine();
			}

			buffer.close();
			isr.close();
			fis.close();
		}
	}
	
	private void buscarDisciplina()throws Exception {
		
		String cboxDisciplina = (String) cboxConsultaInscritos.getSelectedItem();
		String[] vetCodigoDisciplina = cboxDisciplina.split("-");
		String codigoDisciplina = vetCodigoDisciplina[0].trim();
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("Professores inscritos no processo da sisciplina:\n"+cboxDisciplina+"\nCPF - Nome - Área - Pontos\n\n");
		
		int tamanhoInscritos = listaInscritos.size();
		int tamanhoProfessores = listaProfessores.size();
		
		Lista<String> listaDisciplina = new Lista<>(String.class);
		
		for(int i=0; i<tamanhoInscritos; i++) {
			
			String inscrito = listaInscritos.get(i);
			
			if(inscrito.contains(codigoDisciplina)) {
				
				listaDisciplina.addLast(inscrito);
			}
		}
		
		tamanhoInscritos = listaDisciplina.size();
		Lista<Professor> classificacao = new Lista<>(Professor.class);
		
		for(int i=0; i< tamanhoProfessores; i++) {
			
			Professor p = listaProfessores.get(i);
			for(int j=0; j<tamanhoInscritos; j++) {
				
				String[] vetInscritos = listaDisciplina.get(j).split(";");
				
				if(vetInscritos[0].equals(p.getCpf())) {
					
					classificacao.addLast(p);
				}
			}
		}
		
	
		QuickSort metodo = new QuickSort();
		
		int tamanho = classificacao.size();
		metodo.ordenar(classificacao, 0, tamanho-1);
		
		
		for(int i=0; i<tamanho; i++) {
			
			buffer.append(classificacao.get(i).toString());
			buffer.append("\n\n");
		}
		
		String ordem = buffer.toString();
		
		taConsulta.setText(ordem);
	}

}
