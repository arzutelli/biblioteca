package com.nttdata.model;

import java.io.Serializable;

public class Indirizzi implements Serializable{
	
	private String badgeId;
	private String via;
	private String citta;
	private String provincia;
	private String cap;

	public String getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(String badgeId) {
		this.badgeId = badgeId;
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
		return "Indirizzi [badgeId=" + badgeId + ", via=" + via + ", citta=" + citta + ", provincia=" + provincia
				+ ", cap=" + cap + "]";
	}
	
}
