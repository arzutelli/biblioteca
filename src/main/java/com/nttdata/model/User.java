package com.nttdata.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private String email;
	private int badgeId;

	private Date dataNascita;

	private int eta;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(int badgeId) {
		this.badgeId = badgeId;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

}
