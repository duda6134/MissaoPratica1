package model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {  
    public static String f_ou_j(){
                Scanner entrada = new Scanner(System.in);
                System.out.println("Insira o tipo de pessoa:");
                String x = entrada.nextLine();
                x.toLowerCase();
                return x;
            }
    
    public static void main(String args[]) {
        
        //instânciação de repositórios
        PessoaFisicaRepo repoF = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJ = new PessoaJuridicaRepo();
       
        boolean controle = true;
        while (controle){
            
            //menu
            System.out.println("=======================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo ID");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("=======================================");
            System.out.print("Escolha uma opção: ");
            
            //instância da classe Scanner (possibilita ao usuário digitar dados)
            Scanner entrada = new Scanner(System.in);
            int op = entrada.nextInt();

            //escolha o menu
            switch(op){
                case 1: //incluir
                    System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
                    String pes = entrada.next();
                    if (pes.toLowerCase().equals("f")){ //insira dados da pessoa Física
                        System.out.println("Digite o ID da pessoa:");
                        int id = entrada.nextInt();
                        System.out.println("Digite o nome da pessoa:");
                        String nome = entrada.next();
                        System.out.println("Digite a idade da pessoa:");
                        int idade = entrada.nextInt();
                        System.out.println("Digite o cpf da pessoa(XXX.XXX.XXX-XX):");
                        String cpf = entrada.next();

                        //adiciona pessoa ao repositório
                        repoF.inserir(new PessoaFisica(id, nome, idade, cpf));

                    }else if (pes.toLowerCase().equals("j")){ //insira dados de pessoa Jurídica
                        System.out.println("Digite o ID da pessoa:");
                        int id = entrada.nextInt();
                        System.out.println("Digite o nome da pessoa:");
                        String nome = entrada.next();
                        System.out.println("Digite o cnpj da pessoa(XX.XXX.XXX/YYYY-XX):");
                        String cnpj = entrada.next();

                        //adiciona pessoa ao repositório
                        repoJ.inserir(new PessoaJuridica(id, nome, cnpj));
                    }else{
                        System.out.println("Opção inválida. Reiniciando...");
                    }
                break;
                case 2: //alterar
                    String tipo = f_ou_j();
                    System.out.println("Digite o id da pessoa que sofrerá alterações:");
                    int id = entrada.nextInt();
                    if (tipo.equals("f")){
                        repoF.alterar(id);
                    }else if (tipo.equals("j")){
                        repoJ.alterar(id);
                    }else{
                        System.out.println("Opção inválida. Reiniciando...");
                    }
                break;    
                case 3: //excluir
                    tipo = f_ou_j();
                    System.out.println("Digite o id da pessoa a ser excluída:");
                    id = entrada.nextInt();
                    if (tipo.equals("f")){
                        repoF.excluir(id);
                    }else if (tipo.equals("j")){
                        repoJ.excluir(id);
                    }else{
                        System.out.println("Opção inválida. Reiniciando...");
                    }
                break;
                case 4: //buscar
                    tipo = f_ou_j();
                    System.out.println("Digite o id da pessoa buscada:");
                    id = entrada.nextInt();
                    if (tipo.equals("f")){
                        repoF.obter(id);
                    }else if (tipo.equals("j")){
                        repoJ.obter(id);
                    }else{
                        System.out.println("Opção inválida. Reiniciando...");
                    }
                break;
                case 5: //exibir
                    tipo = f_ou_j();
                    if (tipo.equals("f")){
                        ArrayList<PessoaFisica> pessoas = repoF.obterTodos();
                        for (PessoaFisica pessoa : pessoas){
                            pessoa.exibir();
                        }
                    }else if (tipo.equals("j")){
                        ArrayList<PessoaJuridica> pessoas = repoJ.obterTodos();
                        for (PessoaJuridica pessoa : pessoas){
                            pessoa.exibir();
                        }
                    }else{
                        System.out.println("Opção inválida. Reiniciando...");
                    }
                break;
                case 6: //persisitr
                    System.out.print("Digite o prefixo para os arquivos de persistência: ");
                    String prefixoPersistencia = entrada.next();
                    try {
                        repoF.persistir(prefixoPersistencia);
                        repoJ.persistir(prefixoPersistencia);
                    } catch (IOException e) {
                        System.err.println("Erro ao persistir os dados: " + e.getMessage());
                    }
                    break;

                case 7: //recuperar
                    System.out.print("Digite o prefixo para os arquivos de recuperação: ");
                    String prefixoRecuperacao = entrada.next();
                    try {
                        repoF.recuperar(prefixoRecuperacao);
                        repoJ.recuperar(prefixoRecuperacao);
                    } catch (IOException | ClassNotFoundException e) {
                        System.err.println("Erro ao recuperar os dados: " + e.getMessage());
                    }
                    break;
                    
                case 0: //fim programa
                    controle = false;
                    break;
            }
        }
    }
}

