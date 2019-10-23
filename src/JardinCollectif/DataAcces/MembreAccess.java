package JardinCollectif.DataAcces;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MembreAccess {

	Connexion conn;

	public MembreAccess(Connexion cx) {
		conn = cx;
	}

	public boolean inscrireMembre(String prenom, String nom, String motDePasse, int noMembre) {
		try {
			PreparedStatement s = conn.getConnection()
					.prepareStatement("insert into Membre(noMembre, nom, prenom, motDePasse) values(?,?,?,?)");
			s.setInt(1, noMembre);
			s.setString(2, nom);
			s.setString(3, prenom);
			s.setString(4, motDePasse);

			s.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean supprimerMembre(int noMembre) {
		try {
			PreparedStatement s = conn.getConnection().prepareStatement("delete from Membre where nomembre = ?");
			s.setInt(1, noMembre);
			s.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean makeAdmin(int noMembre) {
		try {
			PreparedStatement s = conn.getConnection()
					.prepareStatement("UPDATE membre SET isadmin = 'true' WHERE nomembre = ?");
			s.setInt(1, noMembre);
			s.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}
