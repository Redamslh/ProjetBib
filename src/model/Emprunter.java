package model;


import java.math.BigDecimal;
public class Emprunter  {
	private int NL;
	private String dateEmp;
	private String dateRet;
	private int NA;
	public Emprunter(int NL, String dateEmp, String dateRet, int NA) {
		this.NL=NL;
		this.dateEmp=dateEmp;
		this.dateRet=dateRet;
		this.NA=NA;
	}
	public int getNbrePage() {
		return NL;
	}
	public String getAuteur() {
		return dateEmp;
	}
	public String getId(){
		return dateRet;
	}
	public int gettitre() {
		return NA;
	}
	public void setNbrePage(int nbrePage) {
		 this.NL=NL;
	}
	public void setAuteur(String auteur) {
		 this.dateEmp=dateEmp;
	}
	public String toString() {	
		return ( "le nbre est :"+ getNbrePage()+"\n"+"le auteur est :"+ getAuteur()+"\n");
	}
}
