package com.nttdata.model;

import java.io.Serializable;

public class Noleggio implements Serializable {


	private static final long serialVersionUID = 1L;
	private String idUtente;
	private String idLibro;
	private String dataPrelievo;
	private String dataConsegna;
    private int idNoleggio;
    
	
	public int getIdNoleggio() {
		return idNoleggio;
	}


	public void setIdNoleggio(int idNoleggio) {
		this.idNoleggio = idNoleggio;
	}

	public String getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}

	public String getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(String idLibro) {
		this.idLibro = idLibro;
	}

	public String getDataPrelievo() {
		return dataPrelievo;
	}

	public void setDataPrelievo(String dataPrelievo) {
		this.dataPrelievo = dataPrelievo;
	}

	public String getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(String dataConsegna) {
		this.dataConsegna = dataConsegna;
	}
	

	@Override
	public String toString() {
		return "Noleggio [idUtente=" + idUtente + ", idLibro=" + idLibro + ", dataPrelievo=" + dataPrelievo
				+ ", dataConsegna=" + dataConsegna + ", idNoleggio=" + idNoleggio + "]";
	}
	
}
