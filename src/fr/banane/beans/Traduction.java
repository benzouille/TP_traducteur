package fr.banane.beans;

import java.util.ArrayList;

public class Traduction {
    private Integer id;
    private String titre;
    // Stocker display string + ID dans les langues
    private int langueOrigine;
    private int langueTraduction;
    private ArrayList<BlockTrad> blockTrads;

    public Traduction(){
        id = 0;
        titre = "";
        langueOrigine = -1;
        langueTraduction = -1;
        blockTrads = new ArrayList<>();
    }

    public Traduction(String titre, int langueOrigine, int langueTraduction, ArrayList<BlockTrad> blockTrads){
        this.titre = titre;
        this.langueOrigine = langueOrigine;
        this.langueTraduction = langueTraduction;
        this.blockTrads = blockTrads;
    }

    public String toString(){
        String str ="objet traduction, id = " + id + ", titre = " + titre + ", langue origine = " + langueOrigine + ", langue traduite = " + langueTraduction;
        return str;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getLangueOrigine() {
        return langueOrigine;
    }

    public void setLangueOrigine(int langueOrigine) {
        this.langueOrigine = langueOrigine;
    }

    public int getLangueTraduction() {
        return langueTraduction;
    }

    public void setLangueTraduction(int langueTraduction) {
        this.langueTraduction = langueTraduction;
    }

    public ArrayList<BlockTrad> getBlockTrads() {
        return blockTrads;
    }

    public void setBlockTrads(ArrayList<BlockTrad> blockTrads) {
        this.blockTrads = blockTrads;
    }

    public void addBlockTrad(BlockTrad blockTrad){
        this.blockTrads.add(blockTrad);
    }
}
