package com.nttdata.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AutoreLibro {
	private int idAutore;
	private int idLibro;

	public int getIdAutore() {
		return idAutore;
	}

	public void setIdAutore(int idAutore) {
		this.idAutore = idAutore;
	}

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
