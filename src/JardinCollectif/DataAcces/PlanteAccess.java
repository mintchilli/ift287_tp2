package JardinCollectif.DataAcces;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class PlanteAccess {

	Connexion conn;

	public PlanteAccess(Connexion cx) {
		conn = cx;
	}
	
	public boolean ajouterPlante(String nomPlante, int tempsDeCulture) {
		try {
			PreparedStatement s = conn.getConnection()
					.prepareStatement("insert into Plante(nomPlante, tempsDeCulture) values(?,?)");
			s.setString(1, nomPlante);
			s.setInt(2, tempsDeCulture);

			s.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean retirerPlante(String nomPlante) {
		try {
			PreparedStatement s = conn.getConnection()
					.prepareStatement("delete from Plante where nomPlante = ?");
			s.setString(1, nomPlante);

			s.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean planterPlante(int idLot, int idPlante, Date datePlantation, int nbExemplaires, int noMembre) {
		try {
			PreparedStatement s = conn.getConnection()
					.prepareStatement("insert into PlanteLot(idLot, idPlante, datePlantation, nbExemplaires, noMembre) values(?,?,?,?,?)");
			s.setInt(1, idLot);
			s.setInt(2, idPlante);
			s.setDate(3, datePlantation);
			s.setInt(4, nbExemplaires);
			s.setInt(5, noMembre);

			s.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean recolterPlante(String nomPlante, String nomLot, int noMembre) {
		try {
			PreparedStatement s = conn.getConnection()
					.prepareStatement("delete from PlanteLot where noMembre = ? and nomLot = ? and nomPlante = ?");
			s.setInt(1, noMembre);
			s.setString(2, nomLot);
			s.setString(3, nomPlante);

			s.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean afficherPlantes() {
		
		return false;
	}
	
	public boolean afficherPlantesLot() {
		
		return false;
	}
}
