package model;


public class Prispevek {
    private int id;
    private User uzivatel;
    private String text;

    public Prispevek(int id, User uzivatel, String text) {
        this.id = id;
        this.uzivatel = uzivatel;
        this.text = text;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(User uzivatel) {
        this.uzivatel = uzivatel;
    }
    
    
}
