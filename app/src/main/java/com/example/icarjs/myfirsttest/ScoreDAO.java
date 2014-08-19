package com.example.icarjs.myfirsttest;

/**
 * Created by imie on 19/08/14.
 */
public class ScoreDAO {
    public static final String TABLE_NAME = "Score";
    public static final String KEY = "id";
    public static final String INTITULE = "nom";
    public static final String SALAIRE = "score";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + INTITULE + " TEXT, " + SALAIRE + " REAL);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";


    public void ajouter(String nom, Integer score) {


      //  INSERT INTO Score (nom_de_la_colonne1, nom_de_la_colonne2, …) VALUES (valeur1, valeur2, …)
        // CODE
    }

    public void supprimer(Integer id) {
        // CODE
    }

    public void modifier(Integer id) {
        // CODE
    }

    public Integer selectionner(Integer id) {
        Integer score = 0;
        // CODE
        return score;
    }
}
