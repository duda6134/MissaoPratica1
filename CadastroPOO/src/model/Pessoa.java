package model;
import java.io.Serializable;

public class Pessoa implements Serializable{
    //atributos
    int id;
    String nome;
    
    //construtor
    public Pessoa(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    //métodos
    public void exibir(){
        
    }
    //acessores
    public int getId() {
        return id;
    }
    public void setId(int inteiro) {
        this.id = inteiro;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
  
}
