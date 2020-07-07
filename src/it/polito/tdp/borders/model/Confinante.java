package it.polito.tdp.borders.model;

public class Confinante {
	private Country stato;
	private int stanziali;
	private int appenaSpostati;
	public Confinante(Country stato, int stanziali, int appenaSpostati) {
		super();
		this.stato = stato;
		this.stanziali = stanziali;
		this.appenaSpostati = appenaSpostati;
	}
	public Country getStato() {
		return stato;
	}
	public void setStato(Country stato) {
		this.stato = stato;
	}
	public int getStanziali() {
		return stanziali;
	}
	public void setStanziali(int stanziali) {
		this.stanziali = stanziali;
	}
	public int getAppenaSpostati() {
		return appenaSpostati;
	}
	public void setAppenaSpostati(int appenaSpostati) {
		this.appenaSpostati = appenaSpostati;
	}
	
	public void aggiungiStanziale(int n) {
		this.stanziali+=n;
	}

}
