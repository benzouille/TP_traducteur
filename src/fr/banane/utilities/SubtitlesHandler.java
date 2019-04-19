package fr.banane.utilities;

import fr.banane.beans.BlockTrad;

import javax.servlet.http.Part;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SubtitlesHandler {
    private ArrayList<String> originalSubtitles = null;
    private ArrayList<String> translatedSubtitles = null;
    private Part part = null;
    private ArrayList<BlockTrad> blockTrads = new ArrayList<>();
    public static final int TAILLE_TAMPON = 10240;

    public SubtitlesHandler(){}

    public ArrayList<String > readSrt(String contextPathName) {
        originalSubtitles = new ArrayList<String>();

        BufferedReader br;
        try {
            System.out.println("le path plus le titre de readSrt : " + contextPathName);
            br = new BufferedReader(new InputStreamReader(new FileInputStream(contextPathName), StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                originalSubtitles.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return originalSubtitles;
    }

    public ArrayList<BlockTrad> convertStringToBlockTrads (ArrayList<String> originalSubtitles){
        int num = -1;
        String temps = "";
        String ligneOrigine_1 = "";
        String ligneOrigine_2 = "";

        for(int i =1; i<originalSubtitles.size(); i++) {
            if (originalSubtitles.get(i).matches("^[0-9]+$")) {
                num = Integer.parseInt(originalSubtitles.get(i));
            }
            else if (originalSubtitles.get(i).matches("[0-9]{2}:[0-9]{2}:[0-9]{2},[0-9]{3} --> [0-9]{2}:[0-9]{2}:[0-9]{2},[0-9]{3}")) {
                temps = originalSubtitles.get(i);
            }
            else if (originalSubtitles.get(i).matches("[^0-9]+$")){
                if(ligneOrigine_1.equals("")) {
                    ligneOrigine_1 = originalSubtitles.get(i);
                }
                else {
                    ligneOrigine_2 = originalSubtitles.get(i);
                }
            }
            else if (originalSubtitles.get(i).matches("^\\s*$")){
                BlockTrad blockTrad = new BlockTrad(num, temps, ligneOrigine_1, ligneOrigine_2);
                blockTrads.add(blockTrad);
                //TODO à virer après test
                System.out.println(blockTrad.toString());
                num = -1;
                temps = "";
                ligneOrigine_1 = "";
                ligneOrigine_2 = "";
            }
            else{
                //TODO à virer après test
                System.out.println("!!!! problem !!!!");
            }
        }
        return blockTrads;
    }

    public void ecrireFichier( Part part,String contextPathName) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            System.out.println("le path plus le titre de ecrireFichier : " + contextPathName);
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(contextPathName)), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }

    public void SubtitleWriter(String contextPath, String fileName){

    }

    public ArrayList<String> getSubtitles() {
        return originalSubtitles;
    }
}

