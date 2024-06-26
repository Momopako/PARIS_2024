package Modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Controleur.Evenement;

public class ModeleEvenement {

	private static Bdd uneBdd = new Bdd("localhost", "jo_paris", "Pako", "26092001MPk");

	public static void insertEvenement(Evenement unEvenement) {
		String req = "INSERT INTO evenement VALUES(NULL, '"
				+ unEvenement.getType() + "', '"
				+ unEvenement.getDateEvent() + "', '"
				+ unEvenement.getNomEvenement()+ "', '"
				+ unEvenement.getDescription() +"', '"
				+ unEvenement.getAdresse() + "', '"
				+ unEvenement.getHorraireD() + "', '"
				+ unEvenement.getHorraireF() + "', '"
				+ unEvenement.getCapacite() + "', '"
				+ unEvenement.getIdcategorie() + "');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(req);
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException e) {
			System.out.println("Erreur d'execution de : " + req);
		}
	}

	
	public static ArrayList<Evenement> selectAllEvenements() {
		String req = "SELECT * FROM evenement;";
		ArrayList<Evenement> lesEvenements = new ArrayList<Evenement>();
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(req);
			
			while(desResultats.next()) {
				Evenement unEvent = new Evenement(
						desResultats.getInt("idevenement"),
						desResultats.getString("type"),
						desResultats.getString("dateEvent"),
						desResultats.getString("nomEvenement"),
						desResultats.getString("description"),
						desResultats.getString("adresse"),
						desResultats.getString("horraireD"),
						desResultats.getString("horraireF"),
						desResultats.getInt("capacite"),
						desResultats.getInt("idcategorie")
						);
				lesEvenements.add(unEvent);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException e) {
			System.out.println("Erreur d'execution de la requete : " + req);
			System.out.println(e.getMessage());
		}
		return lesEvenements;
	}
}
