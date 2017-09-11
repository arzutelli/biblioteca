package com.nttdata.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Libro implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idLibro;
	private String titolo;
	private String genere;
	private BigDecimal prezzo;
	private String scaffale;

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public BigDecimal getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public String getScaffale() {
		return scaffale;
	}

	public void setScaffale(String scaffale) {
		this.scaffale = scaffale;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
