package org.example;

import java.util.ArrayList;
import java.util.List;

public class Bibliotheque {
    private List<Livre> livres;

    public Bibliotheque() {
        livres = new ArrayList<>();
    }

    public void ajouterLivre(Livre livre) {
        livres.add(livre);
    }

    public Livre rechercherLivre(String titre) {
        for (Livre livre : livres) {
            if (livre.getTitre().equalsIgnoreCase(titre)) {
                return livre;
            }
        }
        return null;
    }

    public List<Livre> listerTousLesLivres() {
        return new ArrayList<>(livres);
    }
}
