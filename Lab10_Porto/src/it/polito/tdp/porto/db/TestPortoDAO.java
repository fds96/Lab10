package it.polito.tdp.porto.db;

import it.polito.tdp.porto.model.AuthorIdMap;
import it.polito.tdp.porto.model.PaperIdMap;

public class TestPortoDAO {
	
	public static void main(String args[]) {
		PortoDAO pd = new PortoDAO();
//		System.out.println(pd.getAutore(85));
//		System.out.println(pd.getArticolo(2293546));
//		System.out.println(pd.getArticolo(1941144));
		
		AuthorIdMap authorIdMap = new AuthorIdMap();
		PaperIdMap paperIdMap = new PaperIdMap();
		
		System.out.println(pd.getAllAuthors(authorIdMap).size());
		System.out.println(pd.getAllArticolo(paperIdMap).size());
		System.out.println(pd.associatePaperToAuthor(authorIdMap, paperIdMap));
	}

}
