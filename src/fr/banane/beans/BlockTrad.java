package fr.banane.beans;

public class BlockTrad {
    private Integer ID_blockTrad, ID_traduction, numLigne;
    private String temps;
    private String ligneOrigine_1;
    private String ligneOrigine_2;
    private String ligneTraduction_1;
    private String ligneTraduction_2;

    public BlockTrad(){
        ID_blockTrad = 0;
        ID_traduction = 0;
        numLigne = 0;
        temps = "";
        ligneOrigine_1 = "";
        ligneOrigine_2 = "";
        ligneTraduction_1 = "";
        ligneTraduction_2 = "";
    }

    public BlockTrad(int numLigne){
        ID_blockTrad = 0;
        ID_traduction = 0;
        this.numLigne = numLigne;
        temps = "";
        ligneOrigine_1 = "";
        ligneOrigine_2 = "";
        ligneTraduction_1 = "";
        ligneTraduction_2 = "";
    }

    public BlockTrad(int numLigne, String temps, String ligneOrigine_1, String ligneOrigine_2){
        ID_blockTrad = 0;
        ID_traduction = 0;
        this.numLigne = numLigne;
        this.temps = temps;
        this.ligneOrigine_1 = ligneOrigine_1;
        this.ligneOrigine_2 = ligneOrigine_2;
        this.ligneTraduction_1 = "";
        this.ligneTraduction_2 = "";
    }

    public BlockTrad(int numLigne, String temps, String ligneOrigine_1, String ligneOrigine_2, String ligneTraduction_1, String ligneTraduction_2){
        ID_blockTrad = 0;
        ID_traduction = 0;
        this.numLigne = numLigne;
        this.temps = temps;
        this.ligneOrigine_1 = ligneOrigine_1;
        this.ligneOrigine_2 = ligneOrigine_2;
        this.ligneTraduction_1 = ligneTraduction_1;
        this.ligneTraduction_2 = ligneTraduction_2;
    }

    public Integer getID_blockTrad() {
        return ID_blockTrad;
    }

    public void setID_blockTrad(Integer ID_blockTrad) {
        this.ID_blockTrad = ID_blockTrad;
    }

    public Integer getID_traduction() {
        return ID_traduction;
    }

    public void setID_traduction(Integer ID_traduction) {
        this.ID_traduction = ID_traduction;
    }

    public Integer getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(Integer numLigne) {
        this.numLigne = numLigne;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public String getLigneOrigine_1() {
        return ligneOrigine_1;
    }

    public void setLigneOrigine_1(String ligneOrigine_1) {
        this.ligneOrigine_1 = ligneOrigine_1;
    }

    public String getLigneOrigine_2() {
        return ligneOrigine_2;
    }

    public void setLigneOrigine_2(String ligneOrigine_2) {
        this.ligneOrigine_2 = ligneOrigine_2;
    }

    public String getLigneTraduction_1() {
        return ligneTraduction_1;
    }

    public void setLigneTraduction_1(String ligneTraduction_1) {
        this.ligneTraduction_1 = ligneTraduction_1;
    }

    public String getLigneTraduction_2() {
        return ligneTraduction_2;
    }

    public void setLigneTraduction_2(String ligneTraduction_2) {
        this.ligneTraduction_2 = ligneTraduction_2;
    }

    public String toString() {
        String toString = "n°" + getNumLigne() + " timer : " + getTemps() + " ligne 1 : " + getLigneOrigine_1() + " ligne 2 : " + getLigneOrigine_2();
        if(!ligneTraduction_1.equals("") && ligneTraduction_2.equals("")){
            String trad = " ligne traduite 1 : " + getLigneTraduction_1();
            toString += trad;
        }
        else if (!ligneTraduction_1.equals("") && !ligneTraduction_2.equals("")){
            String trad = " ligne traduite 1 : " + getLigneTraduction_1() + " ligne traduite 2 : " + getLigneTraduction_2();
        }
        return  toString;
    }
}
