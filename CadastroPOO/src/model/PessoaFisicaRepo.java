package model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class PessoaFisicaRepo {
    Scanner entrada = new Scanner(System.in);
    
    private ArrayList <PessoaFisica> pessoasFisicas= new ArrayList<>();
    
    //métodos
    public void inserir(PessoaFisica pessoaf){
        pessoasFisicas.add(pessoaf);
        pessoaf.exibir();
        System.out.println("Pessoa adicionada com sucesso");
    }
    public void alterar(int id){
        boolean encontrada = false;
        for (PessoaFisica pes : pessoasFisicas){
            if (pes.getId() == id){
                
                encontrada = true;
                System.out.print("Pessoa encontrada: ");
                pes.exibir();
                System.out.println("Insira os novos dados:");
                System.out.print("Id: ");
                int ID = entrada.nextInt();
                System.out.print("Nome: ");
                String nome = entrada.next();
                System.out.print("Idade: ");
                int idade = entrada.nextInt();
                System.out.print("CPF: ");
                String cpf = entrada.next();
                
                pes.setId(ID);
                pes.setNome(nome);
                pes.setIdade(idade);
                pes.setCpf(cpf);
                
                System.out.println("Pessoa Física alterada com sucesso");
                pes.exibir();
            }
        }    
        if (!encontrada){
            System.out.println("Pessoa Física não encontrada");
        }
    }
    public void excluir(int id){
        boolean encontrada = false;
        for (PessoaFisica pes: pessoasFisicas){
            if (pes.getId() == id){
                pessoasFisicas.remove(pessoasFisicas.indexOf(pes));
                System.out.println("Pessoa Física excluída com sucesso");
                encontrada = true;
                break;
            }    
        }
        if (!encontrada){
            System.out.println("Pessoa Física não encontrada");
        }
    }
    public void obter(int id){
        boolean encontrada = false;
        for (PessoaFisica pes: pessoasFisicas){
            if (pes.getId() == id){
                pes.exibir();
                encontrada = true;
                break;
            }    
        }
        if (!encontrada){
            System.out.println("Pessoa Física não encontrada");
        }
    }
    public ArrayList obterTodos(){
        return pessoasFisicas;
    }
    
    public void persistir(String prefixo) throws IOException{
    String arquivoFisica = prefixo + ".fisica.bin";
    try (FileOutputStream salvaFisica = new FileOutputStream(new File(arquivoFisica));
         ObjectOutputStream oosFisica = new ObjectOutputStream(salvaFisica)) {
        
        oosFisica.writeObject(pessoasFisicas);
        
        System.out.println("Dados de Pessoa Física Armazenados em " + arquivoFisica);
    } catch (IOException e) {
        throw new IOException("Erro ao persistir os dados de Pessoa Física: " + e.getMessage());
    }
}

public void recuperar(String prefixo) throws IOException, ClassNotFoundException {
    String arquivoFisica = prefixo + ".fisica.bin";
    try (FileInputStream recuperaFisica = new FileInputStream(arquivoFisica);
         ObjectInputStream oisFisica = new ObjectInputStream(recuperaFisica)) {
        
        pessoasFisicas = (ArrayList<PessoaFisica>) oisFisica.readObject();
        
        System.out.println("Dados de Pessoa Física Recuperados de " + arquivoFisica);
    } catch (IOException | ClassNotFoundException e) {
        throw new IOException("Erro ao recuperar os dados de Pessoa Física: " + e.getMessage());
    }
}
}

