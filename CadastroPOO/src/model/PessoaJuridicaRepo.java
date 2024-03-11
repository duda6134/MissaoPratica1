package model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class PessoaJuridicaRepo {
    Scanner entrada = new Scanner(System.in);
    
    private ArrayList <PessoaJuridica> pessoasJuridicas = new ArrayList<>();
    
    //métodos
    public void inserir(PessoaJuridica pessoaj){
        pessoasJuridicas.add(pessoaj);
        pessoaj.exibir();
        System.out.println("Pessoa adicionada com sucesso");
    }
    public void alterar(int id){
        boolean encontrada = false;
        for (PessoaJuridica pes : pessoasJuridicas){
            if (pes.getId() == id){
                
                encontrada = true;
                System.out.print("Pessoa encontrada: ");
                pes.exibir();
                System.out.println("Insira os novos dados:");
                System.out.print("Id: ");
                int ID = entrada.nextInt();
                System.out.print("Nome: ");
                String nome = entrada.next();
                System.out.print("CNPJ: ");
                String cnpj = entrada.next();
                
                pes.setId(ID);
                pes.setNome(nome);
                pes.setCnpj(cnpj);
                
                System.out.println("Pessoa Jurídica alterada com sucesso");
                pes.exibir();
            }
        }    
        if (!encontrada){
            System.out.println("Pessoa Jurídica não encontrada");
        }
    }
    public void excluir(int id){
        boolean encontrada = false;
        for (PessoaJuridica pes: pessoasJuridicas){
            if (pes.getId() == id){
                pessoasJuridicas.remove(pessoasJuridicas.indexOf(pes));
                System.out.println("Pessoa Jurídica excluída com sucesso");
                encontrada = true;
                break;
            }    
        }
        if (!encontrada){
            System.out.println("Pessoa Jurídica não encontrada");
        }
    }

    public void obter(int id){
        boolean encontrada = false;
        for (PessoaJuridica pes: pessoasJuridicas){
            if (pes.getId() == id){
                pes.exibir();
                encontrada = true;
                break;
            }    
        }
        if (!encontrada){
            System.out.println("Pessoa Jurídica não encontrada");
        }
    }
    public ArrayList obterTodos(){
        return pessoasJuridicas;
    }
    
    public void persistir(String prefixo) throws IOException {
    String arquivoJuridica = prefixo + ".juridica.bin";
    try (FileOutputStream salvaJuridica = new FileOutputStream(new File(arquivoJuridica));
         ObjectOutputStream oosJuridica = new ObjectOutputStream(salvaJuridica)) {
        
        oosJuridica.writeObject(pessoasJuridicas);
        
        System.out.println("Dados de Pessoa Jurídica Armazenados em " + arquivoJuridica);
    } catch (IOException e) {
        throw new IOException("Erro ao persistir os dados de Pessoa Física: " + e.getMessage());
    }
}

public void recuperar(String prefixo) throws IOException, ClassNotFoundException {
        String arquivoJuridica = prefixo + ".juridica.bin";
        try (FileInputStream recuperaJuridica = new FileInputStream(arquivoJuridica);
             ObjectInputStream oisJuridica = new ObjectInputStream(recuperaJuridica)) {

            pessoasJuridicas = (ArrayList<PessoaJuridica>) oisJuridica.readObject();

            System.out.println("Dados de Pessoa Jurídica Recuperados de " + arquivoJuridica);
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("Erro ao recuperar os dados de Pessoa Física: " + e.getMessage());
        }
    }
}
