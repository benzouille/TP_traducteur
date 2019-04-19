package fr.banane.dao;

import fr.banane.beans.BlockTrad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoBlockTrad extends Dao<BlockTrad> {

    public DaoBlockTrad(Connection conn) {
        super(conn);
    }

    @Override
    public boolean create(BlockTrad obj) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = EditeurConnection.getInstance().prepareStatement("INSERT INTO BlockTrad(id_traduction, numLigne, temps, ligneorigine_1, ligneorigine_2, lignetraduite_1, lignetraduite_2) VALUES(?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, obj.getID_traduction());
            preparedStatement.setInt(2, obj.getNumLigne());
            preparedStatement.setString(3, obj.getTemps());
            preparedStatement.setString(4, obj.getLigneOrigine_1());
            preparedStatement.setString(5, obj.getLigneOrigine_2());
            preparedStatement.setString(6, obj.getLigneTraduction_1());
            preparedStatement.setString(7, obj.getLigneTraduction_2());

            preparedStatement.executeUpdate();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(BlockTrad obj) {
        return false;
    }

    @Override
    public boolean update(BlockTrad obj) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = EditeurConnection.getInstance().
                    prepareStatement("UPDATE BlockTrad SET ligneTraduite_1 = ?, ligneTraduite_2 = ? WHERE ID_BlockTrad = ?;");

            preparedStatement.setString(1, obj.getLigneTraduction_1());
            preparedStatement.setString(2, obj.getLigneTraduction_2());
            preparedStatement.setInt(3, obj.getID_blockTrad());

            preparedStatement.executeUpdate();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public BlockTrad find(int id_blockTrad) {
        PreparedStatement preparedStatement = null;
        BlockTrad blockTrad = new BlockTrad();

        try {
            // Exécution de la requête
            preparedStatement = EditeurConnection.getInstance().prepareStatement("SELECT * FROM BlockTrad WHERE ID_blockTrad = "+id_blockTrad+";");
            // Récupération des données
            blockTrad.setID_blockTrad(id_blockTrad);
            blockTrad.setID_traduction(preparedStatement.getResultSet().getInt("id_traduction"));
            blockTrad.setNumLigne(preparedStatement.getResultSet().getInt("numLigne"));
            blockTrad.setTemps(preparedStatement.getResultSet().getString("temps"));
            blockTrad.setLigneOrigine_1(preparedStatement.getResultSet().getString("ligneOrigine_1"));
            blockTrad.setLigneOrigine_2(preparedStatement.getResultSet().getString("ligneOrigine_2"));
            blockTrad.setLigneTraduction_1(preparedStatement.getResultSet().getString("ligneTraduite_1"));
            blockTrad.setLigneTraduction_2(preparedStatement.getResultSet().getString("ligneTraduite_2"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blockTrad;
    }

    @Override
    public ArrayList<BlockTrad> lister(Integer id_traduction) {
        ArrayList<BlockTrad> blockTrads = new ArrayList<>();
        ResultSet result = null;

        try {
            // Exécution de la requête
            result = EditeurConnection.getInstance().createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM BlockTrad WHERE ID_traduction = "+id_traduction+"ORDER BY id_blocktrad;");

            // Récupération des données
            while (result.next()) {
                BlockTrad blockTrad = new BlockTrad();
                blockTrad.setID_blockTrad(result.getInt("id_blocktrad"));
                blockTrad.setID_traduction(id_traduction);
                blockTrad.setNumLigne(result.getInt("numligne"));
                blockTrad.setTemps(result.getString("temps"));
                blockTrad.setLigneOrigine_1(result.getString("ligneorigine_1"));
                blockTrad.setLigneOrigine_2(result.getString("ligneorigine_2"));
                blockTrad.setLigneTraduction_1(result.getString("lignetraduite_1"));
                blockTrad.setLigneTraduction_2(result.getString("lignetraduite_2"));

                blockTrads.add(blockTrad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blockTrads;
    }
}
