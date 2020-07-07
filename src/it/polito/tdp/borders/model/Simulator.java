package it.polito.tdp.borders.model;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;



public class Simulator {
	
	private int T;
	private final int nMigranti=1000;
	private Graph<Country,DefaultEdge>grafo;
	private Map<Country,Confinante>confinanti;
	private Confinante partenza;
	
	private PriorityQueue<Event>queue;
	
	public Simulator(Graph<Country,DefaultEdge> grafo) {
		this.grafo=grafo;
		
		
		
	}
	public void init(Country partenza) {
		for(Country c:this.grafo.vertexSet()) {
			
			confinanti.put(c, new Confinante(c,0,0));
			
		}
		confinanti.get(partenza).aggiungiStanziale(500);
		T=1;
		List<Country>neib=Graphs.neighborListOf(grafo, partenza);
		for(Country c:neib) {
			confinanti.get(c).setAppenaSpostati(500/neib.size());
			queue.add(new Event(T+1,confinanti.get(c)));
		}
		confinanti.get(partenza).aggiungiStanziale(500%neib.size());
		
		
	}
	public void run() {
		while (!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			processEvent(e);
		}
	}
	private void processEvent(Event e) {
		T=e.getTime();
		int spostati=e.getConfinante().getAppenaSpostati();
		int spostanti=spostati-spostati/2;
		e.getConfinante().aggiungiStanziale(spostati/2);
		List<Country>neib=Graphs.neighborListOf(grafo, e.getConfinante().getStato());
		if(spostanti>=neib.size()) {
			for(Country c:neib) {
				confinanti.get(c).setAppenaSpostati(spostanti/neib.size());
				queue.add(new Event(e.getTime()+1,confinanti.get(c)));
				
			}
		
			e.getConfinante().aggiungiStanziale(spostanti%neib.size());
			
		}else e.getConfinante().aggiungiStanziale(spostanti);
		
		
		
	}
	
	
	

}
