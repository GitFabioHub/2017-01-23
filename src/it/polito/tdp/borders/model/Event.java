package it.polito.tdp.borders.model;

public class Event implements Comparable<Event>{
	private int time;
	private Confinante confinante;
	public Event(int time, Confinante confinante) {
		super();
		this.time = time;
		this.confinante = confinante;
	}
	public int getTime() {
		return time;
	}
	public Confinante getConfinante() {
		return confinante;
	}
	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return this.time-o.time;
	}
	
	
	
	

}
