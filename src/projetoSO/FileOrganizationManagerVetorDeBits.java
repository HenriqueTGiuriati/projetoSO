/*
 * Classe que implementa a interface ManagementInterface para Vetor de Bits
 */

package projetoSO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileOrganizationManagerVetorDeBits implements ManagementInterface  {

	FileReader fr;
	BufferedReader br;
	FileWriter fw;
	BufferedWriter bw;
	File f;

	// vetor de bits
	private int[] vetorDeBits;

	private int m = 0;
	private int n = 0;

	// construtor recebe arquivo
	public FileOrganizationManagerVetorDeBits(File f) {

		try {

			this.f = f;

			if (!f.exists()) {

				System.out.println("Arquivo nao encontrado!");

			} else {

				fr = new FileReader(f);
				br = new BufferedReader(fr);
				String linha = "";
				int count = 0;

				while ((linha = br.readLine()) != null) {
					// numero de colunas do arquivo
					// n = tamanho do vetor linha de leitura do arquivo
					n = linha.length();
					count++;
				}

				// numero de linhas do arquivo
				// m = ultima posicao do ponteiro de leitura do arquivo
				m = count;

				vetorDeBits = new int[m * n];
				
				fr = new FileReader(f);
				br = new BufferedReader(fr);

				int i=0;
				// preenche vetorDeBits
				while ((linha = br.readLine()) != null) {
					
					count = 0;
					while (count < linha.length()) {
						vetorDeBits[i] = Integer.parseInt(linha.substring(count, count+1));
						i++;
						count++;
					}
				}
				
				/*for (int j = 0; j < vetorDeBits.length; j++) {
					System.out.print(vetorDeBits[j] + " ");
				}*/

			}

		} catch (IOException ioe) {

			System.err.println(ioe);
		}

	}

	// implementacao dos metodos

	public void compact() {

		int count = 0;

		for (int i = 1; i < vetorDeBits.length; i++) {
			if (vetorDeBits[i] == 1) {
				count++;
			}
		}

		format();

		for (int i = 1; i < count + 1; i++) {
			vetorDeBits[i] = 1;
		}

	}

	
	public int[] allocateDataBlock(int numberOfBlocks) {
	 
            int array[] = new int[numberOfBlocks];
            int i = 1, j = 0;

            while(i < vetorDeBits.length && j < array.length)  {
                
                if(vetorDeBits[i] == 1)  {

                    array[j] = i;
                    vetorDeBits[i] = 0;
                    j++;

                }
                
                i++;
                        
            }
            
            return array;
            
	}
	  
        public boolean freeDataBlocks(int[] blockId) {  // 3, 4, 7
	 
            int i = 0;
            int temp;

            while(i < blockId.length)  {
            
                temp = blockId[i];
                
                vetorDeBits[temp] = 0;
                
                i++;  
            }
            
            return true;
	}
	 
	// utilizada para formatar o sistema de arquivos, tornando todos os blocos
	// disponiveis
	public void format() {
            
            vetorDeBits[0] = 1;

            for (int i = 1; i < m * n; i++) {
                    vetorDeBits[i] = 0;
            }


	}

	// utilizada para obter informacoes sobre um bloco de dados, retornara se o
	// bloco esta disponivel ou nao
	public String getDataBlockInfo(int blockId) {

            String info = "";

            if (vetorDeBits[blockId + 1] == 0) {
                    info = "Bloco disponivel";
            } else {
                    info = "Bloco alocado";
            }

            return info;
	}

	// utilizada para recuperar a lista de blocos disponiveis no sistema
	public int[] getEmptyFileBlockList() {

            int emptyList[];

            int countEmpty = 0;
            int j = 0;

            //conta numero de posicoes vazias
            while(j < vetorDeBits.length)  {

                if(vetorDeBits[j] == 0)  {
                    countEmpty++;
                }

                j++;
            }

            emptyList = new int[countEmpty];  //inicializa vetor de blocos vazios

            int count = 0; // armazena posicoes dos blocos vazios
            int w = 0;

            for (int u = 0; u < vetorDeBits.length; u++) {

                if (vetorDeBits[u] == 0) {

                        emptyList[w] = count; // armazena no vetor de blocos livres
                        w++;
                }

                count++;

            }

            /*for (int u = 0; u < emptyList.length; u++) {

                System.out.print(emptyList[u] + " ");

            }*/

            return emptyList;
	}

	// utilizada para recuperar a lista de blocos alocados no sistema
	public int[] getUsedFileBlockList() {

            int alocList[];

            int countAloc = 0;
            int j = 0;

            //conta numero de posicoes vazias
            while(j < vetorDeBits.length)  {

                if(vetorDeBits[j] == 1)  {
                    countAloc++;
                }

                j++;
            }

            alocList = new int[countAloc];  //inicializa vetor de blocos ocupados

            int count = 0; // armazena posicoes dos blocos ocupados
            int w = 0;

            for (int u = 0; u < vetorDeBits.length; u++) {

                if (vetorDeBits[u] == 1) {

                        alocList[w] = count; // armazena no vetor de blocos ocupados
                        w++;
                }

                count++;

            }

            /*for (int u = 1; u < alocList.length; u++) {

                System.out.print(alocList[u] + " ");

            }*/

            return alocList;
	}

	public boolean saveToFile(String fileName) {

		File fileSave;
		FileWriter fw;
		BufferedWriter save;
                PrintWriter pw;
                int i = 0, j = 0, k = 0;

                try  {
                           
                    fileSave = new File(fileName + ".txt");
                    fw = new FileWriter(fileSave);
                    save = new BufferedWriter(fw);
                    pw = new PrintWriter(fileSave);
                    
                    while(i < m)  {
                    
                        j = 0;
                        while(j < n)  {
                      
                            pw.write(Integer.toString(vetorDeBits[k]));
                            j++;
                            k++;
                        }
                   
                        pw.println();
                        i++;
                    }

                    save.flush();
                    save.close();
                    fw.close();
                    pw.close();
                
                }  catch(IOException ioe)  {
                    System.err.println(ioe);
                }
 
            return true;
	}
}
