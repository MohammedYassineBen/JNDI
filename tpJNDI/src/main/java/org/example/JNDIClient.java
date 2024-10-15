package org.example;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class JNDIClient {
    public static void main(String[] args) {
        try {
            // Configuration du contexte JNDI
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
            env.put(Context.PROVIDER_URL, "rmi://localhost:1099");

            // Création du contexte initial
            Context context = new InitialContext(env);

            // pour rechercher la bibliothèque dans JNDI
            Bibliotheque bibliotheque = (Bibliotheque) context.lookup("bibliotheque");

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\nMenu :");
                System.out.println("1. Ajouter un livre");
                System.out.println("2. Rechercher un livre");
                System.out.println("3. Lister tous les livres");
                System.out.println("4. Quitter");
                System.out.print("Choix : ");
                int choix = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choix) {
                    case 1:
                        System.out.print("Titre : ");
                        String titre = scanner.nextLine();
                        System.out.print("Auteur : ");
                        String auteur = scanner.nextLine();
                        bibliotheque.ajouterLivre(new Livre(titre, auteur));
                        System.out.println("Livre ajouté avec succès.");
                        break;
                    case 2:
                        System.out.print("Titre du livre à rechercher : ");
                        titre = scanner.nextLine();
                        Livre livre = bibliotheque.rechercherLivre(titre);
                        if (livre != null) {
                            System.out.println("Livre trouvé : " + livre);
                        } else {
                            System.out.println("Livre non trouvé.");
                        }
                        break;
                    case 3:
                        List<Livre> livres = bibliotheque.listerTousLesLivres();
                        System.out.println("Liste des livres :");
                        for (Livre l : livres) {
                            System.out.println(l);
                        }
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Choix invalide.");
                        break;
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
