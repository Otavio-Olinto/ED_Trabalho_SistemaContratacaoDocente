package controller;

import model.Curso;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import otavioolinto.Lista;

import view.Tela;

public class cursoController implements ActionListener {

	private Lista<Curso> listaCursos = new Lista<>(Curso.class);

	private JTextField tfCursoCodigo;
	private JTextField tfCursoNome;
	private JTextField tfCursoArea;
	private JTextArea taCurso;
	
	private Tela tela;

	public cursoController(JTextField tfCursoCodigo, JTextField tfCursoNome, JTextField tfCursoArea,
			JTextArea taCurso, Tela tela) {

		super();
		
		this.tfCursoCodigo = tfCursoCodigo;
		this.tfCursoNome = tfCursoNome;
		this.tfCursoArea = tfCursoArea;
		this.taCurso = taCurso;
		this.tela = tela;

		try {

			carregarCursos();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// Carrega os cursos do csv

	private void carregarCursos() throws Exception {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File arq = new File(path, "Cursos.csv");

		if (arq.exists() && arq.isFile()) {

			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);

			String linha = buffer.readLine();

			while (linha != null) {

				String[] dados = linha.split(";");

				Curso curso = new Curso();

				curso.codigo = Integer.parseInt(dados[0]);

				curso.nome = dados[1];

				curso.area = dados[2];

				listaCursos.addLast(curso);

				linha = buffer.readLine();

			}

			buffer.close();
			isr.close();
			fis.close();

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

		if (cmd.equals("Remover")) {

			remover();

		}

	}

	private void buscar() {

		taCurso.setText("");

		try {

			// Busca por codigo

			if (!tfCursoCodigo.getText().isBlank()) {

				int codigo = Integer.parseInt(tfCursoCodigo.getText());

				for (int i = 0; i < listaCursos.size(); i++) {

					Curso curso = listaCursos.get(i);

					if (curso.codigo == codigo) {

						taCurso.setText(curso.toString());

						return;
					}
				}

				taCurso.setText("Curso não encontrado.");
			}

			// Busca por nome

			else if (!tfCursoNome.getText().isBlank()) {

				String nome = tfCursoNome.getText();

				for (int i = 0; i < listaCursos.size(); i++) {

					Curso curso = listaCursos.get(i);

					if (curso.nome.equalsIgnoreCase(nome)) {

						taCurso.setText(curso.toString());

						return;

					}

				}

				taCurso.setText("Curso não encontrado.");
			}

			// Busca por area

			else if (!tfCursoArea.getText().isBlank()) {

				String area = tfCursoArea.getText();

				StringBuilder resultado = new StringBuilder();

				for (int i = 0; i < listaCursos.size(); i++) {

					Curso curso = listaCursos.get(i);

					if (curso.area.equalsIgnoreCase(area)) {

						resultado.append(curso.toString()).append("\n");

					}

				}

				if (resultado.length() > 0) {

					taCurso.setText(resultado.toString());

				} else {

					taCurso.setText("Nenhum curso encontrado para esta área.");

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	private void salvar() throws Exception {

		Curso curso = new Curso();

		curso.codigo = Integer.parseInt(tfCursoCodigo.getText());

		curso.nome = tfCursoNome.getText();

		curso.area = tfCursoArea.getText();

		try {

			listaCursos.addLast(curso);

		} catch (Exception e) {

			e.printStackTrace();
		}

		taCurso.append(curso.toString() + "\n");

		cadastraCurso(curso.toString());

		tfCursoCodigo.setText("");

		tfCursoNome.setText("");

		tfCursoArea.setText("");
		
		taCurso.setText("");
		
		tela.adicionarCodigoCurso(String.valueOf(curso.codigo));
		if(qntAreaIguais(curso.area)==0) tela.adicionarArea(curso.area);
	}

	private void cadastraCurso(String csvCurso) throws IOException {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File dir = new File(path);

		if (!dir.exists()) {

			dir.mkdir();

		}
		File arq = new File(path, "Cursos.csv");

		boolean existe = false;

		if (arq.exists()) {

			existe = true;

		}
		FileWriter fw = new FileWriter(arq, existe);

		PrintWriter pw = new PrintWriter(fw);

		pw.write(csvCurso + "\r\n");

		pw.flush();

		pw.close();

		fw.close();

	}

	private void remover() {
		
		taCurso.setText("");

		try {

			int codigo = Integer.parseInt(tfCursoCodigo.getText());

			int posicao = -1;

			for (int i = 0; i < listaCursos.size(); i++) {

				Curso curso = listaCursos.get(i);

				if (curso.codigo == codigo) {
					
					tela.removerCodigoCurso(String.valueOf(curso.codigo));
					if(qntAreaIguais(curso.area)==1) tela.removerArea(curso.area);

					posicao = i;

					break;
				}

			}

			if (posicao == -1) {

				taCurso.setText("Curso não encontrado.");

				return;

			}

			listaCursos.remove(posicao);

			arqatualiza();

			taCurso.setText("Curso removido com sucesso.");

			tfCursoCodigo.setText("");

			tfCursoNome.setText("");

			tfCursoArea.setText("");

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void arqatualiza() throws Exception {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File arq = new File(path, "Cursos.csv");

		FileWriter fw = new FileWriter(arq, false);

		PrintWriter pw = new PrintWriter(fw);

		for (int i = 0; i < listaCursos.size(); i++) {

			Curso curso = listaCursos.get(i);

			pw.println(curso.toString());
		}

		pw.flush();

		pw.close();

		fw.close();

	}

	public String[] buscarCodigos() {
		String[] vetorCodigos = new String[listaCursos.size()];

		try {
			for (int i = 0; i < listaCursos.size(); i++) {
				vetorCodigos[i] = Integer.toString(listaCursos.get(i).codigo);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return vetorCodigos;
		
	}
	
	public String[] buscarAreas() {
		
		StringBuffer buffer = new StringBuffer();
		
		int tamanho = listaCursos.size();
		
		String[] vetorAreas;
		
		String area = "";
		
		try {
			for (int i = 0; i < tamanho; i++) {
				area = listaCursos.get(i).area;
				
				for(int j = 0; j<=i; j++) {
					
					if(i==j) {
						
						buffer.append(area);
						buffer.append(";");
					}
					
					if(area.equals(listaCursos.get(j).area)) {
						break;
					}
				}
			}
			
			 area = buffer.toString();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		vetorAreas = area.split(";");
		return vetorAreas;
		
	}
	
	private int qntAreaIguais(String area) {
		
		int qnt = 0;

		int tamanho = listaCursos.size();
		
		Curso curso;
		
		for(int i=0; i<tamanho; i++) {
			
			try {
				
				curso = listaCursos.get(i);
				
				if(curso.area.equals(area)) qnt++;
				
			}catch (Exception exc) {
				
				System.err.println(exc.getMessage());
			}
			
		}
		
		return qnt;
	}

}
