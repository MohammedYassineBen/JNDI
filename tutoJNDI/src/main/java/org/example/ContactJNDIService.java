package org.example;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import java.util.Hashtable;

public class ContactJNDIService {
    public static void main(String[] args) {
        // Configuration de l’environnement JNDI
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
        env.put(Context.PROVIDER_URL, "file:///tmp/contact_jndi");

        try {
            // Création du contexte initial
            Context ctx = new InitialContext(env);

            // Création d’un objet Contact
            Contact contact = new Contact("C001", "Marie Curie", "marie.curie@example.com", "Recherche");

            // Liaison de l’objet Contact dans le contexte
            String name = "contact/C001";
            ctx.bind(name, contact);
            System.out.println("Contact lié avec succès : " + name);

            // Recherche de l’objet Contact dans le contexte
            Contact retrievedContact = (Contact) ctx.lookup(name);
            System.out.println("Contact récupéré : " + retrievedContact);

            // Liste des contacts dans le contexte
            System.out.println("Liste des contacts dans le contexte :");
            for (NamingEnumeration<NameClassPair> enumeration = ctx.list("contact"); enumeration.hasMore(); ) {
                NameClassPair ncp = enumeration.next();
                System.out.println(ncp.getName() + " : " + ncp.getClassName());
            }

            // Fermeture du contexte
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}

class Contact {
    private String id;
    private String name;
    private String email;
    private String department;

    public Contact(String id, String name, String email, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
    }

    // Getters et Setters (ajoutez si nécessaire)

    @Override
    public String toString() {
        return "ID: " + id + ", Nom: " + name + ", Email: " + email + ", Département: " + department;
    }
}
