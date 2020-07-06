package it.polito.tdp.borders.model;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private BordersDAO dao;
	private Graph<Country,DefaultEdge>grafo;
	public Model() {
		this.dao=new BordersDAO();
		
	}
	public void creaGrafo(int anno) {
		
		grafo=new SimpleGraph<Country,DefaultEdge>(DefaultEdge.class);
		
		for(Adiacenza  a: dao.getAdiacenze(anno)) {
			
			if(!grafo.containsVertex(a.getC1())) {
				grafo.addVertex(a.getC1());
			}else if(!grafo.containsVertex(a.getC2())) {
				grafo.addVertex(a.getC2());
			}
			if(!grafo.containsEdge(a.getC1(), a.getC2())) {
				grafo.addEdge(a.getC1(), a.getC2());
			}
			
		}
	}
	
	public int numeroVertici() {
		return this.grafo.vertexSet().size();
	}
	public int numeroArchi() {
		return this.grafo.edgeSet().size();
	}
	public Graph<Country,DefaultEdge>getGrafo(){
		return this.grafo;
	}
	
	
	
	
	

}
