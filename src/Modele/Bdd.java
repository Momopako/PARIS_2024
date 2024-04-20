package Modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bdd {
	private String serveur, bdd, user, mdp;
	
	private Connection maConnexion;

	public Bdd(String serveur, String bdd, String user, String mdp) {
		this.serveur = serveur;
		this.bdd = "jo_paris";
		this.user = "Pako";
		this.mdp = "26092001MPk";
		this.maConnexion = null;
	}
	
	
	public void chargerPilote() {
		// JDBC : Java DataBase Connector
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException exp) {
			System.out.println("Erreur de chargement du pilote JDBC");
		}
	}
	
	
	public void seConnecter() {
		this.chargerPilote();
		String url = "jdbc:mysql://localhost:3306/jo_paris";
		try {
			this.maConnexion = DriverManager.getConnection(url, "Pako", "26092001MPk");
		} catch (SQLException exp) {
			System.out.println("Erreur de connexion à : " + url);
		}
	}

	
	public void seDeconnecter() {
	 try {
		 if (this.maConnexion != null) {
			 this.maConnexion.close();
		 }
	 } catch(SQLException exp) {
		 System.out.println("Erreur de fermeture de la connexion");
	 	}
	}
	
	
	public Connection getMaConnexion() {
		return this.maConnexion;
	}
	
}
