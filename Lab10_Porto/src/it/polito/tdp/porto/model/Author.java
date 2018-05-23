package it.polito.tdp.porto.model;

import java.util.*;

public class Author implements Comparable<Author>{

	private int id;
	private String lastname;
	private String firstname;
	private Map<Integer,Paper> papers;
		
	public Author(int id, String lastname, String firstname) {
		super();
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.papers = new HashMap<>();
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public List<Paper> getPapers() {
		return new ArrayList<>(papers.values());
	}

	public void setPapers(List<Paper> papers) {
		for(Paper p : papers)
			this.papers.put(p.getEprintid(), p);
	}
	
	public void addPaper(Paper paper) {
		papers.put(paper.getEprintid(), paper);
	}

	@Override
	public String toString() {
		//return "Author [id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + "]";
		return this.lastname + " " + this.firstname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(Author other) {
		return this.lastname.compareTo(other.getLastname());
	}
	
	
}
