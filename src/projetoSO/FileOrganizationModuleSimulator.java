/*
 * Classe de interface com o usuario
 */
package projetoSO;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Cassio Henrique de Oliveira Daltoe Henrique Tresmonde Giuriati
 *         Gabriel Henrique Alves Vieira Joao Paulo Peres Martins
 */

public class FileOrganizationModuleSimulator {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int opcao = 0;
		int opcaoInterna = 0;
		String nomeArquivo;
		int numeroBlocosAlocar;
		int numeroBloco;
		boolean trocaMenu = false;

		System.out.println("Insira o nome do arquivo: ");
		System.out.print("> ");
		String nameFile = sc.nextLine();

		File f;
		if (nameFile.contains(".txt"))
			f = new File(nameFile);
		else
			f = new File(nameFile + ".txt");

		while (opcao != 3 && trocaMenu == false) {

			System.out.println("Escolha a politica de gerenciamento de espaco livre:");
			System.out.println("1 - Vetor de bits \n2 - Agrupamento\n3 - Sair");

			System.out.print("> ");
			opcao = sc.nextInt();

			switch (opcao) {

			case 1:

				FileOrganizationManagerVetorDeBits fomvt = new FileOrganizationManagerVetorDeBits(f);
				trocaMenu = true;

				while (trocaMenu == true) {

					System.out.println("****Vetor de Bits****");
					System.out.println("1. Compactar");
					System.out.println("2. Alocar Blocos");
					System.out.println("3. Liberar Blocos");
					System.out.println("4. Formatar");
					System.out.println("5. Informacao Bloco");
					System.out.println("6. Lista de Blocos Disponiveis");
					System.out.println("7. Lista de Blocos Ocupados");
					System.out.println("8. Salvar no arquivo");
					System.out.println("9. Voltar\n");

					System.out.print("> ");
					opcaoInterna = sc.nextInt();

					switch (opcaoInterna) {

					case 1:

						fomvt.compact();

						break;

					case 2:

						System.out.print("Entre com o numero de blocos que deseja alocar: ");
						numeroBlocosAlocar = sc.nextInt();
						fomvt.allocateDataBlock(numeroBlocosAlocar);

						break;

					case 3:

						int numero;
						System.out.print("Entre com o numero de blocos que deseja liberar:");
						numero = sc.nextInt();
						System.out.println("Entre com os blocos que deseja liberar:");

						int[] vector;

						vector = new int[numero];
						int i = 0;

						while (i < vector.length) {

							vector[i] = sc.nextInt();
							i++;

						}

						fomvt.freeDataBlocks(vector);

						break;

					case 4:

						fomvt.format();
						break;

					case 5:

						System.out.print("Entre com o numero do bloco:");
						numeroBloco = sc.nextInt();
						System.out.println("Informacao: " + fomvt.getDataBlockInfo(numeroBloco));
						System.out.println();

						break;

					case 6:

						int vetorVazios[] = new int[fomvt.getEmptyFileBlockList().length];
						int k = 0;

						vetorVazios = fomvt.getEmptyFileBlockList();

						System.out.println("Lista de Blocos Vazios:");
						while (k < vetorVazios.length) {

							System.out.print(vetorVazios[k] + " ");
							k++;

						}

						System.out.println();

						break;

					case 7:

						int vetorCheios[] = new int[fomvt.getUsedFileBlockList().length];
						int l = 0;

						vetorCheios = fomvt.getUsedFileBlockList();

						System.out.println("Lista de Blocos ocupados:");
						while (l < vetorCheios.length) {

							System.out.print(vetorCheios[l] + " ");
							l++;

						}

						System.out.println();

						break;

					case 8:

						System.out.print("Salvar no arquivo: ");
						nomeArquivo = sc.nextLine();
						nomeArquivo = sc.nextLine();
						fomvt.saveToFile(nomeArquivo);

					case 9:

						trocaMenu = false;

						break;

					}

				}

				break;

			case 2:

				FileOrganizationManagerAgrupamento foma = new FileOrganizationManagerAgrupamento(f);

				trocaMenu = true;

				while (trocaMenu == true) {

					System.out.println("****Agrupamento****");
					System.out.println("1. Compactar");
					System.out.println("2. Alocar Blocos");
					System.out.println("3. Liberar Blocos");
					System.out.println("4. Formatar");
					System.out.println("5. Informacao Bloco");
					System.out.println("6. Lista de Blocos Disponiveis");
					System.out.println("7. Lista de Blocos Ocupados");
					System.out.println("8. Salvar no arquivo");
					System.out.println("9. Voltar\n");

					System.out.print("> ");
					opcaoInterna = sc.nextInt();

					switch (opcaoInterna) {

					case 1:

						foma.compact();

						break;

					case 2:

						System.out.print("Entre com o numero de blocos que deseja alocar: ");
						numeroBlocosAlocar = sc.nextInt();
						foma.allocateDataBlock(numeroBlocosAlocar);

						break;

					case 3:

						int numero;
						System.out.print("Entre com o numero de blocos que deseja liberar:");
						numero = sc.nextInt();
						System.out.println("Entre com os blocos que deseja liberar:");

						int[] vector;

						vector = new int[numero];
						int i = 0;

						while (i < vector.length) {

							vector[i] = sc.nextInt();
							i++;

						}

						boolean result = foma.freeDataBlocks(vector);

						if (result == true)
							System.out.println("Bloco liberado");
						else
							System.out.println("Não foi possível liberar nenhum destes blocos");
						break;

					case 4:

						foma.format();
						break;

					case 5:

						System.out.print("Entre com o numero do bloco:");
						numeroBloco = sc.nextInt();
						System.out.println("Informacao: " + foma.getDataBlockInfo(numeroBloco));
						System.out.println();

						break;

					case 6:

						int vetorVazios[] = new int[foma.getEmptyFileBlockList().length];
						int k = 0;

						vetorVazios = foma.getEmptyFileBlockList();

						System.out.println("Lista de Blocos Vazios:");
						while (k < vetorVazios.length) {

							System.out.print(vetorVazios[k] + " ");
							k++;

						}

						System.out.println();

						break;

					case 7:

						int vetorCheios[] = new int[foma.getUsedFileBlockList().length];
						int l = 0;

						vetorCheios = foma.getUsedFileBlockList();

						System.out.println("Lista de Blocos ocupados:");
						while (l < vetorCheios.length) {

							System.out.print(vetorCheios[l] + " ");
							l++;

						}

						System.out.println();

						break;

					case 8:

						System.out.print("Salvar no arquivo: ");
						nomeArquivo = sc.nextLine();
						nomeArquivo = sc.nextLine();
						foma.saveToFile(nomeArquivo);

					case 9:

						trocaMenu = false;

						break;

					}
				}

				break;

			case 3:

				System.exit(0);
				break;

			default:
				System.out.println("Pressione 1/2/3 apenas");

			}

		}

	}

}
