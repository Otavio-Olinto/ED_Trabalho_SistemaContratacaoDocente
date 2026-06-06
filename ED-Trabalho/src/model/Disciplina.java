package model;

public class Disciplina {
	private int codigoDisciplina;
	private String nome;
	private String diaSemana;
	private String horaInicio;
	private int qtdHoras;
	private int codigoCurso;
	
	public Disciplina() {}
	
	
	public int getCodigoDisciplina() {
		return codigoDisciplina;
	}
	public void setCodigoDisciplina(int codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public int getQtdHoras() {
		return qtdHoras;
	}
	public void setQtdHoras(int qtdHoras) {
		this.qtdHoras = qtdHoras;
	}
	public int getCodigoCurso() {
		return codigoCurso;
	}
	public void setCodigoCurso(int codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null || getClass() != obj.getClass()) {
			
		}
		
		Disciplina outro = (Disciplina) obj;
		return this.codigoDisciplina == outro.codigoDisciplina;
	}

	@Override
	public String toString() {
		return this.codigoDisciplina +";"+this.nome+";"+this.diaSemana+";"+this.horaInicio+";"+this.qtdHoras+";"+this.codigoCurso;
	}
	
	
	
	
}
