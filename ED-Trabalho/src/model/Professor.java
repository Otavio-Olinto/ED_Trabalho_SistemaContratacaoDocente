package model;

public class Professor {
	
	public String cpf;

	public String nome;

	public String area;

	public int pontos;

	public Professor() {
	}

	public Professor(String cpf, String nome, String area, int pontos) {

		this.cpf = cpf;
		this.nome = nome;
		this.area = area;
		this.pontos = pontos;
	}

	public String getCpf() {

		return cpf;

	}

	public void setCpf(String cpf) {

		this.cpf = cpf;

	}

	public String getNome() {

		return nome;

	}

	public void setNome(String nome) {

		this.nome = nome;

	}

	public String getArea() {

		return area;

	}

	public void setArea(String area) {

		this.area = area;

	}

	public int getPontos() {

		return pontos;

	}

	public void setPontos(int pontos) {

		this.pontos = pontos;

	}

	@Override
	public String toString() {

		return cpf + ";" +
			   nome + ";" +
			   area + ";" +
			   pontos;

	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)

			return true;

		if (obj == null || getClass() != obj.getClass())

			return false;

		Professor outro = (Professor) obj;

		return this.cpf.equals(outro.cpf);

	}
}

