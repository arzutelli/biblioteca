package com.nttdata.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Telefoni implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idCell;
	private int idUtente;
	private String numero;
	private String tipo;
	
	
		public int getIdCell() {
		return idCell;
	}

	public void setIdCell(int idCell) {
		this.idCell = idCell;
	}
	
	
	public int getIdUtente() {
		return idUtente;
	}
	
	public void setIdUtente(int idUtente) {
		this.idUtente=idUtente;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
	
	

