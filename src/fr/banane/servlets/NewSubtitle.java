package fr.banane.servlets;

import fr.banane.beans.Langue;
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
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "NewSubtitle")
public class NewSubtitle extends HttpServlet {
    private Traduction traduction;
    private SubtitlesHandler subtitles;
    private static final String PATH = "/WEB-INF/srt_folder/";
    private String name;

    private String[] langues= {"Français", "Anglais", "Allemand", "Italien", "Espagnol", "Mandarin"};
    private List<Langue> listeLangues = new ArrayList<>();

    public void init() {
        //-- Initialisation liste de langues : a remplacer par l'appel au DAO adéquat.
        for (int numLang = 1; numLang < 7; numLang++) {
            listeLangues.add(new Langue(numLang, langues[numLang - 1]));
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //-- ATTENTION, on récupère les textes dans les listes de choix et non les indices.
        String titre = request.getParameter("titre");
        String lang_origin = request.getParameter("lang_origin");
        String lang_trad = request.getParameter("lang_trad");

        System.out.println(titre);  //-- DEBUG
        System.out.println(lang_origin);  //-- DEBUG
        System.out.println(lang_trad);  //-- DEBUG

        //-- Trouver l'id des langues
        Integer id_lang_origin = -1;
        Integer id_lang_trad = -1;
        for (Langue langue: listeLangues) {
            if (lang_origin.equals(langue.getLangue())) {
                id_lang_origin = langue.getId();
            }
            if (lang_trad.equals(langue.getLangue())) {
                id_lang_trad = langue.getId();
            }
        }

        // On récupère le champ du fichier
        Part part = request.getPart("fichier");

        // On vérifie qu'on a bien reçu un fichier
        String nomFichier = getNomFichier(part);

        System.out.println(nomFichier); //-- DEBUG

        // Si on a bien un fichier
        if (nomFichier != null && !nomFichier.isEmpty()) {
            String nomChamp = part.getName();
            // Corrige un bug du fonctionnement d'Internet Explorer
            nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                    .substring(nomFichier.lastIndexOf('\\') + 1);
            ServletContext context = getServletContext();

            // On écrit met le fichier en arraylist

            // On écrit définitivement le fichier sur le disque

            System.out.println(context.getRealPath(PATH + nomFichier));
            subtitles = new SubtitlesHandler();
            subtitles.ecrireFichier(part,context.getRealPath(PATH+nomFichier));

            request.setAttribute(nomChamp, nomFichier);
        }

        ServletContext context = getServletContext();
        // Utiliser l'id fourni dans le data (POST), et verifier qu'il existe dans la DB
        // SELECT COUNT(id_trad) FROM lang_traduction WHERE ID IN ('int(post[origin_trad_id])','int(post[origin_id])')

        traduction = new Traduction(titre, id_lang_origin , id_lang_trad, subtitles.convertStringToBlockTrads(subtitles.readSrt(context.getRealPath(PATH+nomFichier))));
        DaoTraduction daoTraduction = new DaoTraduction(EditeurConnection.getInstance());
        daoTraduction.create(traduction);
        traduction = new Traduction();
        traduction = daoTraduction.find();
        System.out.println(traduction.toString());
        request.setAttribute("trad", daoTraduction.find());

        this.getServletContext().getRequestDispatcher("/WEB-INF/edit_subtitle.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("titre", "Un titre");
        request.setAttribute("fichier", "Un fichier srt");

//        //-- Afficher le contenu de la liste envoyée
//        for (Langue langue : listeLangues) {
//            System.out.println(langue.getId() + " - " + langue.getLangue());
//        }

        //-- Envoyer la liste de langues à la page JSP dès qu'elle s'ouvre à l'écran
        request.setAttribute("listeLangues", listeLangues);

        this.getServletContext().getRequestDispatcher("/WEB-INF/new_subtitle.jsp").forward(request, response);
    }

    private static String getNomFichier(Part part) {
        for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
            if (contentDisposition.trim().startsWith("filename")) {
                return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}