package Model;

import Db.Database;
import java.sql.*;

public class Animal{
    private int id;
    private String Localizacao;
    private String Nome;
    private String Estado;
    private Blob imagem;
    private String Raca;
    private String Especie; 

    public User(int id, String localizacao, String nome, String estado, Blob imagem, String raca, String especie) {
        this.id = id;
        this.localizacao = localizacao;
        this.nome = nome;
        this.estado = estado; 
        this.imagem = imagem;
        this.raca = raca;
        this.especie = especie;
    }

    // Getters e setters
    public int getId() 
    { return id; }
    public void setId(int id)
    { this.id = id; }

    public String getLocalizacao()
    { return localizacao; }
    public void setLocalizacao(String localizacao)
    { this.localizacao = localizacao; }

    public String getNome() 
    { return nome; }
    public void setNome(String nome) 
    { this.nome = nome; }

    public String getEstado()
    { return estado; } 
    public void setEstado(String estado)
    { this.estado = estado;}

    public Blob getImagem() 
    { return imagem; }
    public void setImagem(Blob imagem)
    { this.imagem = imagem; }

    public String getRaca()
    { return raca; }
    public void setRaca(String raca)
    { this.raca = raca;}

    public String getEspecie()
    { return especie;}
    public void setEspecie(String especie)
    |{ this.especie = especie; }

  

    

    // Sobrescrevendo toString para exibir dados do usuário
    @Override
    public String toString() {
        return "ID: " + id + ", Localizacao: " + localizacao + ", Nome: " + nome + ", Estado: " +  estado + ", Imagem: " + imagem + ", Raca: " + raca + ", Especie:" + especie; 
    }
}
