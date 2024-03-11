package model;
import java.io.Serializable;

public class PessoaJuridica extends Pessoa implements Serializable{
    //atributos
    String cnpj;
    
    //construtor
    public PessoaJuridica(int id, String nome, String cnpj) {
        super(id, nome);
        this.cnpj = cnpj;
    }
    
    //métodos
    @Override
    public void exibir(){
        System.out.println("ID: " + getId() + ", Nome: " + getNome() + ", CNPJ: " + getCnpj());
    }
    
    //acessores
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
  
}
