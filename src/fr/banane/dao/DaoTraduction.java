package fr.banane.dao;

import fr.banane.beans.BlockTrad;
import fr.banane.beans.Traduction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoTraduction extends Dao<Traduction>{
    public DaoTraduction(Connection conn) {
        super(conn);
    }

    @Override
    public boolean create(Traduction obj) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = EditeurConnection.getInstance()
                    .prepareStatement("INSERT INTO Traduction(titre, ID_langueOrigine, ID_langueTraduite) VALUES(?, ?, ?);");

            preparedStatement.setString(1, obj.getTitre());
            preparedStatement.setInt(2, obj.getLangueOrigine());
            preparedStatement.setInt(3, obj.getLangueTraduction());
            preparedStatement.executeUpdate();

            ResultSet result = null;
                result = EditeurConnection.getInstance().createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                        .executeQuery("SELECT MAX(id_traduction) as id_traduction FROM Traduction;");

            int id_traduction = -1;
                if(result.first()) {
                   id_traduction = result.getInt("id_traduction");
                }

                for (int i = 0; i<obj.getBlockTrads().size(); i++){
                    obj.getBlockTrads().get(i).setID_traduction(id_traduction);
                }

                DaoBlockTrad daoBlockTrad = new DaoBlockTrad(this.connect);
                for (int i = 0; i<obj.getBlockTrads().size(); i++) {
                    daoBlockTrad.create(obj.getBlockTrads().get(i));
                }

            } catch (
                    SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public boolean delete(Traduction obj) {
            return false;
        }

        @Override
        public boolean update(Traduction obj) {
                DaoBlockTrad daoBlockTrad = new DaoBlockTrad(this.connect);
                for (int i = 0; i<obj.getBlockTrads().size(); i++) {
                    daoBlockTrad.update(obj.getBlockTrads().get(i));
                }
        return false;
        }

    public Traduction find() {
        Traduction traduction = new Traduction();
        PreparedStatement preparedStatement = null;
        BlockTrad blockTrad = new BlockTrad();

        try {
            // Exécution de la requête
            ResultSet result = null;
            result = EditeurConnection.getInstance().createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery("SELECT MAX(id_traduction) as id_traduction FROM Traduction;");

            int id_traduction = -1;
            if(result.first()) {
                id_traduction = result.getInt("id_traduction");
                // Récupération des données
                traduction = find(id_traduction);
            }

        } catch (SQLException e)
        { e.printStackTrace(); }

        return traduction;
    }

        @Override
        public Traduction find(int id_traduction) {
            Traduction traduction = new Traduction();
            PreparedStatement preparedStatement = null;
            BlockTrad blockTrad = new BlockTrad();

            try {
                // Exécution de la requête
                preparedStatement = EditeurConnection.getInstance().prepareStatement("SELECT * FROM Traduction WHERE id_traduction = ? ;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                preparedStatement.setInt(1, id_traduction);
                preparedStatement.executeQuery();
                if(preparedStatement.getResultSet().first()) {

                    // Récupération des données
                    traduction.setId(id_traduction);
                    traduction.setTitre(preparedStatement.getResultSet().getString("titre"));
                    traduction.setLangueOrigine(preparedStatement.getResultSet().getInt("id_langueorigine"));
                    traduction.setLangueTraduction(preparedStatement.getResultSet().getInt("id_languetraduite"));
                    DaoBlockTrad daoBlockTrad = new DaoBlockTrad(this.connect);
                    traduction.setBlockTrads(daoBlockTrad.lister(id_traduction));
                }


            } catch (SQLException e)
            { e.printStackTrace(); }
            finally {
                // Fermeture de la connexion
                try {
                    if (preparedStatement != null)
                        preparedStatement.close();
                } catch (SQLException ignore) {
                }
            }
            return traduction;
        }

        @Override
        public ArrayList<Traduction> lister(Integer typeNull) {
        //Juste pour leiste les traductions sans charger les blocktrads
          ArrayList<Traduction> traductions = new ArrayList<>();
          ResultSet result = null;
          try {
              result = EditeurConnection.getInstance().createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                      .executeQuery("SELECT * FROM Traduction;");

              while (result.next()) {
                  Traduction traduction = new Traduction();
                  traduction.setId(result.getInt("id_traduction"));
                  traduction.setTitre(result.getString("titre"));
                  traduction.setLangueOrigine(result.getInt("id_langueorigine"));
                  traduction.setLangueTraduction(result.getInt("id_languetraduite"));
                  traductions.add(traduction);
              }
          }catch (SQLException e){
              e.printStackTrace();
          }



        return traductions;
        }
    }

