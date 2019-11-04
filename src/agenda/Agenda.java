package agenda;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Agenda {
    static ArrayList<Contato> contatos = new ArrayList();
    static Scanner ent = new Scanner(System.in);

    public static void main(String[] args) {
        lerContatos();
        mostrarMenu();
    }

    public static void mostrarMenu() {
        while (true) {
            System.out.println("1. Novo Contato");
            System.out.println("2. Listar Contatos");
            System.out.println("3. Excluir Todos Contatos");
            System.out.println("4. Excluir Contato");
            System.out.println("5. Atualizar Contato");
            System.out.println("6. Pesquisar Contato Por Nome");
            System.out.println("7. Sair");
            System.out.print("Sua opção: ");
            int opcao = Integer.parseInt(ent.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarNovoContato();
                    break;
                case 2:
                    listarContatos();
                    break;
                case 3:
                    excluirTodosContatos();
                    break;
                case 4:
                    excluirContato();
                    break;
                case 5:
                    atualizarContato();
                    break;
                case 6:
                    pesquisarContato();
                    break;    
                case 7:
                    System.out.println("\nAu Revoir...\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nOpção inválida! Afff!\n");
                    break;
            }
        }
    }

    public static void cadastrarNovoContato() {
        System.out.println("\nNovo Contato:\n");
        Contato c = new Contato();
        System.out.print("Código: ");
        c.setCodigo(Integer.parseInt(ent.nextLine()));
        System.out.print("Nome: ");
        c.setNome(ent.nextLine());
        contatos.add(c);
        System.out.println("\nContato salvo com sucesso.\n");
        salvarContatos();
    }

    public static void listarContatos() {
        System.out.println("\nListando os contatos\n");
        for (int i = 0; i < contatos.size(); i++) {
            System.out.println("Código: " + contatos.get(i).getCodigo());
            System.out.println("Nome: " + contatos.get(i).getNome() + "\n");
        }
    }

    public static void salvarContatos() {
        try {
            FileOutputStream fos = new FileOutputStream("c.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(contatos);
            oos.close();
        } catch (Exception exc) {
            System.out.println("Erro: " + exc.getMessage());
        }
    }

    public static void lerContatos() {
        try {
            FileInputStream fis = new FileInputStream("c.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            contatos = (ArrayList) ois.readObject();
            ois.close();
        } catch (Exception exc) {
            System.out.println("Erro: " + exc.getMessage());
        }
    }

    public static void excluirTodosContatos() {
        contatos.clear();
        System.out.println("\nContatos excluídos com sucesso.\n");
        salvarContatos();
    }

    public static void excluirContato() {
        System.out.println("\nExcluir Contato:\n");
        System.out.print("Informe o código do contato a ser excluído: ");
        int codigo = Integer.parseInt(ent.nextLine());
        
        // o contato foi encontrado?
        boolean encontrado = false;
        for(int i = 0; i < contatos.size(); i++){
            if(contatos.get(i).getCodigo() == codigo){
                // encontramos... vamos excluir o contato com este índice
                contatos.remove(i);
                encontrado = true;
                break; // saimos do laço
            }
        }
        
        if(encontrado){
            System.out.println("\nContato excluído com sucesso.\n");
            salvarContatos();
        }   
        else{
            System.out.println("\nContato não foi encontrado.\n");
        }
    }

    private static void atualizarContato() {
        System.out.println("\nAtualizar Contato:\n");
        System.out.print("Informe o código do contato a ser atualizado: ");
        int codigo = Integer.parseInt(ent.nextLine());
        
        // o contato foi encontrado?
        boolean encontrado = false;
        for(int i = 0; i < contatos.size(); i++){
            if(contatos.get(i).getCodigo() == codigo){
                // encontramos... vamos atualizar o contato com este índice
                System.out.print("Novo nome: ");
                contatos.get(i).setNome(ent.nextLine());
                encontrado = true;
                break; // saimos do laço
            }
        }
        
        if(encontrado){
            System.out.println("\nContato atualizado com sucesso.\n");
            salvarContatos();
        }   
        else{
            System.out.println("\nContato não foi encontrado.\n");
        }
    }

    public static void pesquisarContato() {
        System.out.println("\nPesquisar Contato:\n");
        System.out.print("Informe uma parte do nome do contato a ser pesquisado: ");
        String pesquisa = ent.nextLine();
        
        // quantos contatos foram encontrados?
        int encontrados = 0;
        for(int i = 0; i < contatos.size(); i++){
            if(contatos.get(i).getNome().toUpperCase().contains(pesquisa.toUpperCase())){
                // encontramos mais um contato...
                System.out.println("Código: " + contatos.get(i).getCodigo());
                System.out.println("Nome: " + contatos.get(i).getNome() + "\n");
                encontrados++;
            }
        }
        
        System.out.println(encontrados + " contatos encontrados.\n");
    }
}
    