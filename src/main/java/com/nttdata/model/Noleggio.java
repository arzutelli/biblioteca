package com.nttdata.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nttdata.exception.ResourceConflictException;

public class Noleggio implements Serializable {


	private static final long serialVersionUID = 1L;
	private int idUtente;
	private String idLibro;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="CET")
	private Date dataPrelievo;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="CET")
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

		Calendar cPrelievo = Calendar.getInstance();	
		cPrelievo.setTime(dataPrelievo);
		Calendar now = Calendar.getInstance();

		if(cPrelievo.get(Calendar.YEAR) == now.get(Calendar.YEAR)&&cPrelievo.get(Calendar.MONTH) == now.get(Calendar.MONTH)
				&& cPrelievo.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH))
			this.dataPrelievo = dataPrelievo;
		else
			throw new ResourceConflictException("LA DATA INSERITA NON CORRISPONDE ALLA DATA ATTUALE");

			
	}

	public Date getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(Date dataConsegna) {
		
		this.dataConsegna=dataConsegna;

	}	


	@Override
	public String toString() {
		return "Noleggio [idUtente=" + idUtente + ", idLibro=" + idLibro + ", dataPrelievo=" + dataPrelievo
				+ ", dataConsegna=" + dataConsegna + ", idNoleggio=" + idNoleggio + "]";
	}
	


	
}
