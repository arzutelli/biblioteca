package com.nttdata.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class Libro implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idLibro;
	private String titolo;
	private String genere;
	private BigDecimal prezzo;
	private String scaffale;
	
	private List<Autore> autore;
	
	


	public List<Autore> getAutore() {
		return autore;
	}

	public void setAutore(List<Autore> autore) {
		this.autore = autore;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genere == null) ? 0 : genere.hashCode());
		result = prime * result + idLibro;
		result = prime * result + ((prezzo == null) ? 0 : prezzo.hashCode());
		result = prime * result + ((scaffale == null) ? 0 : scaffale.hashCode());
		result = prime * result + ((titolo == null) ? 0 : titolo.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (genere == null) {
			if (other.genere != null)
				return false;
		} else if (!genere.equals(other.genere))
			return false;
		if (idLibro != other.idLibro)
			return false;
		if (prezzo == null) {
			if (other.prezzo != null)
				return false;
		} else if (!prezzo.equals(other.prezzo))
			return false;
		if (scaffale == null) {
			if (other.scaffale != null)
				return false;
		} else if (!scaffale.equals(other.scaffale))
			return false;
		if (titolo == null) {
			if (other.titolo != null)
				return false;
		} else if (!titolo.equals(other.titolo))
			return false;
		return true;
	}

}
