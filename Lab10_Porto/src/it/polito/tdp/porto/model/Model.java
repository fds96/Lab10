package it.polito.tdp.porto.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;


import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	
	private PortoDAO dao;
	
	private PaperIdMap paperIdMap;
	private AuthorIdMap authorIdMap;
	
	private List<Paper> papers;
	private List<Author> authors;
	
	private Graph<Author,DefaultEdge> graph;
	
	public Model() {
		
		dao = new PortoDAO();
		
		paperIdMap = new PaperIdMap();
		authorIdMap = new AuthorIdMap();
		
		papers = dao.getAllArticolo(paperIdMap);
		authors = dao.getAllAuthors(authorIdMap);
		
		dao.associatePaperToAuthor(authorIdMap, paperIdMap);
		
		graph = new SimpleGraph<>(DefaultEdge.class);
		
		this.initialize();
	}

	private void initialize() {
		Graphs.addAllVertices(this.graph, authors);
		
		//Ciclo su tutti i papers ed associo gli autori che li hanno creati.
		
		for(Paper p : papers) {
	
			for(int i=0;i<p.getAuthors().size();i++) {
				
				for(int j=i+1;j<p.getAuthors().size();j++) {
					
					graph.addEdge(p.getAuthors().get(i), p.getAuthors().get(j));
					
				}
			}
		}
		
		//System.out.println(graph.vertexSet().size() +  " " + graph.edgeSet().size());
	}
	
	public void findConnectedSet() {
		
		ConnectivityInspector<Author,DefaultEdge> ci = new ConnectivityInspector<>(graph);
		for(Set<Author> s : ci.connectedSets())
			System.out.println(s);
	}
	
//public Set<Airport> getBiggestSCC() {
//		
//		ConnectivityInspector<Airport, DefaultWeightedEdge> ci = new ConnectivityInspector(grafo);
//		
//		Set<Airport> bestSet = null;
//		int bestSize = 0;
//		
//		for (Set<Airport> s : ci.connectedSets()) {
//			if (s.size() > bestSize) {
//				bestSet = new HashSet<Airport>(s);
//				bestSize = s.size();
//			}	
//		}
//		
//		return bestSet;
//}
	
	private List<Author> getShorestPath(Author autore1,Author autore2){
		
		
		DijkstraShortestPath<Author,DefaultEdge> spa = new DijkstraShortestPath<Author,DefaultEdge>(this.graph);
		
		return new ArrayList<>(spa.getPath(autore1, autore2).getVertexList());
	}
	
	public List<Paper> papersShortestPath(int id1, int id2){
		Author autore1 = authorIdMap.get(id1);
		Author autore2 = authorIdMap.get(id2);
		
		if(autore1 == null || autore2 == null) {
			return null;
		}
		
		List<Author> path = this.getShorestPath(autore1, autore2);
		
		List<Paper> result = new ArrayList<>();
		
		if(path!=null) {
			for(int i=0;i<path.size()-1;i++) {
				for(Paper p : papers) {
					if(p.getAuthors().contains(path.get(i)) && p.getAuthors().contains(path.get(i+1))) {
						result.add(p);
						break;
					}
				}
			}
		}
		
		return result;
	}
	
}
