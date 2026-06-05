package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfDisciplinaNome;
	private JTextField tfDisciplinaCodigo;
	private JTextField tfDisciplinaHoraInicio;
	private JTextField tfDisciplinaQntHoras;
	private JTextField tfCursoCodigo;
	private JTextField tfCursoNome;
	private JTextField tfCursoArea;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tela() {
		setTitle("Contratação de Docentes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 604, 419);
		contentPane.add(tabbedPane);
		
		//------------------------------------------ Tab Disciplina -------------------------------------------------------
		
		JPanel tabDisciplina = new JPanel();
		tabbedPane.addTab("Disciplina", null, tabDisciplina, "Cadastro de Disciplinas disponíveis");
		tabDisciplina.setLayout(null);
		
		JLabel lblDisciplinaCodigoDisciplina = new JLabel("Código da Disciplina:");
		lblDisciplinaCodigoDisciplina.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDisciplinaCodigoDisciplina.setBounds(40, 40, 160, 25);
		tabDisciplina.add(lblDisciplinaCodigoDisciplina);
		
		tfDisciplinaCodigo = new JTextField();
		tfDisciplinaCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaCodigo.setToolTipText("Digite o Código da Disciplina");
		tfDisciplinaCodigo.setBounds(190, 40, 160, 25);
		tabDisciplina.add(tfDisciplinaCodigo);
		tfDisciplinaCodigo.setColumns(10);
		
		JLabel lblDisciplinaCodigoCurso = new JLabel("Código do Curso:");
		lblDisciplinaCodigoCurso.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDisciplinaCodigoCurso.setBounds(360, 40, 160, 25);
		tabDisciplina.add(lblDisciplinaCodigoCurso);
		
		JComboBox<String> cboxDisciplinaCodigo = new JComboBox<>();
		cboxDisciplinaCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboxDisciplinaCodigo.setBounds(500, 40, 90, 25);
		tabDisciplina.add(cboxDisciplinaCodigo);
		
		JLabel lblDisciplinaNome = new JLabel("Nome:");
		lblDisciplinaNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDisciplinaNome.setBounds(40, 100, 90, 25);
		tabDisciplina.add(lblDisciplinaNome);
		
		tfDisciplinaNome = new JTextField();
		tfDisciplinaNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaNome.setToolTipText("Digite o Nome da Disciplina");
		tfDisciplinaNome.setBounds(110, 100, 240, 25);
		tabDisciplina.add(tfDisciplinaNome);
		tfDisciplinaNome.setColumns(10);
		
		JLabel lblDisciplinaDiaSemana = new JLabel("Dia da Semana:");
		lblDisciplinaDiaSemana.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDisciplinaDiaSemana.setBounds(360, 100, 160, 25);
		tabDisciplina.add(lblDisciplinaDiaSemana);
		
		String[] dias = {"Segunda-feira","Terça-feira","Quarta-feira","Quinta-feira","Sexta-feira","Sábado"};
		JComboBox<String> cboxDisciplinaDiaSemana = new JComboBox<>(dias);
		cboxDisciplinaDiaSemana.setSelectedIndex(-1);
		cboxDisciplinaDiaSemana.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboxDisciplinaDiaSemana.setBounds(480, 100, 110, 25);
		tabDisciplina.add(cboxDisciplinaDiaSemana);
		
		JLabel lblDisciplinaHoraInicio = new JLabel("Horário de Início:");
		lblDisciplinaHoraInicio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDisciplinaHoraInicio.setBounds(40, 160, 160, 25);
		tabDisciplina.add(lblDisciplinaHoraInicio);
		
		tfDisciplinaHoraInicio = new JTextField();
		tfDisciplinaHoraInicio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaHoraInicio.setToolTipText("Digite o Horário de Início das Aulas");
		tfDisciplinaHoraInicio.setBounds(170, 160, 180, 25);
		tabDisciplina.add(tfDisciplinaHoraInicio);
		tfDisciplinaHoraInicio.setColumns(10);
		
		JLabel lblDisciplinaQntHoras = new JLabel("Horas diárias:");
		lblDisciplinaQntHoras.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDisciplinaQntHoras.setBounds(360, 160, 160, 25);
		tabDisciplina.add(lblDisciplinaQntHoras);
		
		tfDisciplinaQntHoras = new JTextField();
		tfDisciplinaQntHoras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaQntHoras.setToolTipText("Digite a quantidade de horas diárias");
		tfDisciplinaQntHoras.setBounds(470, 160, 120, 25);
		tabDisciplina.add(tfDisciplinaQntHoras);
		tfDisciplinaQntHoras.setColumns(10);
		
		//------------------------------------------ Tab Curso ------------------------------------------------------------
		
		JPanel tabCurso = new JPanel();
		tabbedPane.addTab("Curso", null, tabCurso, "Cadastro de Cursos");
		tabCurso.setLayout(null);
		
		JLabel lblCursoCodigo = new JLabel("Código:");
		lblCursoCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCursoCodigo.setBounds(40, 40, 90, 25);
		tabCurso.add(lblCursoCodigo);
		
		tfCursoCodigo = new JTextField();
		tfCursoCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCursoCodigo.setToolTipText("Digite o Código do Curso");
		tfCursoCodigo.setBounds(110, 40, 160, 25);
		tabCurso.add(tfCursoCodigo);
		tfCursoCodigo.setColumns(10);
		
		JLabel lblCursoNome = new JLabel("Nome:");
		lblCursoNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCursoNome.setBounds(40, 100, 90, 25);
		tabCurso.add(lblCursoNome);
		
		tfCursoNome = new JTextField();
		tfCursoNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCursoNome.setToolTipText("Digite o Nome do Curso");
		tfCursoNome.setBounds(110, 100, 160, 25);
		tabCurso.add(tfCursoNome);
		tfCursoNome.setColumns(10);
		
		JLabel lblCursoArea = new JLabel("Área:");
		lblCursoArea.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCursoArea.setBounds(40, 160, 90, 25);
		tabCurso.add(lblCursoArea);
		
		tfCursoArea = new JTextField();
		tfCursoArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCursoArea.setToolTipText("Digite o Área do Curso");
		tfCursoArea.setBounds(110, 160, 160, 25);
		tabCurso.add(tfCursoArea);
		tfCursoArea.setColumns(10);
		
		//------------------------------------------ Tab Professor -------------------------------------------------------
		
		JPanel tabProfessor = new JPanel();
		tabbedPane.addTab("Professor", null, tabProfessor, "Cadastro de Professores");
		tabProfessor.setLayout(null);
		
		JLabel lblProfessorCpf = new JLabel("CPF do Docente:");
		lblProfessorCpf.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProfessorCpf.setBounds(40, 40, 160, 25);
		tabProfessor.add(lblProfessorCpf);
		
		JLabel lblProfessorNome = new JLabel("Nome do Docente:");
		lblProfessorNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProfessorNome.setBounds(40, 100, 160, 25);
		tabProfessor.add(lblProfessorNome);
		
		JLabel lblProfessorArea = new JLabel("Área de Atuação:");
		lblProfessorArea.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProfessorArea.setBounds(40, 160, 160, 25);
		tabProfessor.add(lblProfessorArea);
		
		JLabel lblProfessorQntPontos = new JLabel("Quantidade de Pontos:");
		lblProfessorQntPontos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProfessorQntPontos.setBounds(360, 160, 160, 25);
		tabProfessor.add(lblProfessorQntPontos);
		
		//------------------------------------------ Tab Inscrições ------------------------------------------------------
		
		JPanel tabInscrições = new JPanel();
		tabbedPane.addTab("Inscrições", null, tabInscrições, "Inscrições dos Professores nos processos abertos");
		tabInscrições.setLayout(null);
		
		JLabel lblIncriçõesCpf = new JLabel("CPF do Docente:");
		lblIncriçõesCpf.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIncriçõesCpf.setBounds(40, 40, 160, 25);
		tabInscrições.add(lblIncriçõesCpf);
		
		JLabel lblIncriçõesCodigo = new JLabel("Código da Disciplina:");
		lblIncriçõesCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIncriçõesCodigo.setBounds(40, 100, 160, 25);
		tabInscrições.add(lblIncriçõesCodigo);
		
		JLabel lblInscriçõesProcesso = new JLabel("Código do Processo:");
		lblInscriçõesProcesso.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInscriçõesProcesso.setBounds(40, 160, 160, 25);
		tabInscrições.add(lblInscriçõesProcesso);
		
		//------------------------------------------ Tab Consulta --------------------------------------------------------
		
		JPanel tabConsulta = new JPanel();
		tabbedPane.addTab("Consulta", null, tabConsulta, "Consulta das Inscrições e Disciplinas");
		tabConsulta.setLayout(null);
		
		JLabel lblConsultaInscritos = new JLabel("Inscritos por Disciplina:");
		lblConsultaInscritos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConsultaInscritos.setBounds(40, 40, 160, 25);
		tabConsulta.add(lblConsultaInscritos);
		
		JLabel lblConsultaProcessosAbertos = new JLabel("Processos Abertos:");
		lblConsultaProcessosAbertos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConsultaProcessosAbertos.setBounds(40, 100, 160, 25);
		tabConsulta.add(lblConsultaProcessosAbertos);

	}
}
