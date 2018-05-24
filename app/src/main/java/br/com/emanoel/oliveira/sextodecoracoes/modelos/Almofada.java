package br.com.emanoel.oliveira.sextodecoracoes.modelos;

/**
 * Created by USUARIO on 07/05/2018.
 */

public class Almofada {

    String nome;
    Double price;
    String description;
    String fotoPath;
    String dataIn;
    String tamanho;
    String tecido;
    String codigo;
    boolean isActive;


    public Almofada() {
    }

    public Almofada(String nome, Double price, String description,
                    String fotoPath, String dataIn, String tamanho,
                    String tecido,String codigo,boolean isActive) {
        this.nome = nome;
        this.price = price;
        this.description = description;
        this.fotoPath = fotoPath;
        this.dataIn = dataIn;
        this.tamanho = tamanho;
        this.tecido = tecido;
        this.codigo = codigo;
        this.isActive = isActive;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFotoPath() {
        return fotoPath;
    }

    public void setFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
    }

    public String getDataIn() {
        return dataIn;
    }

    public void setDataIn(String dataIn) {
        this.dataIn = dataIn;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getTecido() {
        return tecido;
    }

    public void setTecido(String tecido) {
        this.tecido = tecido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean getIsActive() {

        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
