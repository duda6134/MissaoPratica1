package model;
import java.io.Serializable;

public class PessoaFisica extends Pessoa implements Serializable{
    //atributos
    String cpf;
    int idade;
    
    //construtor
    public PessoaFisica(int id, String nome, int idade, String cpf) {
        super(id, nome);
        this.cpf = cpf;
        this.idade = idade;
    }
    
    //métodos
    @Override
    public void exibir(){
        System.out.println("ID: " + getId() + ", Nome: " + getNome() + ", CPF: " + getCpf() + ", Idade: " + getIdade());
    }
    
    //acessores
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
   
}
