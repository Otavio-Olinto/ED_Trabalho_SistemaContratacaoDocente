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
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JScrollPane;

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
	private JTextField tfProfessorCpf;
	private JTextField tfProfessorNome;
	private JTextField tfProfessorQntPontos;
	private JTextField tfProcessoCodigo;
	
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
		setBounds(100, 100, 640, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 604, 459);
		contentPane.add(tabbedPane);
		
		String[] dias = {"Segunda-feira","Terça-feira","Quarta-feira","Quinta-feira","Sexta-feira","Sábado"};
		
		//------------------------------------------ Tab Curso ------------------------------------------------------------
		
		JPanel tabCurso = new JPanel();
		tabCurso.setBackground(new Color(240, 240, 240));
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
		tfCursoNome.setBounds(110, 100, 450, 25);
		tabCurso.add(tfCursoNome);
		tfCursoNome.setColumns(10);
		
		JLabel lblCursoArea = new JLabel("Área:");
		lblCursoArea.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCursoArea.setBounds(40, 160, 90, 25);
		tabCurso.add(lblCursoArea);
		
		tfCursoArea = new JTextField();
		tfCursoArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCursoArea.setToolTipText("Digite a Área do Curso");
		tfCursoArea.setBounds(110, 160, 450, 25);
		tabCurso.add(tfCursoArea);
		tfCursoArea.setColumns(10);
		
		JButton btnCursoSalvar = new JButton("Salvar");
		btnCursoSalvar.setToolTipText("Adicionar ou Alterar os dados de um Curso");
		btnCursoSalvar.setForeground(new Color(0, 128, 0));
		btnCursoSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoSalvar.setBounds(40, 220, 90, 25);
		tabCurso.add(btnCursoSalvar);
		
		JButton btnCursoBuscar = new JButton("Buscar");
		btnCursoBuscar.setToolTipText("Buscar Curso");
		btnCursoBuscar.setForeground(new Color(0, 0, 0));
		btnCursoBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoBuscar.setBounds(255, 220, 90, 25);
		tabCurso.add(btnCursoBuscar);
		
		JButton btnCursoRemover = new JButton("Remover");
		btnCursoRemover.setToolTipText("Remover Curso do Cadastro");
		btnCursoRemover.setForeground(new Color(255, 0, 0));
		btnCursoRemover.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoRemover.setBounds(469, 220, 90, 25);
		tabCurso.add(btnCursoRemover);
		
		JScrollPane scrollPaneCurso = new JScrollPane();
		scrollPaneCurso.setBounds(10, 256, 579, 164);
		tabCurso.add(scrollPaneCurso);
		
		JTextArea taCurso = new JTextArea();
		scrollPaneCurso.setViewportView(taCurso);
		
		//------------------------------------------ Tab Disciplina -------------------------------------------------------
		
		JPanel tabDisciplina = new JPanel();
		tabDisciplina.setBackground(SystemColor.menu);
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
		cboxDisciplinaCodigo.setToolTipText("Selecione o Código do Curso desejado");
		cboxDisciplinaCodigo.setSelectedIndex(-1);
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
		
		JComboBox<String> cboxDisciplinaDiaSemana = new JComboBox<>(dias);
		cboxDisciplinaDiaSemana.setToolTipText("Selecione o dia da Semana de aplicação da Disciplina");
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
		
		JButton btnDisciplinaSalvar = new JButton("Salvar");
		btnDisciplinaSalvar.setToolTipText("Criar ou Alterar uma Disciplina");
		btnDisciplinaSalvar.setForeground(new Color(0, 128, 0));
		btnDisciplinaSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDisciplinaSalvar.setBounds(40, 220, 90, 25);
		tabDisciplina.add(btnDisciplinaSalvar);
		
		JButton btnDisciplinaBuscar = new JButton("Buscar");
		btnDisciplinaBuscar.setToolTipText("Buscar Disciplina");
		btnDisciplinaBuscar.setForeground(new Color(0, 0, 0));
		btnDisciplinaBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDisciplinaBuscar.setBounds(255, 220, 90, 25);
		tabDisciplina.add(btnDisciplinaBuscar);
		
		JButton btnDisciplinaRemover = new JButton("Remover");
		btnDisciplinaRemover.setToolTipText("Remover Disciplina do Cadastro");
		btnDisciplinaRemover.setForeground(new Color(255, 0, 0));
		btnDisciplinaRemover.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDisciplinaRemover.setBounds(469, 220, 90, 25);
		tabDisciplina.add(btnDisciplinaRemover);
		
		JScrollPane scrollPaneDisciplina = new JScrollPane();
		scrollPaneDisciplina.setBounds(10, 256, 579, 164);
		tabDisciplina.add(scrollPaneDisciplina);
		
		JTextArea taDisciplina = new JTextArea();
		scrollPaneDisciplina.setViewportView(taDisciplina);
				
		//------------------------------------------ Tab Professor -------------------------------------------------------
		
		JPanel tabProfessor = new JPanel();
		tabbedPane.addTab("Professor", null, tabProfessor, "Cadastro de Professores");
		tabProfessor.setLayout(null);
		
		JLabel lblProfessorCpf = new JLabel("CPF do Docente:");
		lblProfessorCpf.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProfessorCpf.setBounds(40, 40, 160, 25);
		tabProfessor.add(lblProfessorCpf);
		
		tfProfessorCpf = new JTextField();
		tfProfessorCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProfessorCpf.setToolTipText("Digite o CPF do Docente");
		tfProfessorCpf.setBounds(190, 40, 160, 25);
		tabProfessor.add(tfProfessorCpf);
		tfProfessorCpf.setColumns(10);
		
		JLabel lblProfessorNome = new JLabel("Nome do Docente:");
		lblProfessorNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProfessorNome.setBounds(40, 100, 160, 25);
		tabProfessor.add(lblProfessorNome);
		
		tfProfessorNome = new JTextField();
		tfProfessorNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProfessorNome.setToolTipText("Digite o Nome do Docente");
		tfProfessorNome.setBounds(190, 100, 379, 25);
		tabProfessor.add(tfProfessorNome);
		tfProfessorNome.setColumns(10);
		
		JLabel lblProfessorArea = new JLabel("Área de Atuação:");
		lblProfessorArea.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProfessorArea.setBounds(40, 160, 160, 25);
		tabProfessor.add(lblProfessorArea);
		
		JComboBox<String> cboxProfessorArea = new JComboBox<>(dias);
		cboxProfessorArea.setToolTipText("O Docente só pode ser cadastrado se sua área for compatível com algum curso da unidade");
		cboxProfessorArea.setSelectedIndex(-1);
		cboxProfessorArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboxProfessorArea.setBounds(190, 160, 160, 25);
		tabProfessor.add(cboxProfessorArea);
		
		JLabel lblProfessorQntPontos = new JLabel("Quantidade de Pontos:");
		lblProfessorQntPontos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProfessorQntPontos.setBounds(360, 160, 160, 25);
		tabProfessor.add(lblProfessorQntPontos);
		
		tfProfessorQntPontos = new JTextField();
		tfProfessorQntPontos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProfessorQntPontos.setToolTipText("Digite a quantidade de pontos do Docente");
		tfProfessorQntPontos.setBounds(530, 160, 40, 25);
		tabProfessor.add(tfProfessorQntPontos);
		tfProfessorQntPontos.setColumns(10);
		
		
		JButton btnProfessorSalvar = new JButton("Salvar");
		btnProfessorSalvar.setToolTipText("Adicionar ou Alterar os dados de um Docente");
		btnProfessorSalvar.setForeground(new Color(0, 128, 0));
		btnProfessorSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorSalvar.setBounds(40, 220, 90, 25);
		tabProfessor.add(btnProfessorSalvar);
		
		JButton btnProfessorBuscar = new JButton("Buscar");
		btnProfessorBuscar.setToolTipText("Buscar Docente");
		btnProfessorBuscar.setForeground(new Color(0, 0, 0));
		btnProfessorBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorBuscar.setBounds(255, 220, 90, 25);
		tabProfessor.add(btnProfessorBuscar);
		
		JButton btnProfessorRemover = new JButton("Remover");
		btnProfessorRemover.setToolTipText("Remover Docente do Cadastro");
		btnProfessorRemover.setForeground(new Color(255, 0, 0));
		btnProfessorRemover.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorRemover.setBounds(469, 220, 90, 25);
		tabProfessor.add(btnProfessorRemover);
		
		JScrollPane scrollPaneProfessor = new JScrollPane();
		scrollPaneProfessor.setBounds(10, 256, 579, 164);
		tabProfessor.add(scrollPaneProfessor);
		
		JTextArea taProfessor = new JTextArea();
		scrollPaneProfessor.setViewportView(taProfessor);
		
		//------------------------------------------ Tab Processo --------------------------------------------------------
		
		JPanel tabProcesso = new JPanel();
		tabbedPane.addTab("Processo", null, tabProcesso, "Tela para atualizar um processo");
		tabProcesso.setLayout(null);
		
		JLabel lblProcessoCodigo = new JLabel("Código do Processo:");
		lblProcessoCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProcessoCodigo.setBounds(40, 40, 160, 25);
		tabProcesso.add(lblProcessoCodigo);
		
		tfProcessoCodigo = new JTextField();
		tfProcessoCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProcessoCodigo.setToolTipText("Digite o Código do Processo");
		tfProcessoCodigo.setBounds(210, 40, 160, 25);
		tabProcesso.add(tfProcessoCodigo);
		tfProcessoCodigo.setColumns(10);
		
		JLabel lblProcessoStatus = new JLabel("Status:");
		lblProcessoStatus.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProcessoStatus.setBounds(380, 40, 90, 25);
		tabProcesso.add(lblProcessoStatus);
		
		String[] status = {"Aberto","Inscrições Encerradas","Finalizado"};
		JComboBox<String> cboxProcessoStatus = new JComboBox<>(status);
		cboxProcessoStatus.setToolTipText("Selecione o Status do Processo");
		cboxProcessoStatus.setSelectedIndex(-1);
		cboxProcessoStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboxProcessoStatus.setBounds(450, 40, 140, 25);
		tabProcesso.add(cboxProcessoStatus);
		
		JLabel lblProcessoDisciplina = new JLabel("Disciplina:");
		lblProcessoDisciplina.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProcessoDisciplina.setBounds(40, 100, 90, 25);
		tabProcesso.add(lblProcessoDisciplina);
		
		JComboBox<String> cboxDisciplina = new JComboBox<>(status);
		cboxDisciplina.setToolTipText("Selecione as Disciplinas que irão fazer parte do processo");
		cboxDisciplina.setSelectedIndex(-1);
		cboxDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboxDisciplina.setBounds(140, 100, 450, 25);
		tabProcesso.add(cboxDisciplina);
		
		JButton btnProcessoSalvar = new JButton("Salvar");
		btnProcessoSalvar.setToolTipText("Adicionar ou Alterar os dados de um Processo");
		btnProcessoSalvar.setForeground(new Color(0, 128, 0));
		btnProcessoSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProcessoSalvar.setBounds(105, 160, 90, 25);
		tabProcesso.add(btnProcessoSalvar);
		
		JButton btnProcessoBuscar = new JButton("Buscar");
		btnProcessoBuscar.setToolTipText("Buscar Processo");
		btnProcessoBuscar.setForeground(new Color(0, 0, 0));
		btnProcessoBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProcessoBuscar.setBounds(404, 160, 90, 25);
		tabProcesso.add(btnProcessoBuscar);
		
		JScrollPane scrollPaneProcesso = new JScrollPane();
		scrollPaneProcesso.setBounds(10, 196, 579, 224);
		tabProcesso.add(scrollPaneProcesso);
		
		JTextArea taProcesso = new JTextArea();
		scrollPaneProcesso.setViewportView(taProcesso);
		
		//------------------------------------------ Tab Inscrições ------------------------------------------------------
		
		JPanel tabInscrições = new JPanel();
		tabbedPane.addTab("Inscrições", null, tabInscrições, "Inscrições dos Professores nos processos abertos");
		tabInscrições.setLayout(null);
		
		JLabel lblIncriçõesCpf = new JLabel("CPF do Docente:");
		lblIncriçõesCpf.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIncriçõesCpf.setBounds(40, 40, 160, 25);
		tabInscrições.add(lblIncriçõesCpf);
		
		JComboBox<String> cboxIncriçõesCpf = new JComboBox<>(dias);
		cboxIncriçõesCpf.setToolTipText("Selecione o CPF do Docente");
		cboxIncriçõesCpf.setSelectedIndex(-1);
		cboxIncriçõesCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboxIncriçõesCpf.setBounds(210, 40, 250, 25);
		tabInscrições.add(cboxIncriçõesCpf);
		
		JLabel lblInscriçõesProcesso = new JLabel("Código do Processo:");
		lblInscriçõesProcesso.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInscriçõesProcesso.setBounds(40, 100, 160, 25);
		tabInscrições.add(lblInscriçõesProcesso);
		
		JComboBox<String> cboxIncriçõesCodigoProcesso = new JComboBox<>(dias);
		cboxIncriçõesCodigoProcesso.setToolTipText("Selecione o Código do Processo Aberto");
		cboxIncriçõesCodigoProcesso.setSelectedIndex(-1);
		cboxIncriçõesCodigoProcesso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboxIncriçõesCodigoProcesso.setBounds(210, 100, 250, 25);
		tabInscrições.add(cboxIncriçõesCodigoProcesso);
		
		JLabel lblIncriçõesCodigo = new JLabel("Código da Disciplina:");
		lblIncriçõesCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIncriçõesCodigo.setBounds(40, 160, 160, 25);
		tabInscrições.add(lblIncriçõesCodigo);
		
		JComboBox<String> cboxIncriçõesCodigoDisciplina = new JComboBox<>(dias);
		cboxIncriçõesCodigoDisciplina.setToolTipText("Selecione o Código da Disciplina");
		cboxIncriçõesCodigoDisciplina.setSelectedIndex(-1);
		cboxIncriçõesCodigoDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboxIncriçõesCodigoDisciplina.setBounds(210, 160, 250, 25);
		tabInscrições.add(cboxIncriçõesCodigoDisciplina);
		
		JButton btnIncriçõesSalvar = new JButton("Salvar");
		btnIncriçõesSalvar.setToolTipText("Adicionar ou Alterar os dados de uma Inscrição");
		btnIncriçõesSalvar.setForeground(new Color(0, 128, 0));
		btnIncriçõesSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIncriçõesSalvar.setBounds(40, 220, 90, 25);
		tabInscrições.add(btnIncriçõesSalvar);
		
		JButton btnIncriçõesBuscar = new JButton("Buscar");
		btnIncriçõesBuscar.setToolTipText("Buscar Inscrição");
		btnIncriçõesBuscar.setForeground(new Color(0, 0, 0));
		btnIncriçõesBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIncriçõesBuscar.setBounds(255, 220, 90, 25);
		tabInscrições.add(btnIncriçõesBuscar);
		
		JButton btnIncriçõesRemover = new JButton("Remover");
		btnIncriçõesRemover.setToolTipText("Remover Inscrição do Cadastro");
		btnIncriçõesRemover.setForeground(new Color(255, 0, 0));
		btnIncriçõesRemover.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIncriçõesRemover.setBounds(469, 220, 90, 25);
		tabInscrições.add(btnIncriçõesRemover);
		
		JScrollPane scrollPaneInscrições = new JScrollPane();
		scrollPaneInscrições.setBounds(10, 256, 579, 164);
		tabInscrições.add(scrollPaneInscrições);
		
		JTextArea taInscrições = new JTextArea();
		scrollPaneInscrições.setViewportView(taInscrições);
		
		//------------------------------------------ Tab Consulta --------------------------------------------------------
		
		JPanel tabConsulta = new JPanel();
		tabbedPane.addTab("Consulta", null, tabConsulta, "Consulta das Inscrições e Disciplinas");
		tabConsulta.setLayout(null);
		
		JLabel lblConsultaInscritos = new JLabel("Inscritos por Disciplina:");
		lblConsultaInscritos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConsultaInscritos.setBounds(40, 40, 166, 25);
		tabConsulta.add(lblConsultaInscritos);

		JComboBox<String> cboxConsultaInscritos = new JComboBox<>(dias);
		cboxConsultaInscritos.setToolTipText("Selecione a Disciplina que deseja Consultar");
		cboxConsultaInscritos.setSelectedIndex(-1);
		cboxConsultaInscritos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboxConsultaInscritos.setBounds(210, 40, 250, 25);
		tabConsulta.add(cboxConsultaInscritos);
		
		JButton btnConsultaBuscarInscritos = new JButton("Buscar");
		btnConsultaBuscarInscritos.setToolTipText("Buscar Inscrição");
		btnConsultaBuscarInscritos.setForeground(new Color(0, 0, 0));
		btnConsultaBuscarInscritos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConsultaBuscarInscritos.setBounds(469, 40, 90, 25);
		tabConsulta.add(btnConsultaBuscarInscritos);
		
		
		JLabel lblConsultaProcessosAbertos = new JLabel("Disciplinas com Processos Abertos:");
		lblConsultaProcessosAbertos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConsultaProcessosAbertos.setBounds(40, 100, 270, 25);
		tabConsulta.add(lblConsultaProcessosAbertos);
		
		JButton btnConsultaBuscarProcessos = new JButton("Buscar");            
		btnConsultaBuscarProcessos.setToolTipText("Buscar Disciplinas com Processos Abertos");         
		btnConsultaBuscarProcessos.setForeground(new Color(0, 0, 0));          
		btnConsultaBuscarProcessos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConsultaBuscarProcessos.setBounds(469, 100, 90, 25);                 
		tabConsulta.add(btnConsultaBuscarProcessos);  
		
		JScrollPane scrollPaneConsulta = new JScrollPane();
		scrollPaneConsulta.setBounds(10, 136, 579, 284);
		tabConsulta.add(scrollPaneConsulta);
		
		JTextArea taConsulta = new JTextArea();
		scrollPaneConsulta.setViewportView(taConsulta);
	}   
}          