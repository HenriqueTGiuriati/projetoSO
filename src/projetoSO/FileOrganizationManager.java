/*
 * Classe que implementa a interface ManagementInterface para Vetor de Bits
 */

package projetoso;

import java.io.*;

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
    /*
    //utilizada para obter informacoes sobre um bloco de dados
    //SO TENTEI, AINDA NAO DEU CERTO
    public String getDataBlockInfo(int blockId)  {
        
        String linha, info = "0";
        int matrix[][] = new int[8][8];
        
        try  {
            
            for(int i=0; i<8; i++)  {
                for(int j=0; i<8; j++)  {
            
                    matrix[i][j] = br.read();
                }
            }
        
            for(int i=0; i<8; i++)  {
                for(int j=0; i<8; j++)  {
            
                    System.out.print(matrix[i][j] + " ");
                    
                }
                System.out.println();
            }
        
        }  catch(IOException ioe)  {
            System.err.println(ioe);
        }
        
        return info;
    }
    
    public int[] getEmptyFileBlockList()  {
        
    }
    
    public int[] getUsedFileBlockList()  {
    
    }
    
    public boolean saveToFile(String fileName)  {
    
    }*/
}
