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
    
    //utilizada para obter informacoes sobre um bloco de dados, retornara se o bloco esta disponivel ou nao
    public String getDataBlockInfo(int blockId)  {
        
        String info = "0";
        char vetor[] = new char[blockId];
        char verifica, linha2[];
        String linha;
        
        try  {
        
            int k = 0;
            int j;
            
            while(br.ready()) { 
            
                linha = br.readLine();
                linha2 = linha.toCharArray(); //cria um vetor da linha
                
                j = 0;
                while(j < linha2.length && k < blockId)  { //o vetor vai armazenar ate a posicao requerida
                    
                    vetor[k] = linha2[j];
                    k++;
                    j++;
                
                }
                
            }
            
            /*for(j=0; j<vetor.length; j++)  {
                
                System.out.print(vetor[j]);
            }*/
            
            verifica = vetor[blockId - 1]; //a ultima posicao sera a posicao requerida
            
            if(verifica == '0')  {
                info = "Bloco disponivel";
            }  else  {
                info = "Bloco alocado";
            }
            
        }  catch(IOException ioe)  {
            System.err.println(ioe);
        }

        //System.out.println(info);
        return info;
    }
    
    //utilizada para recuperar a lista de blocos disponiveis no sistema
    public int[] getEmptyFileBlockList()  {
        
        int emptyList[] = new int[30];
        char vetorTotal[] = new char[500];
        char linha2[];
        String linha;
        int j, k;
        
        try  {
            
            k = 0;
            
            while(br.ready()) { 

                    linha = br.readLine();
                    linha2 = linha.toCharArray(); //cria um vetor da linha

                    j = 0;
                    while(j < linha2.length)  { //armazena todos os blocos em um vetor

                        vetorTotal[k] = linha2[j];
                        j++;
                        k++;

                    }

            }
            
            int count = 0;  //armazena posicoes vazias
            int w = 0;
            for(int u=0; u<vetorTotal.length; u++)  {
                
                if(vetorTotal[u] == '0')  {

                    emptyList[w] = count;  //armazena no vetor de blocos livres
                    w++;
                    
                }
               
                count++;
            }
            
            for(int u=0; u<emptyList.length; u++)  {
          
                System.out.print(emptyList[u] + " ");
                
            }
           
        }  catch(IOException ioe)  {
            System.err.println(ioe);
        }
        
        return emptyList;
    }
    
    //utilizada para recuperar a lista de blocos alocados no sistema
    public int[] getUsedFileBlockList()  {
    
        int alocList[] = new int[30];
        char vetorTotal[] = new char[500];
        char linha2[];
        String linha;
        int j, k;
        
        try  {
            
            k = 0;
            
            while(br.ready()) { 

                    linha = br.readLine();
                    linha2 = linha.toCharArray(); //cria um vetor da linha

                    j = 0;
                    while(j < linha2.length)  { //armazena todos os blocos em um vetor

                        vetorTotal[k] = linha2[j];
                        j++;
                        k++;

                    }

            }
            
            int count = 0;  //armazena posicoes ocupadas
            int w = 0;
            for(int u=0; u<vetorTotal.length; u++)  {
                
                if(vetorTotal[u] == '1')  {

                    alocList[w] = count;  //armazena no vetor de blocos ocupados
                    w++;
                    
                }
               
                count++;
            }
            
            for(int u=0; u<alocList.length; u++)  {
          
                System.out.print(alocList[u] + " ");
                
            }
           
        }  catch(IOException ioe)  {
            System.err.println(ioe);
        }
        
        return alocList;
        
    }
    
    public boolean saveToFile(String fileName)  {
        
        File fileSave;
        FileWriter fw;
        BufferedWriter save;
        
        fileSave = new File(fileName);
        
    
        return true;
    }
}
