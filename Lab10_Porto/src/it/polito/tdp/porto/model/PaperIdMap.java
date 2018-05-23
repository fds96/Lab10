package it.polito.tdp.porto.model;

import java.util.*;

public class PaperIdMap {
	
	private Map<Integer,Paper> map;
	
	public PaperIdMap() {
		map = new HashMap<>();
	}
	
	public Paper get(int id) {
		return map.get(id);
	}
	
	public Paper get(Paper paper) {
		Paper old = map.get(paper.getEprintid());
		if(old==null) {
			map.put(paper.getEprintid(), paper);
			return paper;
		}
		return old;
	}
	
	public void put(int id, Paper paper) {
		map.put(id, paper);
	}

}
