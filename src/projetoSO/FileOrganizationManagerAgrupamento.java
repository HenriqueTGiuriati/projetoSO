/**
 * 
 */
package projetoSO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileOrganizationManagerAgrupamento implements ManagementInterface {

	FileReader fr;
	BufferedReader br;
	FileWriter fw;
	BufferedWriter bw;
	File f;

	private List<int[]> agrupamento = new ArrayList<int[]>();
	private int totalBlocos; // variavel global para trabalhar com o total de
								// blocos no arquivo

	public FileOrganizationManagerAgrupamento(File f) {

		try {

			this.f = f;

			if (!f.exists()) {

				System.out.println("Arquivo nao encontrado!");

			} else {

				fr = new FileReader(f);
				br = new BufferedReader(fr);
				String linha = "";
				int i = 0, count = 0, countLinha = 0, nroBloco = 0;

				// inicializa o meu agrupamento (primeiro vetor)
				int[] vetorInicio = new int[8];
				for (int j = 0; j < 8; j++)
					vetorInicio[j] = -1;
				agrupamento.add(vetorInicio);

				// ler o numero de posicoes vazias no arquivo e criar a lista
				// para cada linha do arquivo
				while ((linha = br.readLine()) != null) {
					countLinha = 0;
					// pega cada posi��o da linha
					while (countLinha < linha.length()) {
						// insere aquele valor no vetor da lista
						if (Integer.parseInt(linha.substring(countLinha, countLinha + 1)) == 0) {
							agrupamento.get(i)[count] = nroBloco;
							count++;
							// verifica se vetor esta cheio e insere novo
							// vetor
							if (count % 7 == 0) {
								int[] vetor = new int[8];
								for (int j = 0; j < 8; j++)
									vetor[j] = -1;
								agrupamento.add(vetor);
								i++;
								count = 0;
							}
						}
						countLinha++;
						nroBloco++;
					}
				}

				totalBlocos = nroBloco;

				for (int x = 0; x < agrupamento.size(); x++) {
					for (int j = 0; j < 8; j++) {
						System.out.print(agrupamento.get(x)[j] + " ");
					}
					System.out.println();
				}
			}

		} catch (IOException ioe) {

			System.err.println(ioe);
		}

	}

	public void compact() {
		// TODO Auto-generated method stub

	}

	public int[] allocateDataBlock(int numberOfBlocks) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean freeDataBlocks(int[] blockId) {
		// TODO Auto-generated method stub
		return false;
	}

	public void format() {

	}

	public String getDataBlockInfo(int blockId) {

		String info = "";
		boolean verifica = false;

		for (int x = 0; x < agrupamento.size(); x++) {

			for (int j = 0; j < 8; j++) {

				if (agrupamento.get(x)[j] == blockId) {

					info = "Bloco disponivel";
					verifica = true;
				}

			}

		}

		// caso ele nao encontre nenhum bloco na lista
		// bloco esta alocado
		if (verifica == false) {

			info = "Bloco alocado";
		}

		return info;
	}

	// percorrer a lista e pegar os indices
	public int[] getEmptyFileBlockList() {

		int emptyList[];
		int temp;
		int k = 0, count = 0;

		// contar numero de posicoes vazias para inicializar o vetor emptyList
		for (int x = 0; x < agrupamento.size(); x++) {

			for (int j = 0; j < 8; j++) {

				temp = agrupamento.get(x)[j];
				if (temp != -1) {

					count++;
				}
			}

		}

		emptyList = new int[count];

		// aramazenar os blocos disponiveis na lista
		for (int l = 0; l < agrupamento.size(); l++) {

			for (int t = 0; t < 8; t++) {

				if (agrupamento.get(l)[t] != -1) {

					emptyList[k] = agrupamento.get(l)[t]; // posicao da lista
															// seguida da
															// posicao do vetor
					k++;
				}

			}
		}

		/*
		 * for (int j = 0; j < emptyList.length; j++) {
		 * System.out.print(emptyList[j] + " "); }
		 */

		return emptyList;
	}

	// percorrer e pegar blocos que nao estao na lista
	public int[] getUsedFileBlockList() {

		/*
		 * int alocList[]; int temp[];
		 * 
		 * int count = 0, k = 0, j = 0; int arm;
		 * 
		 * temp = new int[totalBlocos]; alocList = new int[totalBlocos -
		 * getEmptyFileBlockList().length]; //Blocos Totais - disponiveis
		 * 
		 * 
		 * //aramazenar os blocos disponiveis na lista for (int l = 0; l <
		 * agrupamento.size(); l++) {
		 * 
		 * for (int t = 0; t < 8; t++) {
		 * 
		 * temp[k] = agrupamento.get(l)[t]; k++;
		 * 
		 * }
		 * 
		 * }
		 * 
		 * for(int i = 0; i < temp.length; i++) {
		 * 
		 * if(temp[i] != count) {
		 * 
		 * alocList[j] = count; //0 j++; } count++; }
		 * 
		 * for (int z = 0; z < alocList.length; z++) {
		 * System.out.print(alocList[z] + " "); }
		 */

		return null;
	}

	public boolean saveToFile(String fileName) {
		// TODO Auto-generated method stub
		return false;
	}

}
