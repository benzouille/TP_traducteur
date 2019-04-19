package fr.banane.servlets;

import fr.banane.beans.BlockTrad;
import fr.banane.beans.Traduction;
import fr.banane.dao.DaoTraduction;
import fr.banane.dao.EditeurConnection;
import fr.banane.utilities.SubtitlesHandler;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "EditSubtitle")
public class EditSubtitle extends HttpServlet {

    private Traduction traduction;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer id_traduction = -1;
        ArrayList<Traduction> traductions = new ArrayList<>();
        DaoTraduction daoTraduction = new DaoTraduction(EditeurConnection.getInstance());

        //Recuperation de l'id de la traduction pour ensuite la recuperer dans la bdd le bouton ok
        if(request.getParameter("_ok_") != null) {
            traductions = daoTraduction.lister(null);
            id_traduction = Integer.parseInt(request.getParameter("id_traduction"));
            request.setAttribute("traductions", traductions);

        }
        else if (request.getParameter("_enregistrer_") != null){
            String trad = request.getParameter("trad");
            System.out.println("c'est le request : "+trad);
            System.out.println(request.getAttributeNames().toString());
//            for(String attribut : request.getAttributeNames()){
//                System.out.println(attribut);
//            }
            //daoTraduction.update(traduction);
        }

        traduction = new Traduction();
        traduction = daoTraduction.find(id_traduction);
        System.out.println("EditSubtitle ligne 46" + traduction.toString());
        request.setAttribute("trad", daoTraduction.find(id_traduction));

        this.getServletContext().getRequestDispatcher("/WEB-INF/edit_subtitle.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //recuperation des titres des differente traductions.
        ArrayList<Traduction> traductions = new ArrayList<>();
        DaoTraduction daoTraduction = new DaoTraduction(EditeurConnection.getInstance());
        traductions = daoTraduction.lister(null);
        for (int i = 0; i<traductions.size(); i++) {
            System.out.println(traductions.get(i).toString());
        }

        request.setAttribute("traductions", traductions);

        this.getServletContext().getRequestDispatcher("/WEB-INF/edit_subtitle.jsp").forward(request, response);
    }
}
