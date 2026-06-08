package model;

public class Processo {

	public int codigoProcesso;
	public String nomeDisciplina;
	public String status;

	public Processo() {
	}

	public Processo(int codigo, String nomeDisciplina, String status) {
		this.codigoProcesso = codigo;
		this.nomeDisciplina = nomeDisciplina;
		this.status = status;
	}

	public int getCodigoProcesso() {
		return codigoProcesso;
	}

	public void setCodigoProcesso(int codigo) {
		this.codigoProcesso = codigo;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return codigoProcesso + ";" + nomeDisciplina + ";" + status;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		Processo outro = (Processo) obj;
		return this.codigoProcesso == outro.codigoProcesso && 
				this.nomeDisciplina.equals(outro.nomeDisciplina);

	}
}
