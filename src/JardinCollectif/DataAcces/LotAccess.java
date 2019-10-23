package JardinCollectif.DataAcces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LotAccess {

	Connexion conn;

	public LotAccess(Connexion cx) {
		conn = cx;
	}

	public boolean ajouterLot(String nomLot, int noMaxMembre) {
		try {
			PreparedStatement s = conn.getConnection()
					.prepareStatement("insert into lot(nomlot, nomaxmembre) values(?,?)");

			s.setString(1, nomLot);
			s.setInt(2, noMaxMembre);

			s.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean rejoindreLot(int idLot, int noMembre) {
		try {
			PreparedStatement s = conn.getConnection()
					.prepareStatement("insert into membreLot(idLot, noMembre) values(?,?)");

			s.setInt(1, idLot);
			s.setInt(2, noMembre);

			s.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public int getLotid(String nomLot) {
		try {
			PreparedStatement s = conn.getConnection().prepareStatement("SELECT idlot FROM lot WHERE nomlot = ?");

			s.setString(1, nomLot);

			s.execute();
			ResultSet rs = s.getResultSet();
			if(rs.next()) {
				return rs.getInt("idlot");
			}

			return -1;

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}

	}

	public boolean accepterDemande(int idLot, int noMembre) {
		try {
			PreparedStatement s = conn.getConnection()
					.prepareStatement("UPDATE membrelot SET validationAdmin = 'true' WHERE nomembre = ? AND idLot = ?");

			s.setInt(1, noMembre);
			s.setInt(2, idLot);

			s.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean refuserDemande(int idLot, int noMembre) {
		try {
			PreparedStatement s = conn.getConnection()
					.prepareStatement("DELETE FROM membrelot WHERE nomembre = ? AND idLot = ?");

			s.setInt(1, noMembre);
			s.setInt(2, idLot);

			s.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public int getMembreMax(String nomLot) {
		try {
			PreparedStatement s = conn.getConnection().prepareStatement("SELECT nomaxmembre FROM lot WHERE nomlot = ?");

			s.setString(1, nomLot);

			s.execute();
			ResultSet rs = s.getResultSet();
			if (rs.next()) {
				return rs.getInt("nomaxmembre");
			}
			return -1;

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public boolean supprimerLot(String nomLot) {
		try {

			PreparedStatement s = conn.getConnection().prepareStatement("DELETE FROM membrelot WHERE idlot = ?");
			s.setInt(1, getLotid(nomLot));
			s.execute();
			
			s = conn.getConnection().prepareStatement("DELETE FROM lot WHERE nomlot = ?");
			s.setString(1, nomLot);
			s.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public int getPlantsForLot(String nomLot) {
		try {

			PreparedStatement s = conn.getConnection().prepareStatement("SELECT COUNT(*) FROM plantelot WHERE idlot = ?");
			s.setInt(1, getLotid(nomLot));
			s.execute();
			
			ResultSet rs = s.getResultSet();
			if (rs.next()) {
				return rs.getInt("count");
			}
			return -1;

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
