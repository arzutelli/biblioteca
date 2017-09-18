package com.nttdata.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Autore {
	private int idAutore;
	private String nome;
	private String cognome;
	private String email;

	public int getIdAutore() {
		return idAutore;
	}

	public void setIdAutore(int idAutore) {
		this.idAutore = idAutore;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
