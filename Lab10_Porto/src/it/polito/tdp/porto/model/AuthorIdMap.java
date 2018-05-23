package it.polito.tdp.porto.model;

import java.util.*;

public class AuthorIdMap {
	
	private Map<Integer,Author> map;
	
	public AuthorIdMap() {
		map = new HashMap<>();
	}
	
	public Author get(int id) {
		return map.get(id);
	}
	
	public Author get(Author author) {
		Author old = map.get(author.getId());
		if(old==null) {
			map.put(author.getId(), author);
			return author;
		}
		return old;
	}
	
	public void put(int id, Author author) {
		map.put(id, author);
	}

}
