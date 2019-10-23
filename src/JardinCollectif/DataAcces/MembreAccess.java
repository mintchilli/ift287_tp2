package JardinCollectif.DataAcces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	public ArrayList<Integer> getMembreLots(int noMembre) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		try {
			PreparedStatement s = conn.getConnection()
					.prepareStatement("SELECT idlot FROM membrelot WHERE nomembre = ?");

			s.setInt(1, noMembre);

			s.execute();
			ResultSet rs = s.getResultSet();

			while (rs.next()) {
				ret.add(rs.getInt("idlot"));
			}

			return ret;

		} catch (SQLException e) {
			e.printStackTrace();
			return ret;
		}

	}

	public int getMembreCount(int idLot) {
		try {
			PreparedStatement s = conn.getConnection()
					.prepareStatement("SELECT count(*) FROM membrelot WHERE idlot = ?");

			s.setInt(1, idLot);

			s.execute();
			ResultSet rs = s.getResultSet();

			rs.next();

			return rs.getInt("count");

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
