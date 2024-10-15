package org.example;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class JNDIServer {
    public static void main(String[] args) {
        try {

            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
            env.put(Context.PROVIDER_URL, "rmi://localhost:1099");

            Context context = new InitialContext(env);


            Bibliotheque bibliotheque = new Bibliotheque();


            context.bind("bibliotheque", bibliotheque);

            System.out.println("Serveur JNDI prêt. Bibliothèque liée.");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
