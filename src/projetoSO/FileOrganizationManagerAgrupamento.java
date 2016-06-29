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

				// ler o numero de posições vazias no arquivo e criar a lista
				// para cada linha do arquivo
				while ((linha = br.readLine()) != null) {
					countLinha = 0;
					// pega cada posição da linha
					while (countLinha < linha.length()) {
						// insere aquele valor no vetor da lista
						if (Integer.parseInt(linha.substring(countLinha, countLinha + 1)) == 0) {
							agrupamento.get(i)[count] = nroBloco;
							count++;
							// verifica se vetor está cheio e insere novo
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see projetoSO.ManagementInterface#compact()
	 */
	@Override
	public void compact() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see projetoSO.ManagementInterface#allocateDataBlock(int)
	 */
	@Override
	public int[] allocateDataBlock(int numberOfBlocks) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see projetoSO.ManagementInterface#freeDataBlocks(int[])
	 */
	@Override
	public boolean freeDataBlocks(int[] blockId) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see projetoSO.ManagementInterface#format()
	 */
	@Override
	public void format() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see projetoSO.ManagementInterface#getDataBlockInfo(int)
	 */
	@Override
	public String getDataBlockInfo(int blockId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see projetoSO.ManagementInterface#getEmptyFileBlockList()
	 */
	@Override
	public int[] getEmptyFileBlockList() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see projetoSO.ManagementInterface#getUsedFileBlockList()
	 */
	@Override
	public int[] getUsedFileBlockList() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see projetoSO.ManagementInterface#saveToFile(java.lang.String)
	 */
	@Override
	public boolean saveToFile(String fileName) {
		// TODO Auto-generated method stub
		return false;
	}

}
