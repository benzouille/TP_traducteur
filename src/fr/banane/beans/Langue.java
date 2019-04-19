package fr.banane.beans;

public class Langue {
    private Integer id;
    private String langue;

    public Langue() {}

    public Langue(String langue) {
        this.langue = langue;
    }

    public Langue(Integer id, String langue) {
        this.id = id;
        this.langue = langue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }
}
