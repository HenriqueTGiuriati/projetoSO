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

/**
 * CIAware :: Centro de Informatizações e Análises
 * ----------------------------------------------- 
 *
 * @author Henrique Tresmonde Giuriati (Henrique, Jun 28, 2016)
 * Responsabilidade da classe:
 *
 *
 */

/**
 * CIAware :: Center of Informatization and Analysis
 * -----------------------------------------------------
 *
 * @author Henrique Tresmonde Giuriati (Henrique, Jun 28, 2016) 
 * Class responsibility: 
 *
 *
 */

public class FileOrganizationManagerAgrupamento implements ManagementInterface{

	FileReader fr;
	BufferedReader br;
	FileWriter fw;
	BufferedWriter bw;
	File f;
	
	private List<int[]> agrupamento = new ArrayList<int[]>();
	
	private int m = 0;
	private int n = 0;
	
	public FileOrganizationManagerAgrupamento(File f){
		
		try {

			this.f = f;

			if (!f.exists()) {

				System.out.println("Arquivo nao encontrado!");

			} else {

				fr = new FileReader(f);
				br = new BufferedReader(fr);
				
				//ler o numero de posições vazias no arquivo e criar a lista
				
				//lista de vetores de 8 posições

			}

		} catch (IOException ioe) {

			System.err.println(ioe);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see projetoSO.ManagementInterface#compact()
	 */
	@Override
	public void compact() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see projetoSO.ManagementInterface#allocateDataBlock(int)
	 */
	@Override
	public int[] allocateDataBlock(int numberOfBlocks) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see projetoSO.ManagementInterface#freeDataBlocks(int[])
	 */
	@Override
	public boolean freeDataBlocks(int[] blockId) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see projetoSO.ManagementInterface#format()
	 */
	@Override
	public void format() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see projetoSO.ManagementInterface#getDataBlockInfo(int)
	 */
	@Override
	public String getDataBlockInfo(int blockId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see projetoSO.ManagementInterface#getEmptyFileBlockList()
	 */
	@Override
	public int[] getEmptyFileBlockList() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see projetoSO.ManagementInterface#getUsedFileBlockList()
	 */
	@Override
	public int[] getUsedFileBlockList() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see projetoSO.ManagementInterface#saveToFile(java.lang.String)
	 */
	@Override
	public boolean saveToFile(String fileName) {
		// TODO Auto-generated method stub
		return false;
	}

}
