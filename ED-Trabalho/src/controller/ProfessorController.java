package controller;

import model.Professor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import otavioolinto.Lista;
import view.Tela;

	public class ProfessorController implements ActionListener {

		// Lista que armazena os professores
		private Lista<Professor> listaProfessores =
				new Lista<>(Professor.class);

		// Componentes da tela
		private JTextField tfProfessorCpf;
		private JTextField tfProfessorNome;
		private JTextField tfProfessorQntPontos;

		private JComboBox<String> cboxProfessorArea;

		private JTextArea taProfessor;
		private Tela tela;

		// =====================================================
		// CONSTRUTOR
		// =====================================================

		public ProfessorController(
				JTextField tfProfessorCpf,
				JTextField tfProfessorNome,
				JTextField tfProfessorQntPontos,
				JComboBox<String> cboxProfessorArea,
				JTextArea taProfessor, Tela tela) {

			super();

			this.tfProfessorCpf = tfProfessorCpf;
			this.tfProfessorNome = tfProfessorNome;
			this.tfProfessorQntPontos = tfProfessorQntPontos;
			this.cboxProfessorArea = cboxProfessorArea;
			this.taProfessor = taProfessor;
			this.tela = tela;

			try {

				carregarProfessores();

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		// CARREGA PROFESSORES DO CSV

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

		// CONTROLA OS BOTÕES DA TELA

		@Override
		public void actionPerformed(ActionEvent e) {

			String cmd =
					e.getActionCommand();

			if (cmd.equals("Salvar")) {

				try {

					salvar();

				} catch (IOException e1) {

					e1.printStackTrace();
				}
			}

			if (cmd.equals("Buscar")) {

				buscar();
			}

			if (cmd.equals("Remover")) {

				remover();
			}
		}

		// BUSCAR PROFESSOR

		private void buscar() {

			taProfessor.setText("");

			try {

				// Busca por CPF

				if (!tfProfessorCpf.getText().isBlank()) {

					String cpf =
							tfProfessorCpf.getText();

					for (int i = 0;
						 i < listaProfessores.size();
						 i++) {

						Professor professor =
								listaProfessores.get(i);

						if (professor.cpf.equals(cpf)) {

							taProfessor.setText(
									professor.toString());

							return;
						}
					}

					taProfessor.setText(
							"Professor não encontrado.");
				}

				// Busca por nome

				else if (!tfProfessorNome.getText().isBlank()) {

					String nome =
							tfProfessorNome.getText();

					for (int i = 0;
						 i < listaProfessores.size();
						 i++) {

						Professor professor =
								listaProfessores.get(i);

						if (professor.nome
								.equalsIgnoreCase(nome)) {

							taProfessor.setText(
									professor.toString());

							return;
						}
					}

					taProfessor.setText(
							"Professor não encontrado.");
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		// SALVAR PROFESSOR

		private void salvar() throws IOException {
			if(cboxProfessorArea.getSelectedItem() == null)
			{
			    taProfessor.setText(
			        "Selecione uma área.");
			    return;
			}

			Professor professor = new Professor();

			professor.cpf = tfProfessorCpf.getText();

			professor.nome = tfProfessorNome.getText();

			professor.area = cboxProfessorArea.getSelectedItem().toString();

			professor.pontos =Integer.parseInt(tfProfessorQntPontos.getText());

			try {

				listaProfessores.addLast(professor);

			} catch (Exception e) {

				e.printStackTrace();
			}

			taProfessor.append(professor.toString() + "\n");

			cadastraProfessor(professor.toString());
			
			tela.adicionarInscriçãoCpf(professor.cpf);

			tfProfessorCpf.setText("");
			tfProfessorNome.setText("");
			tfProfessorQntPontos.setText("");
			cboxProfessorArea.setSelectedIndex(-1);
		}

		// GRAVA PROFESSOR NO CSV

		private void cadastraProfessor(String csvProfessor)throws IOException {

			String path =System.getProperty("user.home")+ File.separator +"SistemaCadastro";

			File dir =new File(path);

			if (!dir.exists()) {
				dir.mkdir();
			}

			File arq =new File(path,"Professor.csv");

			boolean existe = false;

			if (arq.exists()) {

				existe = true;
			}

			FileWriter fw =new FileWriter(arq,existe);

			PrintWriter pw =new PrintWriter(fw);

			pw.write(csvProfessor + "\r\n");

			pw.flush();

			pw.close();

			fw.close();
		}

		// REMOVER PROFESSOR

		private void remover() {

			try {

				String cpf =tfProfessorCpf.getText();

				int posicao = -1;

				for (int i = 0; i < listaProfessores.size(); i++) {

					Professor professor =listaProfessores.get(i);

					if (professor.cpf.equals(cpf)) {
						posicao = i;

						break;
					}
				}

				if (posicao == -1) {

					taProfessor.setText("Professor não encontrado.");

					return;
				}
				
				tela.removerInscriçãoCpf(cpf);

				listaProfessores.remove(posicao);

				arqatualiza();

				taProfessor.setText("Professor removido com sucesso.");

				tfProfessorCpf.setText("");
				tfProfessorNome.setText("");
				tfProfessorQntPontos.setText("");

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		// REGRAVA O CSV APÓS REMOÇÃO

		private void arqatualiza() throws Exception {

			String path =
					System.getProperty("user.home")
					+ File.separator +
					"SistemaCadastro";

			File arq =
					new File(path,
					"Professor.csv");

			FileWriter fw =
					new FileWriter(arq,
					false);

			PrintWriter pw =
					new PrintWriter(fw);

			for (int i = 0;
				 i < listaProfessores.size();
				 i++) {

				Professor professor =
						listaProfessores.get(i);

				pw.println(
						professor.toString());
			}

			pw.flush();

			pw.close();

			fw.close();
		}
		
		public String[] buscarCpf() {
			
			StringBuffer buffer = new StringBuffer();
			
			int tamanho = listaProfessores.size();
			
			String[] vetorProfessores;
			
			String cpf = "";
			
			Professor professor;
			
			try {
				for (int i = 0; i < tamanho; i++) {
					
					professor = listaProfessores.get(i);
					
					for(int j = 0; j<=i; j++) {
						
						if(i==j) {
							
							buffer.append(professor.getCpf());
							buffer.append(";");
						}
						
						if(professor.equals(listaProfessores.get(j))) {
							break;
						}
					}
				}
				
				 cpf = buffer.toString();
				
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			
			vetorProfessores = cpf.split(";");
			return vetorProfessores;
			
		}

}

