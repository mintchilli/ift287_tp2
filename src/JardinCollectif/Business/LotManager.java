package JardinCollectif.Business;

import JardinCollectif.DataAcces.Connexion;
import JardinCollectif.DataAcces.LotAccess;
import JardinCollectif.DataAcces.MembreAccess;

public class LotManager {

	Connexion conn;
	
	public LotManager(Connexion cx) {
		conn = cx;
	}

	public boolean hasSpace(String nomLot) {
		
		MembreAccess ma = new MembreAccess(conn);
		LotAccess la = new LotAccess(conn);
		//get membremax
		
		int max = la.getMembreMax(nomLot);
		
		//get membreCount
		int  count = ma.getMembreCount(la.getLotid(nomLot));
		
		if(count++ > max)
			return false;
		else
			return true;
	}
	
	public boolean hasPlants(String nomLot) {
		
		LotAccess la = new LotAccess(conn);
		//get membremax
		
		int count = la.getPlantsForLot(nomLot);
		
		
		if(count!= 0)
			return true;
		else
			return false;
	}

}
