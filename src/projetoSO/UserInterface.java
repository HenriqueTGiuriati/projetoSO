/*
 * Classe de interface com o usuario
 */
package projetoso;

import java.util.Scanner;
import java.io.*;

/**
 *
 * @author Cassio Henrique de Oliveira Daltoe
 *         Henrique Tresmonde Giuriati
 *         Gabriel Henrique Alves Vieira
 *         Joao Paulo Peres
 */


public class UserInterface {

    public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);
        String opcao = "0";
        
        File f = new File("teste.txt");
   
        FileOrganizationManager fom = new FileOrganizationManager(f);
        fom.compact();
        
        while(!opcao.equals("3"))  {
            
            System.out.println("Escolha a politica de gerenciamento de espaco livre:");
            System.out.println("1 - Vetor de bits \n2 - Agrupamento\n3 - Sair");
        
            System.out.print("> ");
            opcao = sc.nextLine();

            //chama a execucao do vetor de bits
            if(opcao.equals("1"))  {
                
                System.out.println("Voce escolheu vetor de bits\n");
            
            }
            //chama a execucao do agrupamento
            else if(opcao.equals("2"))  {
                
                System.out.println("Voce escolheu agrupamento\n");
            
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
