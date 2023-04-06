package controller;

import view.BibliothequeVue;

public class Main {
    public static void main(String[] args) {
        BibliothequeVue vue = new BibliothequeVue();

        while (true) {
            vue.afficherMenu();
            int choix = vue.getChoixUtilisateur();
            vue.traiterChoix(choix);
        }
    }
}
