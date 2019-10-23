package JardinCollectif.Business;

import java.sql.Date;
import java.sql.SQLException;
import java.util.StringTokenizer;

import JardinCollectif.IFT287Exception;
import JardinCollectif.JardinCollectif;
import JardinCollectif.DataAcces.Connexion;
import JardinCollectif.DataAcces.LotAccess;
import JardinCollectif.DataAcces.MembreAccess;
import JardinCollectif.DataAcces.PlanteAccess;

public class ConnectionManager {
	private static Connexion cx;
	private JardinCollectif jc = new JardinCollectif();
	private MembreAccess ma;
	private LotAccess la;
	private PlanteAccess pa;

	public void getParams(String serveur, String bd, String user, String pass) throws IFT287Exception, SQLException {
		cx = new Connexion(serveur, bd, user, pass);
		
	}
	
	
    /**
     * Decodage et traitement d'une transaction
     * @throws SQLException 
     */
	public void executeTransaction(String transaction) throws SQLException {
        try
        {
            System.out.print(transaction);
            // Decoupage de la transaction en mots
            StringTokenizer tokenizer = new StringTokenizer(transaction, " ");
            if (tokenizer.hasMoreTokens())
            {
                String command = tokenizer.nextToken();
                
                if (command.equals("inscrireMembre"))
                {
                    // Lecture des parametres
                    String prenom = readString(tokenizer);
                    String nom = readString(tokenizer);
                    String motDePasse = readString(tokenizer);
                    int noMembre = readInt(tokenizer);
                    
                    if(ma == null)
                    	ma = new MembreAccess(cx);
                    
                    ma.inscrireMembre(prenom, nom, motDePasse, noMembre);

                }
                else if (command.equals("supprimerMembre"))
                {
                	int noMembre = readInt(tokenizer);
                	
                	MembreManager mm = new MembreManager(cx);
                	
                	if(ma == null)
                    	ma = new MembreAccess(cx);
                    
                	if(!mm.isOnlyLotMember(noMembre)) {
                		ma.supprimerMembre(noMembre);
                	}
                	else {
                		jc.afficher("erreur, le membre est seul sur un lot");
                	}
                    
                }
                else if (command.equals("promouvoirAdministrateur"))
                {
                	int noMembre = readInt(tokenizer);
                	
                	if(ma == null)
                    	ma = new MembreAccess(cx);
                    
                    ma.makeAdmin(noMembre);
                }
                else if (command.equals("ajouterLot"))
                {
                	String nomLot = readString(tokenizer);
                	int noMaxMembre = readInt(tokenizer);
                	
                	if(la == null)
                    	la = new LotAccess(cx);
                    
                    la.ajouterLot(nomLot, noMaxMembre);
                }
                else if (command.equals("supprimerLot"))
                {
                	String nomLot = readString(tokenizer);
                	
                	if(la == null)
                    	la = new LotAccess(cx);
                	LotManager lm = new LotManager(cx);
                	if(lm.hasPlants(nomLot))
                		jc.afficher("erreur, il y a encore desd plantes non r�colt� dans ce lot");
                	else
                		la.supprimerLot(nomLot);
                }
                else if (command.equals("rejoindreLot"))
                {
                	String nomLot = readString(tokenizer);
                	int noMembre = readInt(tokenizer);
                	
                	if(la == null)
                    	la = new LotAccess(cx);
                    
                    la.rejoindreLot(la.getLotid(nomLot), noMembre);
                }
                else if (command.equals("accepterDemande"))
                {
                	String nomLot = readString(tokenizer);
                	int noMembre = readInt(tokenizer);
                	
                	LotManager lm = new LotManager(cx);
                	
                	if(la == null)
                    	la = new LotAccess(cx);
                    
                	if(lm.hasSpace(nomLot)) {
                		la.accepterDemande(la.getLotid(nomLot), noMembre);
                	}
                	else {
                		jc.afficher("erreur, plus d'espace dans le lot");
                	}
                	
                    
                }
                else if (command.equals("refuserDemande"))
                {
                	String nomLot = readString(tokenizer);
                	int noMembre = readInt(tokenizer);
                	
                	if(la == null)
                    	la = new LotAccess(cx);
                    
                    la.refuserDemande(la.getLotid(nomLot), noMembre);
                }
                else if (command.equals("ajouterPlante"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    // de traitement pour la transaction
                }
                else if (command.equals("retirerPlante"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    // de traitement pour la transaction
                }
                else if (command.equals("planterPlante"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    // de traitement pour la transaction
                }
                else if (command.equals("recolterPlante"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    // de traitement pour la transaction
                }
                else if (command.equals("afficherMembres"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    // de traitement pour la transaction
                }
                else if (command.equals("afficherPlantes"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    // de traitement pour la transaction
                }
                else if (command.equals("afficherLots"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    // de traitement pour la transaction
                }
                else if (command.equals("afficherPlantesLot"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    // de traitement pour la transaction
                }
                else
                {
                    jc.afficher(" : Transaction non reconnue");
                }
            }
            
            cx.commit();
        }
        catch (Exception e)
        {
        	jc.afficher(e.toString());
            // Ce rollback est ici seulement pour vous aider et éviter des problèmes lors de la correction
            // automatique. En théorie, il ne sert à rien et ne devrait pas apparaître ici dans un programme
            // fini et fonctionnel sans bogues.
            cx.rollback();
        }
		
	}
	
    /** Lecture d'une chaine de caracteres de la transaction entree a l'ecran */
    static String readString(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
            return tokenizer.nextToken();
        else
            throw new Exception("Autre parametre attendu");
    }

    /**
     * Lecture d'un int java de la transaction entree a l'ecran
     */
    static int readInt(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Integer.valueOf(token).intValue();
            }
            catch (NumberFormatException e)
            {
                throw new Exception("Nombre attendu a la place de \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }

    static Date readDate(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Date.valueOf(token);
            }
            catch (IllegalArgumentException e)
            {
                throw new Exception("Date dans un format invalide - \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }


	public void fermer() throws SQLException {
		if (cx != null) {
			cx.fermer();
		}
		
	}

}


