package model;


import java.math.BigDecimal;
public class Oeuvres  {
	private int NO;
	private String titre;
	private String auteur;
	private int nbrePage;
	public Oeuvres(int NO, String titre, String auteur, int nbrePage) {
		this.NO=NO;
		this.titre=titre;
		this.auteur=auteur;
		this.nbrePage=nbrePage;
	}
	public int getNbrePage() {
		return nbrePage;
	}
	public String getAuteur() {
		return auteur;
	}
	public int getId(){
		return NO;
	}
	public String gettitre() {
		return titre;
	}
	public void setNbrePage(int nbrePage) {
		 this.nbrePage=nbrePage;
	}
	public void setAuteur(String auteur) {
		 this.auteur=auteur;
	}
	public String toString() {	
		return ( "le nbre est :"+ getNbrePage()+"\n"+"le auteur est :"+ getAuteur()+"\n");
	}
}
