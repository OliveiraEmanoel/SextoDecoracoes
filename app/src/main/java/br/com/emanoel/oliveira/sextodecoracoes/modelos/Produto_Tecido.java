package br.com.emanoel.oliveira.sextodecoracoes.modelos;

/**
 * Created by Emanoel de Oliveira on 21/01/2018.
 */

public class Produto_Tecido {

    private String nomeEstampa;
    private String description;
    private double price;
    private int qdade;
    private double raportHorizontal;
    private double raportVertical;
    private String largura;
    private String origem;
    public boolean selected;

    public Produto_Tecido(String nomeEstampa, String description, double price, int qdade, double raportHorizontal,
                          double raportVertical, String largura, String origem, boolean selected) {
        this.nomeEstampa = nomeEstampa;
        this.description = description;
        this.price = price;
        this.qdade = qdade;
        this.raportHorizontal = raportHorizontal;
        this.raportVertical = raportVertical;
        this.largura = largura;
        this.origem = origem;
        this.selected = selected;
    }

    public Produto_Tecido() {
    }

    public Produto_Tecido(String nomeEstampa, double price, int qdade, boolean selected) {
        this.nomeEstampa = nomeEstampa;
        this.price = price;
        this.qdade = qdade;
        this.selected = selected;
    }

    public String getNomeEstampa() {
        return nomeEstampa;
    }

    public void setNomeEstampa(String nomeEstampa) {
        this.nomeEstampa = nomeEstampa;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQdade() {
        return qdade;
    }

    public void setQdade(int qdade) {
        this.qdade = qdade;
    }

    public double getRaportHorizontal() {
        return raportHorizontal;
    }

    public void setRaportHorizontal(double raportHorizontal) {
        this.raportHorizontal = raportHorizontal;
    }

    public double getRaportVertical() {
        return raportVertical;
    }

    public void setRaportVertical(double raportVertical) {
        this.raportVertical = raportVertical;
    }

    public String getLargura() {
        return largura;
    }

    public void setLargura(String largura) {
        this.largura = largura;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
