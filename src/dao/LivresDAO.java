package dao;

import java.util.*;

import model.Oeuvres;

import java.sql.*;
import java.io.*;
import java.math.BigDecimal;
/**
 * 
 * @author www.luv2code.com
 *
 */
public class LivresDAO {

	private Connection myConn;
	String name,pass,url; 
	public LivresDAO() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		name="root";  
        pass="";  
		url ="jdbc:mysql://localhost:3306/bib?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		myConn = DriverManager.getConnection(url, name, pass);
		
		System.out.println("DB connection successful to: " + url);
	}
	
	public List<Oeuvres> getAllLivres() throws Exception {
		List<Oeuvres> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from Livres");
			
			while (myRs.next()) {
				Oeuvres tempLivres = convertRowToLivres(myRs);
				list.add(tempLivres);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Oeuvres> searchLivres(String titre) throws Exception {
		List<Oeuvres> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			titre += "%";
			myStmt = myConn.prepareStatement("select * from Livres where  titre like ?");
			
			myStmt.setString(1, titre);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Oeuvres tempLivres = convertRowToLivres(myRs);
				list.add(tempLivres);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Oeuvres convertRowToLivres(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("NO");
		String titre = myRs.getString("titre");
		String auteur = myRs.getString("auteur");
		int nbrePage = myRs.getInt("nbrePage");
		
		Oeuvres tempLivres = new Oeuvres(id, titre, auteur, nbrePage);
		
		return tempLivres;
	}

	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	public static void main(String[] args) throws Exception {
		
		LivresDAO dao = new LivresDAO();
		System.out.println(dao.searchLivres("JAVA"));

		System.out.println(dao.getAllLivres());
	}
}
