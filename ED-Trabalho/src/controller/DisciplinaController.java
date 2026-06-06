package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Disciplina;
import otavioolinto.Lista;

public class DisciplinaController implements ActionListener {

	// Lista de disciplinas
	private Lista<Disciplina> listaDisciplinas = new Lista<>(Disciplina.class);

	// Campos da tela
	private JTextField tfDisciplinaCodigo;
	private JTextField tfDisciplinaNome;
	private JComboBox<String> cboxDisciplinaDiaSemana;
	private JTextField tfDisciplinaHoraInicio;
	private JTextField tfDisciplinaQntHoras;
	private JComboBox<String> cboxDisciplinaCodigo;
	private JTextArea taDisciplina;

	public DisciplinaController(JTextField tfDisciplinaCodigo, JTextField tfDisciplinaNome,
			JComboBox<String> cboxDisciplinaDiaSemana, JTextField tfDisciplinaHoraInicio,
			JTextField tfDisciplinaQntHoras, JComboBox<String> cboxDisciplinaCodigo, JTextArea taDisciplina) {
		this.tfDisciplinaCodigo = tfDisciplinaCodigo;
		this.tfDisciplinaNome = tfDisciplinaNome;
		this.cboxDisciplinaDiaSemana = cboxDisciplinaDiaSemana;
		this.tfDisciplinaHoraInicio = tfDisciplinaHoraInicio;
		this.tfDisciplinaQntHoras = tfDisciplinaQntHoras;
		this.cboxDisciplinaCodigo = cboxDisciplinaCodigo;
		this.taDisciplina = taDisciplina;

		// Carrega os dados
		try {
			carregarDisciplinas();
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
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Buscar")) {
			buscar();
		}
		if(cmd.equals("Remover")) {
			remove();
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

	private void buscar() {
		taDisciplina.setText("");

		try {
			// verifica se os campos não estão vazios
			if (!tfDisciplinaCodigo.getText().isBlank()) {
				int codigo = Integer.parseInt(tfDisciplinaCodigo.getText());
				for (int i = 0; i < listaDisciplinas.size(); i++) {
					Disciplina disciplina = listaDisciplinas.get(i);

					if (disciplina.getCodigoDisciplina() == codigo) {
						taDisciplina.setText(disciplina.toString());
						return;
					}

				}
				taDisciplina.setText("Disciplina não encontrada");
				// verifica se os campos não estão vazios
			} else if (!tfDisciplinaNome.getText().isBlank()) {
				String nome = tfDisciplinaNome.getText();
				for (int i = 0; i < listaDisciplinas.size(); i++) {
					Disciplina disciplina = listaDisciplinas.get(i);
					if (disciplina.getNome().equalsIgnoreCase(nome)) {
						taDisciplina.setText(disciplina.toString());
						return;
					}
				}
			} else {
				taDisciplina.setText("Nenhuma disciplina encontrada");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void salvar() throws IOException {
		Disciplina disciplina = new Disciplina();
		
		disciplina.setCodigoDisciplina(Integer.parseInt(tfDisciplinaCodigo.getText()));
		disciplina.setNome(tfDisciplinaNome.getText());
		disciplina.setDiaSemana(cboxDisciplinaDiaSemana.getSelectedItem().toString());
		disciplina.setHoraInicio(tfDisciplinaHoraInicio.getText());
		disciplina.setQtdHoras(Integer.parseInt(tfDisciplinaQntHoras.getText()));
		disciplina.setCodigoCurso(Integer.parseInt(cboxDisciplinaCodigo.getSelectedItem().toString()));

		try {
			listaDisciplinas.addLast(disciplina);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		taDisciplina.setText(disciplina.toString());
		cadastrarDisciplina(disciplina);

		tfDisciplinaCodigo.setText("");
		tfDisciplinaNome.setText("");
		cboxDisciplinaDiaSemana.setSelectedIndex(-1);
		tfDisciplinaHoraInicio.setText("");
		tfDisciplinaQntHoras.setText("");
		cboxDisciplinaCodigo.setSelectedIndex(-1);
	}

	private void cadastrarDisciplina(Disciplina csvDisciplina) throws IOException {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File dir = new File(path);

		if (!dir.exists()) {

			dir.mkdir();

		}
		File arq = new File(path, "Disciplinas.csv");

		boolean existe = false;

		if (arq.exists()) {

			existe = true;

		}

		FileWriter fw = new FileWriter(arq, existe);

		PrintWriter pw = new PrintWriter(fw);

		pw.write(csvDisciplina + "\r\n");

		pw.flush();

		pw.close();

		fw.close();
	}

	private void remove() {
		try {

			if (tfDisciplinaCodigo.getText().isBlank()) {
	            taDisciplina.setText("Informe o código da disciplina.");
	            return;
	        }
			
			int codigo = Integer.parseInt(tfDisciplinaCodigo.getText());
			
			int posicao = -1;

			for (int i = 0; i < listaDisciplinas.size(); i++) {
				Disciplina disciplina = listaDisciplinas.get(i);

				if (disciplina.getCodigoDisciplina() == codigo) {
					posicao = i;
					break;
				}

			}
			
			if (posicao == -1) {
				taDisciplina.setText("Disciplina não encontrada");
				return;
			}

			listaDisciplinas.remove(posicao);

			arqatualiza();

			tfDisciplinaCodigo.setText("");
			tfDisciplinaNome.setText("");
			cboxDisciplinaDiaSemana.setSelectedIndex(-1);
			tfDisciplinaHoraInicio.setText("");
			tfDisciplinaQntHoras.setText("");
			cboxDisciplinaCodigo.setSelectedIndex(-1);
			taDisciplina.setText("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void arqatualiza() throws Exception {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "Disciplinas.csv");
		FileWriter fw = new FileWriter(arq, false);
		PrintWriter pw = new PrintWriter(fw);

		for (int i = 0; i < listaDisciplinas.size(); i++) {

			Disciplina disciplina = listaDisciplinas.get(i);

			pw.println(disciplina.toString());
		}

		pw.flush();

		pw.close();

		fw.close();
	}
	
	// Retorna um array de String com os códigos das disciplinas cadastradas
	public String[] buscarCodigos() {
		String[] vetorCodigos = new String[listaDisciplinas.size()];

		try {
			for (int i = 0; i < listaDisciplinas.size(); i++) {
				vetorCodigos[i] = Integer.toString(listaDisciplinas.get(i).getCodigoDisciplina());
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return vetorCodigos;	
	}
}
