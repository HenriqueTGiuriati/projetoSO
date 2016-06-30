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
import java.io.PrintWriter;
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
		int count = 0;
		// determina numero de blocos livres
		for (int x = 0; x < agrupamento.size(); x++) {
			for (int j = 0; j < 8; j++) {
				if (agrupamento.get(x)[j] != -1) {
					count++;
				}
			}
		}

		// formata meu agrupamento
		for (int x = 0; x < agrupamento.size(); x++) {
			for (int j = 0; j < 8; j++) {
				agrupamento.get(x)[j] = -1;
			}
		}

		// marca os ultimos x blocos do disco no agrupamento
		for (int x = 0; x < agrupamento.size(); x++) {
			for (int j = 0; j < 7; j++) {
				if (count > 0) {
					agrupamento.get(x)[j] = totalBlocos - count;
					count--;
				}
			}
		}

	}

	public int[] allocateDataBlock(int numberOfBlocks) {

		int count = 0;
		int vetorResult[] = new int[numberOfBlocks];

		for (int x = 0; x < agrupamento.size(); x++) {
			for (int j = 0; j < 8; j++) {
				if (count < numberOfBlocks) {
					vetorResult[count] = agrupamento.get(x)[j];
					agrupamento.get(x)[j] = -1;
					count++;
				}
			}
		}

		int aux[] = new int[totalBlocos];
		for (int x = 0; x < totalBlocos; x++) {
			aux[x] = -1;
		}

		int a = 0;
		for (int x = 0; x < agrupamento.size(); x++) {
			for (int j = 0; j < 8; j++) {
				if (agrupamento.get(x)[j] != -1) {
					aux[a] = agrupamento.get(x)[j];
					a++;
				}
			}
		}

		// formata meu agrupamento
		for (int x = 0; x < agrupamento.size(); x++) {
			for (int j = 0; j < 8; j++) {
				agrupamento.get(x)[j] = -1;
			}
		}

		int countAux = 0;
		for (int x = 0; x < agrupamento.size(); x++) {
			for (int j = 0; j < 7; j++) {
				if (aux[countAux] != -1) {
					agrupamento.get(x)[j] = aux[countAux];
				}
				countAux++;
			}
		}

		return vetorResult;
	}

	public boolean freeDataBlocks(int[] blockId) {
		boolean checkBlock = false;

		for (int x = 0; x < agrupamento.size(); x++) {
			for (int j = 0; j < 8; j++) {
				for (int y = 0; y < blockId.length; y++) {
					if (agrupamento.get(x)[j] == blockId[y]) {
						checkBlock = true;
						agrupamento.get(x)[j] = -1;
					}
				}
			}
		}

		// vetor auxiliar para reorganizar agrupamento
		int aux[] = new int[totalBlocos];
		for (int x = 0; x < totalBlocos; x++) {
			aux[x] = -1;
		}

		int a = 0;
		for (int x = 0; x < agrupamento.size(); x++) {
			for (int j = 0; j < 8; j++) {
				if (agrupamento.get(x)[j] != -1) {
					aux[a] = agrupamento.get(x)[j];
					a++;
				}
			}
		}

		// formata meu agrupamento
		for (int x = 0; x < agrupamento.size(); x++) {
			for (int j = 0; j < 8; j++) {
				agrupamento.get(x)[j] = -1;
			}
		}

		// reescreve agrupamento
		int countAux = 0;
		for (int x = 0; x < agrupamento.size(); x++) {
			for (int j = 0; j < 8; j++) {
				if (aux[countAux] != -1) {
					agrupamento.get(x)[j] = aux[countAux];
				}
				countAux++;
			}
		}

		return checkBlock;
	}

	public void format() {
		// marca todos os blocos como livres
		agrupamento = new ArrayList<int[]>();
		int total = 0;

		if (totalBlocos % 7 == 0)
			total = totalBlocos / 7;
		else
			total = (totalBlocos / 7) + 1;

		for (int i = 0; i < total; i++) {
			int[] vetor = new int[8];
			for (int j = 0; j < 8; j++)
				vetor[j] = -1;
			agrupamento.add(vetor);
		}

		int count = 1;
		for (int x = 0; x < agrupamento.size(); x++) {
			for (int j = 0; j < 7; j++) {
				if (count < totalBlocos) {
					agrupamento.get(x)[j] = count;
					count++;
				}
			}
		}

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

		return emptyList;
	}

	// percorrer e pegar blocos que nao estao na lista
	public int[] getUsedFileBlockList() {
		// vetor auxiliar para reorganizar agrupamento
		int aux[] = new int[totalBlocos];
		for (int x = 0; x < totalBlocos; x++) {
			aux[x] = -1;
		}

		// verifica quantos blocos livres
		int count = 0;
		for (int l = 0; l < agrupamento.size(); l++) {
			for (int t = 0; t < 8; t++) {
				if (agrupamento.get(l)[t] != -1) {
					aux[agrupamento.get(l)[t]] = agrupamento.get(l)[t];
					count++;
				}
			}
		}

		// monta o vetor de resposta com os blocos alocados
		int[] result = new int[totalBlocos - count];
		int a = 0;
		for (int i = 0; i < aux.length; i++) {
			if (aux[i] == -1) {
				result[a] = i;
				a++;
			}
		}

		return result;
	}

	public boolean saveToFile(String fileName) {
		File fileSave;
		FileWriter fw;
		BufferedWriter save;
		PrintWriter pw;

		try {

			fileSave = new File(fileName + ".txt");
			fw = new FileWriter(fileSave);
			save = new BufferedWriter(fw);
			pw = new PrintWriter(fileSave);

			boolean livre = false;
			for (int i = 0; i < totalBlocos; i++) {
				livre = false;
				if (i != 0 && i % 8 == 0) {
					pw.println();
				}
				for (int l = 0; l < agrupamento.size(); l++) {
					for (int t = 0; t < 8; t++) {
						if (agrupamento.get(l)[t] == i) {
							pw.write("0");
							livre = true;
						}
					}
				}
				if (livre == false) {
					pw.write("1");
				}

			}

			save.flush();
			save.close();
			fw.close();
			pw.close();

		} catch (IOException ioe) {
			System.err.println(ioe);
		}

		return false;

	}

}
