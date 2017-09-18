package com.nttdata.model;

import java.io.Serializable;
import java.util.Date;

public class Noleggio implements Serializable {


	private static final long serialVersionUID = 1L;
	private int idUtente;
	private String idLibro;
	private Date dataPrelievo;
	private Date dataConsegna;
    private int idNoleggio;
    
	
	public int getIdNoleggio() {
		return idNoleggio;
	}


	public void setIdNoleggio(int idNoleggio) {
		this.idNoleggio = idNoleggio;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public String getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(String idLibro) {
		this.idLibro = idLibro;
	}

	public Date getDataPrelievo() {
		return dataPrelievo;
	}

	public void setDataPrelievo(Date dataPrelievo) {
		this.dataPrelievo = dataPrelievo;
	}

	public Date getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(Date dataConsegna) {
		this.dataConsegna = dataConsegna;
	}


	@Override
	public String toString() {
		return "Noleggio [idUtente=" + idUtente + ", idLibro=" + idLibro + ", dataPrelievo=" + dataPrelievo
				+ ", dataConsegna=" + dataConsegna + ", idNoleggio=" + idNoleggio + "]";
	}
	


	
}
