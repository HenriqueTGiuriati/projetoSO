/*
 * Classe que implementa a interface ManagementInterface para Vetor de Bits
 */

package projetoso;

import java.io.*;
import java.util.Scanner;

public class FileOrganizationManager /*implements ManagementInterface*/ {
    
    FileReader fr;
    BufferedReader br;
    FileWriter fw;
    BufferedWriter bw;
    File f;
    
    //construtor recebe arquivo
    public FileOrganizationManager(File f)  {
    
        try  {
            
            this.f = f;
            
            if(!f.exists())  {
              
                System.out.println("Arquivo nao encontrado!");
            
            }  else  {
                
                fr = new FileReader(f);
                br = new BufferedReader(fr);
                //fw = new FileWriter(f);
            
            }
        
        }  catch(IOException ioe)  {
            
            System.err.println(ioe);
        }
        
    }
    
    //implementacao dos metodos
    
    //SO FALTA SALVAR A COMPACTACAO NO ARQUIVO, NAO SEI SE FAZ AQUI OU NO SAVETOFILE
    public void compact()  {
        
        String linha;
        char[] linha2;
        
        try  {
        
            br = new BufferedReader(fr);
         
            //le linha por linha no arquivo enquanto tiver linhas
            while((linha = br.readLine()) != null)  {  
                     
                linha2 = linha.toCharArray(); //armazena a linha em um vetor

                System.out.println("Antes");
                for(int k=0; k<linha2.length; k++)  {
                    System.out.print(linha2[k] + " ");
                }

                System.out.println();

                //faz a compactacao
                for(int i=0; i<linha2.length; i++)  {

                    if(linha2[i] == '0')  {

                        for(int j=i; j<(linha2.length); j++)  {

                            if(linha2[j] == '1')  {

                                linha2[i] = '1';
                                linha2[j] = '0';
                                break;
                            }
                        }

                    }

                }   
                System.out.println("Depois");

                for(int k=0; k<linha2.length; k++)  {
                    System.out.print(linha2[k] + " ");
                }

                System.out.println();

            } //fim do while
                  
        } catch(IOException ie)  {
            
            System.err.println(ie);
        }
    
    }
    
    /*
    public int[] allocateDataBlock(int numberOfBlocks)  {
        
        
    }
    
    public boolean freeDataBlocks(int[] blockId)  {
    
    }*/
    
    //utilizada para formatar o sistema de arquivos, tornando todos os blocos disponiveis
    public void format()  {
        
        String linha;
        char[] linha2;
        
        try  {
        
            while((linha = br.readLine()) != null)  {
           
                linha2 = linha.toCharArray();
                
                for(int i=0; i<linha2.length; i++)  {
                    
                    if(linha2[i] == '1')  {
                        linha2[i] = '0'; 
                    }
                }
                
            }
        
        
        }  catch(IOException ioe)  {
            System.err.println(ioe);
        }
    
    }
    
    //utilizada para obter informacoes sobre um bloco de dados, retornara se o bloco esta disponivel ou nao
    //AINDA NAO DEU CERTO FUNCIONA PARA VALORES <= ao tamanho da linha!
    public String getDataBlockInfo(int blockId)  {
        
        String info = "0";
        int bit, loop;
        char vetor[] = new char[blockId];
        int i;
        char verifica;
        String linha;
        boolean check = false;
        
        try  {
        
            i = 0;
            bit = br.read(vetor, 0, blockId); //le o arquivo e armazena as posicoes em um vetor
             
            while(i < blockId)  {
                
                System.out.println(vetor[i]);
                i++;
                
            }
            
            verifica = vetor[blockId - 1]; //a ultima posicao sera a posicao requerida
            
            if(verifica == '0')  {
                info = "Bloco disponivel";
            }  else  {
                info = "Bloco alocado";
            }
            
        }  catch(IOException ioe)  {
            System.err.println(ioe);
        }

        System.out.println(info);
        return info;
    }
    /*
    public int[] getEmptyFileBlockList()  {
        
    }
    
    public int[] getUsedFileBlockList()  {
    
    }
    */
    public boolean saveToFile(String fileName)  {
        
        File fileSave;
        FileWriter fw;
        BufferedWriter save;
        
        fileSave = new File(fileName);
        
    
        return true;
    }
}
