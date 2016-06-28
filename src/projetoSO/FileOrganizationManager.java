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

public class FileOrganizationManager /* implements ManagementInterface */ {

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
	public FileOrganizationManager(File f) {

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
					System.out.println("oie: "+linha);
					count = 0;
					while (count < linha.length()) {
						vetorDeBits[i] = Integer.parseInt(linha.substring(count, count+1));
						i++;
						count++;
					}
				}
				
				for (int j = 0; j < vetorDeBits.length; j++) {
					System.out.print(vetorDeBits[j] + " ");
				}

			}

		} catch (IOException ioe) {

			System.err.println(ioe);
		}

	}

	// implementacao dos metodos

	// SO FALTA SALVAR A COMPACTACAO NO ARQUIVO, NAO SEI SE FAZ AQUI OU NO
	// SAVETOFILE
	public void compact() {

		int count = 0;

		for (int i = 0; i < vetorDeBits.length; i++) {
			if (vetorDeBits[i] == 1) {
				count++;
			}
		}

		format();

		for (int i = 1; i < count + 1; i++) {
			vetorDeBits[i] = 1;
		}

		for (int i = 0; i < vetorDeBits.length; i++) {
			System.out.print(vetorDeBits[i] + " ");
		}

	}

	/*
	 * public int[] allocateDataBlock(int numberOfBlocks) {
	 * 
	 * 
	 * }
	 * 
	 * public boolean freeDataBlocks(int[] blockId) {
	 * 
	 * }
	 */

	// utilizada para formatar o sistema de arquivos, tornando todos os blocos
	// disponiveis
	public void format() {

		// vetorDeBits = new int[m*n];

		vetorDeBits[0] = 1;

		for (int i = 1; i < m * n; i++) {
			vetorDeBits[i] = 0;
		}

		// saveToFile();

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

		int emptyList[] = new int[30];
		char vetorTotal[] = new char[500];
		char linha2[];
		String linha;
		int j, k;

		try {

			k = 0;

			while (br.ready()) {

				linha = br.readLine();
				linha2 = linha.toCharArray(); // cria um vetor da linha

				j = 0;
				while (j < linha2.length) { // armazena todos os blocos em um
											// vetor

					vetorTotal[k] = linha2[j];
					j++;
					k++;

				}

			}

			int count = 0; // armazena posicoes vazias
			int w = 0;
			for (int u = 0; u < vetorTotal.length; u++) {

				if (vetorTotal[u] == '0') {

					emptyList[w] = count; // armazena no vetor de blocos livres
					w++;

				}

				count++;
			}

			for (int u = 0; u < emptyList.length; u++) {

				System.out.print(emptyList[u] + " ");

			}

		} catch (IOException ioe) {
			System.err.println(ioe);
		}

		return emptyList;
	}

	// utilizada para recuperar a lista de blocos alocados no sistema
	public int[] getUsedFileBlockList() {

		int alocList[] = new int[30];
		char vetorTotal[] = new char[500];
		char linha2[];
		String linha;
		int j, k;

		try {

			k = 0;

			while (br.ready()) {

				linha = br.readLine();
				linha2 = linha.toCharArray(); // cria um vetor da linha

				j = 0;
				while (j < linha2.length) { // armazena todos os blocos em um
											// vetor

					vetorTotal[k] = linha2[j];
					j++;
					k++;

				}

			}

			int count = 0; // armazena posicoes ocupadas
			int w = 0;
			for (int u = 0; u < vetorTotal.length; u++) {

				if (vetorTotal[u] == '1') {

					alocList[w] = count; // armazena no vetor de blocos ocupados
					w++;

				}

				count++;
			}

			for (int u = 0; u < alocList.length; u++) {

				System.out.print(alocList[u] + " ");

			}

		} catch (IOException ioe) {
			System.err.println(ioe);
		}

		return alocList;

	}

	public boolean saveToFile(String fileName) {

		File fileSave;
		FileWriter fw;
		BufferedWriter save;

		fileSave = new File(fileName);

		// salvar o vetorDeBits no arquivo

		return true;
	}
}
