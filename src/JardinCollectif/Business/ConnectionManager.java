package JardinCollectif.Business;

import java.sql.Date;
import java.sql.SQLException;
import java.util.StringTokenizer;

import JardinCollectif.IFT287Exception;
import JardinCollectif.DataAcces.Connexion;
import JardinCollectif.DataAcces.LotManager;
import JardinCollectif.DataAcces.MembreManager;
import JardinCollectif.DataAcces.PlanteManager;
import JardinCollectif.Presentation.JardinCollectif;

public class ConnectionManager {
	private static Connexion cx;
	private JardinCollectif jc = new JardinCollectif();
	private MembreManager mm;
	private LotManager lm;
	private PlanteManager pm;

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
                // Vous devez remplacer la chaine "commande1" et "commande2" par
                // les commandes de votre programme. Vous pouvez ajouter autant
                // de else if que necessaire. Vous n'avez pas a traiter la
                // commande "quitter".
                if (command.equals("inscrireMembre"))
                {
                    // Lecture des parametres
                    String prenom = readString(tokenizer);
                    String nom = readString(tokenizer);
                    String motDePasse = readString(tokenizer);
                    int noMembre = readInt(tokenizer);
                    
                    if(mm == null)
                    	mm = new MembreManager(cx);
                    
                    mm.inscrireMembre(prenom, nom, motDePasse, noMembre);

                }
                else if (command.equals("supprimerMembre"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    // de traitement pour la transaction
                }
                else if (command.equals("promouvoirAdministrateur"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    // de traitement pour la transaction
                }
                else if (command.equals("ajouterLot"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    // de traitement pour la transaction
                }
                else if (command.equals("supprimerLot"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    // de traitement pour la transaction
                }
                else if (command.equals("rejoindreLot"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    // de traitement pour la transaction
                }
                else if (command.equals("accepterDemande"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    // de traitement pour la transaction
                }
                else if (command.equals("refuserDemande"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    // de traitement pour la transaction
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
                    System.out.println(" : Transaction non reconnue");
                }
            }
            
            cx.commit();
        }
        catch (Exception e)
        {
        	jc.AfficherErreur(e.toString());
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


