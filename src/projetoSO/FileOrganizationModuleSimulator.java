/*
 * Classe de interface com o usuario
 */
package projetoSO;

import java.util.Scanner;
import java.io.*;

/**
 *
 * @author Cassio Henrique de Oliveira Daltoe
 *         Henrique Tresmonde Giuriati
 *         Gabriel Henrique Alves Vieira
 *         Joao Paulo Peres Martins
 */


public class FileOrganizationModuleSimulator {

    public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);
        String opcao = "0";
        String opcaoInterna = "0";
        String nomeArquivo;
        int numeroBloco;
        boolean trocaMenu = false;
        
        File f = new File("teste.txt");
   
        FileOrganizationManager fom = new FileOrganizationManager(f);
        //fom.compact();
        //fom.format();
        //fom.getDataBlockInfo(1);
        //fom.getEmptyFileBlockList();
        //fom.getUsedFileBlockList();
        
        while(!opcao.equals("3") && trocaMenu == false)  {
            
            System.out.println("Escolha a politica de gerenciamento de espaco livre:");
            System.out.println("1 - Vetor de bits \n2 - Agrupamento\n3 - Sair");
        
            System.out.print("> ");
            opcao = sc.nextLine();

            //chama a execucao do vetor de bits
            if(opcao.equals("1") && trocaMenu == false)  {
                
                trocaMenu = true;
                while(trocaMenu == true)  {

                    System.out.println("****Vetor de Bits****");
                    System.out.println("1. Compactar");
                    System.out.println("2. Alocar Blocos");
                    System.out.println("3. Liberar Blocos");
                    System.out.println("4. Formatar");
                    System.out.println("5. Informacao Bloco");
                    System.out.println("6. Lista de Blocos Disponiveis");
                    System.out.println("7. Lista de Blocos Ocupados");
                    System.out.println("8. Salvar no arquivo");
                    System.out.println("9. Voltar\n");

                    System.out.print("> ");
                    opcaoInterna = sc.nextLine();

                    if(opcaoInterna.equals("1"))  {
                        fom.compact();

                    }

                    else if(opcaoInterna.equals("2"))  {

                        trocaMenu = false;
                    }

                    else if(opcaoInterna.equals("3"))  {

                        trocaMenu = false;
                    }

                    else if(opcaoInterna.equals("4"))  {
                        fom.format();
                        //trocaMenu = false;
                    }

                    else if(opcaoInterna.equals("5"))  {

                        System.out.print("Entre com o numero do bloco:");
                        numeroBloco = sc.nextInt();
                        System.out.println("Informacao: " + fom.getDataBlockInfo(numeroBloco));
                        System.out.println();
                        //trocaMenu = true;

                    }

                    else if(opcaoInterna.equals("6"))  {

                        fom.getEmptyFileBlockList();

                    }

                    else if(opcaoInterna.equals("7"))  {

                        fom.getUsedFileBlockList();

                    }

                    else if(opcaoInterna.equals("8"))  {

                        System.out.print("Salvar no arquivo: ");
                        nomeArquivo = sc.nextLine();
                        fom.saveToFile(nomeArquivo);

                    }

                    else if(opcaoInterna.equals("9"))  {

                        trocaMenu = false;

                    }
                  
                
                }
                
            }
            //chama a execucao do agrupamento
            else if(opcao.equals("2"))  {
                
                trocaMenu = true;
                System.out.println("****Agrupamento****\n");
            
            }
            //sair do programa
            else if(opcao.equals("3"))  {
                
                System.exit(0);
                
            }
            //caso qualquer outro numero seja digitado
            else  {

                System.out.println("Digite apenas as opcoes: 1/2/3\n");
            }
      
        }
    
    }
    
}
