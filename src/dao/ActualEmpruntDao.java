package dao;

import java.util.*;

import model.Emprunter;

import java.sql.*;
import java.io.*;
import java.math.BigDecimal;
/**
 * 
 * @author www.luv2code.com
 *
 */
public class ActualEmpruntDao {

	private Connection myConn;
	String name,pass,url; 
	public ActualEmpruntDao() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		name="root";  
        pass="";  
		url ="jdbc:mysql://localhost:3306/biblio?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		myConn = DriverManager.getConnection(url, name, pass);
		
		System.out.println("DB connection successful to: " + url);
	}
	
	public List<Emprunter> getAllLivres() throws Exception {
		List<Emprunter> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("\r\n" + 
					"\r\n" + 
					"select emprunter.NL,emprunter.dateEmp,emprunter.dateRet,emprunter.NA from emprunter ");
			
			while (myRs.next()) {
				Emprunter tempLivres = convertRowToLivres(myRs);
				list.add(tempLivres);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Emprunter> searchLivres(String titre) throws Exception {
		List<Emprunter> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			titre += "%";
			myStmt = myConn.prepareStatement("select * from oeuvres where  titre like ?");
			
			myStmt.setString(1, titre);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Emprunter tempLivres = convertRowToLivres(myRs);
				list.add(tempLivres);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Emprunter convertRowToLivres(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("NL");
		String titre = myRs.getString("dateEmp");
		String auteur = myRs.getString("dateRet");
		int nbrePage = myRs.getInt("NA");
		
		Emprunter tempLivres = new Emprunter(id, titre, auteur, nbrePage);
		
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
