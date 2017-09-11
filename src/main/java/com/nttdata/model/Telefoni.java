package com.nttdata.model;

import java.io.Serializable;

public class Telefoni implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idCell;
	private String numero;
	private String tipo;
	
	public int getIdCell() {
		return idCell;
	}
	
	public void setIdCell(int idCell) {
		this.idCell=idCell;
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

	public String toString() {
		return "Telefoni [numero=" + numero + ", tipo=" + tipo + "]";
	}
	
	
	
	
	

}
