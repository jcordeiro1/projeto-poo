package primeirob.projetum;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PlanilhaExcel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int MAX_COLUNAS = 100;

        System.out.println("Informe o número de colunas (mínimo 3, máximo " + MAX_COLUNAS + "):");
        int numColunas = scanner.nextInt();
        scanner.nextLine(); // Consumir a linha pendente

        if (numColunas < 3 || numColunas > MAX_COLUNAS) {
            System.out.println("Número de colunas inválido. Reinicie o programa.");
            scanner.close();
            return;
        }

        String[] colunas = new String[numColunas];
        for (int i = 0; i < numColunas; i++) {
            System.out.println("Informe o nome da coluna " + (i + 1) + ":");
            colunas[i] = scanner.nextLine();
        }

        System.out.println("Informe o número de linhas:");
        int numLinhas = scanner.nextInt();
        scanner.nextLine(); // Consumir a linha pendente

        String[][] dados = new String[numLinhas][numColunas];

        for (int i = 0; i < numLinhas; i++) {
            System.out.println("Inserindo dados para a linha " + (i + 1) + ":");
            for (int j = 0; j < numColunas; j++) {
                System.out.println("Informe o valor para a coluna '" + colunas[j] + "':");
                dados[i][j] = scanner.nextLine();
            }
        }

        try (FileWriter csvWriter = new FileWriter("planilha.csv")) {
            // Escreve os nomes das colunas
            for (int i = 0; i < numColunas; i++) {
                csvWriter.append(colunas[i]);
                if (i < numColunas - 1) {
                    csvWriter.append(","); // Adiciona vírgula entre as colunas
                }
            }
            csvWriter.append("\n");

            // Escreve os dados das linhas
            for (int i = 0; i < numLinhas; i++) {
                for (int j = 0; j < numColunas; j++) {
                    csvWriter.append(dados[i][j]);
                    if (j < numColunas - 1) {
                        csvWriter.append(","); // Adiciona vírgula entre os dados
                    }
                }
                csvWriter.append("\n");
            }

            System.out.println("Arquivo CSV criado com sucesso!");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao criar o arquivo.");
            e.printStackTrace();
        }

        scanner.close(); // Fechar o scanner
    }
}
