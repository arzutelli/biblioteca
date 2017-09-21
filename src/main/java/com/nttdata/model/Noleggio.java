package com.nttdata.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Noleggio implements Serializable {


	private static final long serialVersionUID = 1L;
	private int idUtente;
	private String idLibro;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="CET")
	private Date dataPrelievo;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="CET")
	private Date dataConsegna;
	
    private int idNoleggio;
    private boolean ritardo;
 	
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

	
	
	public boolean isRitardo() {
		return ritardo;
	}


	public void setRitardo(boolean ritardo) {
		this.ritardo = ritardo;
	}


	public void setDataPrelievo(Date dataPrelievo) {
		this.dataPrelievo=dataPrelievo;
	}

	public Date getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(Date dataConsegna) {
		
		this.dataConsegna=dataConsegna;

	}	


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	


	
}
