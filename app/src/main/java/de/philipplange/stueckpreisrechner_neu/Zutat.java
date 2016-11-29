package de.philipplange.stueckpreisrechner_neu;

public class Zutat {

    private String name;
    private double menge;
    private Einheit einheit;
    private double preis;

    public Zutat(String name, double menge, Einheit einheit, double preis) {
        this.name = name;
        this.menge = menge;
        this.einheit = einheit;
        this.preis = preis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMenge() {
        return menge;
    }

    public void setMenge(float menge) {
        this.menge = menge;
    }

    public Einheit getEinheit() {
        return einheit;
    }

    public void setEinheit(Einheit einheit) {
        this.einheit = einheit;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        this.preis = preis;
    }

    @Override
    public String toString() {
        return "Zutat{" +
                   name +
                ", " + menge +
                " " + einheit.toString().toLowerCase() +
                " zu " + preis +
                "€ }";
    }
}