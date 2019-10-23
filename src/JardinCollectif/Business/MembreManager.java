package JardinCollectif.Business;

import java.util.ArrayList;
import java.util.Map;

import JardinCollectif.DataAcces.Connexion;
import JardinCollectif.DataAcces.MembreAccess;

public class MembreManager {
	
	Connexion conn;

	public MembreManager(Connexion cx) {
		conn = cx;
	}

	public boolean isOnlyLotMember(int noMembre) {
		
		//get la liste des lot ou le membre est
		
		MembreAccess ma = new MembreAccess(conn);
		ArrayList<Integer> lst = ma.getMembreLots(noMembre);
		
		//verifie pour chaque lot si le membre est le seul
		for (int idLot : lst) {
			if(ma.getMembreCount(idLot) <= 1)
				return true;
		}
		
		return false;
	}

	public ArrayList<Map<String,Boolean>> getMembre() {
		// TODO Auto-generated method stub
		return null;
	}

}
