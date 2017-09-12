package com.nttdata.model;

import java.io.Serializable;

public class Noleggio implements Serializable {

	

	private String badgeId;
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

	public String getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(String badgeId) {
		this.badgeId = badgeId;
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
		return "Noleggio [badgeId=" + badgeId + ", idLibro=" + idLibro + ", dataPrelievo=" + dataPrelievo
				+ ", dataConsegna=" + dataConsegna + ", idNoleggio=" + idNoleggio + "]";
	}
	
}
