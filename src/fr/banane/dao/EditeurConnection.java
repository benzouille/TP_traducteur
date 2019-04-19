package fr.banane.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EditeurConnection {
    //URL de connexion
    private static String url = "jdbc:postgresql://localhost:5432/Editeur_traduction";
    //Nom du user
    private static String user = "postgres";
    //Mot de passe de l'utilisateur
    private static String passwd = "";
    //Objet Connection
    private static Connection connect;

    //Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
    public static Connection getInstance(){

        if(connect == null){
            try {
                Class.forName("org.postgresql.Driver");
                connect = DriverManager.getConnection(url, user, passwd);
                System.out.println("INSTANCIATION DE LA CONNEXION SQL ! ");
            } catch ( ClassNotFoundException e) {
                System.out.println("ATTENTION : Driver POSTGRESQL non trouvé !");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }
        else{
            System.out.println("CONNEXION SQL EXISTANTE ! ");
        }
        return connect;
    }
}
