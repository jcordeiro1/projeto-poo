package primeirob.projetodois;

import java.util.Scanner;

public class SistemaConversor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Sistema Conversor!");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Converter número inteiro para romano");
        System.out.println("2. Converter moeda");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                System.out.println("Digite o número inteiro (1-3999): ");
                int numero = scanner.nextInt();
                try {
                    String romano = NumeroParaRomano.converteParaRomano(numero);
                    System.out.println("O número romano correspondente é: " + romano);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                scanner.nextLine(); // Consumir a linha pendente
                System.out.println("Digite a moeda de origem (USD, EUR, JPY, GBP, BRL): ");
                String de = scanner.nextLine().toUpperCase();
                System.out.println("Digite a moeda de destino (USD, EUR, JPY, GBP, BRL): ");
                String para = scanner.nextLine().toUpperCase();
                System.out.println("Digite o valor a ser convertido: ");
                double valor = scanner.nextDouble();
                try {
                    double resultado = ConversorMoedas.converteMoeda(de, para, valor);
                    System.out.printf("O valor convertido é: %.2f %s\n", resultado, para);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println("Opção inválida.");
        }

        scanner.close();
    }
}
