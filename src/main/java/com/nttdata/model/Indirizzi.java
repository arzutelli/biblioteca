package com.nttdata.model;

import java.io.Serializable;

public class Indirizzi implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idUtente;
	private int idIndirizzi;
	private String via;
	private String citta;
	private String provincia;
	private String cap;

	public int getIdIndirizzi() {
		return idIndirizzi;
	}

	public void setIdIndirizzi(int idIndirizzi ) {
		this.idIndirizzi = idIndirizzi;
	}
	
	public int getIdUtente() {
		return idUtente;
	}
	
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCitta() {
		return citta;
	}
	
	public void setCitta(String citta) {
		this.citta = citta;
	}
		public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Indirizzi [idUtente=" + idUtente + ", idIndirizzi=" + idIndirizzi + ", via=" + via + ", citta=" + citta
				+ ", provincia=" + provincia + ", cap=" + cap + "]";
	}


	
}
