package model;

public class Curso {

	public int codigo;
	
	public String nome;
	
	public String area;

	public Curso() {
	}

	public Curso(int codigo, String nome, String area) {
		this.codigo = codigo;
		this.nome = nome;
		this.area = area;
	}

	public int getCodigo() {

		return codigo;

	}

	public void setCodigo(int codigo) {

		this.codigo = codigo;

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

	@Override
	public String toString() {

		return codigo + ";" + nome + ";" + area;

	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)

			return true;

		if (obj == null || getClass() != obj.getClass())

			return false;

		Curso outro = (Curso) obj;

		return this.codigo == outro.codigo;

	}

}
