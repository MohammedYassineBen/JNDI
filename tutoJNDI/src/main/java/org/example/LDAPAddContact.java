package org.example;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;

public class LDAPAddContact {

    public static void main(String[] args) {
        // Configuration de l’environnement JNDI pour LDAP
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10389"); // Port par défaut d'ApacheDS
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system"); // DN de l’utilisateur admin
        env.put(Context.SECURITY_CREDENTIALS, "secret"); // Mot de passe

        try {
            // Création du contexte initial
            DirContext ctx = new InitialDirContext(env);
            System.out.println("Connexion au serveur LDAP réussie.");

            // Définir le DN de la nouvelle entrée
            String dn = "uid=mcourtois,ou=contacts,dc=business,dc=com";

            // Préparer les attributs de l’entrée
            Attributes attrs = new BasicAttributes(true); // Insensible à la casse
            Attribute objClass = new BasicAttribute("objectClass");
            objClass.add("inetOrgPerson");
            attrs.put(objClass);
            attrs.put("cn", "Marc Courtois"); // Common Name
            attrs.put("sn", "Courtois");     // Surname
            attrs.put("uid", "mcourtois");   // User ID
            attrs.put("mail", "mcourtois@business.com"); // Email
            attrs.put("department", "Marketing"); // Département

            // Ajouter l’entrée au répertoire
            ctx.createSubcontext(dn, attrs);
            System.out.println("Entrée ajoutée avec succès : " + dn);

            // Fermeture du contexte
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
