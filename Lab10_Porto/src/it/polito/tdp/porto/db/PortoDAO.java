package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.AuthorIdMap;
import it.polito.tdp.porto.model.Paper;
import it.polito.tdp.porto.model.PaperIdMap;

public class PortoDAO {

	/*
	 * Dato l'id ottengo l'autore.
	 */
	
	public List<Author> getAllAuthors(AuthorIdMap authorIdMap){
		
		final String sql = "SELECT * FROM author";
		
		List<Author> authors = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				authors.add(authorIdMap.get(autore));
			}
			
			conn.close();

			return authors;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public List<Paper> getAllArticolo(PaperIdMap paperIdMap) {

		final String sql = "SELECT * FROM paper";
		
		List<Paper> papers = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				papers.add(paperIdMap.get(paper));
			}

			conn.close();
			
			return papers;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public int associatePaperToAuthor(AuthorIdMap authorIdMap, PaperIdMap paperIdMap) {

		final String sql = "SELECT * FROM creator";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			

			ResultSet rs = st.executeQuery();
			Author author;
			Paper paper;
			
			int contatore = 0;
			
			while (rs.next()) {
				
				author=authorIdMap.get(rs.getInt("authorid"));
				paper=paperIdMap.get(rs.getInt("eprintid"));
				
				author.addPaper(paper);
				paper.addAuthor(author);
				
				contatore++;
				
			}
			
			conn.close();

			return contatore;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	
	public Author getAutore(int id) {

		final String sql = "SELECT * FROM author where id=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				return autore;
			}

			conn.close();
			
			return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Paper getArticolo(int eprintid) {

		final String sql = "SELECT * FROM paper where eprintid=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				return paper;
			}

			conn.close();
			
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
}