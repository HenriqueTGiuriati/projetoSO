/*
 * Classe que implementa a interface ManagementInterface
 */

package projetoso;

import java.io.*;

public class FileOrganizationManager /*implements ManagementInterface*/ {
    
    FileReader fr;
    BufferedReader br;
    FileWriter fw;
    BufferedWriter bw;
    File f;
    
    //construtor passar o metodo de gerenciamento e o arquivo
    public FileOrganizationManager(/*File f*/)  {
    
        //this.f = f;
        
    }
    
    //implementacao dos metodos
    
    //fazer para o arquivo inteiro 
    public void compact()  {
        
        String linha;
        char[] linha2;
        
        try  {
        
            fr = new FileReader("teste.txt");
            br = new BufferedReader(fr);
          
            //nao sei se vai compactar o arquivo inteiro ou so uma linha, colocar while           
            linha = br.readLine(); //armazena primeira linha
            linha = br.readLine(); //so para testar
   
            linha2 = linha.toCharArray();
  
            System.out.println("Antes");
            for(int k=0; k<linha2.length; k++)  {
                System.out.print(linha2[k] + " ");
            }
            
            System.out.println();
            
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
                  
        } catch(IOException ie)  {
            
            System.err.println(ie);
        }
    
    }
    
    /*
    public int[] allocateDataBlock(int numberOfBlocks)  {
        
        
    }
    
    public boolean freeDataBlocks(int[] blockId)  {
    
    }
    
    public void format()  {
    
    }
    
    public String getDataBlockInfo(int blockId)  {
        
    }
    
    public int[] getEmptyFileBlockList()  {
        
    }
    
    public int[] getUsedFileBlockList()  {
    
    }
    
    public boolean saveToFile(String fileName)  {
    
    }*/
}
