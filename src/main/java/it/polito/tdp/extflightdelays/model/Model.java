package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph<Airport, DefaultWeightedEdge> grafo;
	private Map<Integer, Airport> idMapAereoporti;
	private List<CoppiaVoli> voli; //Nome ingannevole non sono Voli ma Aereoporti
	private ExtFlightDelaysDAO dao;
	
	public Model() {
		this.idMapAereoporti = new HashMap<>();
		this.dao = new ExtFlightDelaysDAO();
	}

	//DUBBIO
	//Io creo un grafo non orientato e considero la coppia A-B e anche B-A.. ma nel grafo devono comparire entrambe
	//oppure no? Come ho fatto io compaiono entrambe come se fossero due cose separate, e' giusto?
	public void creaGrafo(int mediaMinima) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		dao.loadAllAirports(idMapAereoporti); //Gli ho passato una mappa vuota e me l'ha riempita
		
		Graphs.addAllVertices(this.grafo, this.idMapAereoporti.values()); //Ho messo tutti gli archi nel grafo
		
		this.voli = dao.coppieVoli();
		for(CoppiaVoli cv : this.voli) {
			if(cv.getPeso()>mediaMinima) {
				Graphs.addEdge(this.grafo, this.idMapAereoporti.get(cv.getVolo1()), this.idMapAereoporti.get(cv.getVolo2()),
						cv.getPeso());
			}
		}
	}
	
	public int numVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int numArchi() {
		return this.grafo.edgeSet().size();
	}
	
	//Ok fa quello che deve ma e' brutto af.. dove cambio il toString del grafo e delle sue componenti??
	public String archi() {
		String s = "";
		
		for(DefaultWeightedEdge e : this.grafo.edgeSet()) {
			s += e.toString()+" Distanza media: "+this.grafo.getEdgeWeight(e)+"\n";
		}
		return s;
	}
}
