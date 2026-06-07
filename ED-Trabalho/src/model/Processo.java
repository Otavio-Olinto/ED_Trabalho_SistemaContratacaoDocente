package model;

public class Processo {

	public int codigoProcesso;
	public int codigoDisciplina;
	public String status;

	public Processo() {
	}

	public Processo(int codigo, int codigoDisciplina, String status) {
		this.codigoProcesso = codigo;
		this.codigoDisciplina = codigoDisciplina;
		this.status = status;
	}

	public int getCodigoProcesso() {
		return codigoProcesso;
	}

	public void setCodigoProcesso(int codigo) {
		this.codigoProcesso = codigo;
	}

	public int getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(int codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return codigoProcesso + ";" + codigoDisciplina + ";" + status;
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
		return this.codigoProcesso == outro.codigoProcesso;

	}
}
