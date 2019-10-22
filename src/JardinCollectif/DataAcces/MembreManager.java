package JardinCollectif.DataAcces;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MembreManager {
	
	Connexion conn;

	public MembreManager(Connexion cx) {
		conn = cx;
	}

	public boolean inscrireMembre(String prenom, String nom, String motDePasse, int noMembre) {
		try {
			PreparedStatement s = conn.getConnection().prepareStatement(
					"insert into Membre(noMembre, nom, prenom, motDePasse) values(?,?,?,?)");
			s.setInt(1, noMembre);
			s.setString(2, nom);
			s.setString(3, prenom);
			s.setString(4, motDePasse);
			
			s.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

}
