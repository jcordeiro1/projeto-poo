package primeirob.projetodois;

import java.util.HashMap;

public class ConversorMoedas {

    private static final HashMap<String, Double> taxasConversao = new HashMap<>();

    static {
        taxasConversao.put("USD", 1.00);
        taxasConversao.put("EUR", 0.85);
        taxasConversao.put("JPY", 110.0);
        taxasConversao.put("GBP", 0.75);
        taxasConversao.put("BRL", 5.25);
    }

    public static double converteMoeda(String de, String para, double valor) {
        if (!taxasConversao.containsKey(de) || !taxasConversao.containsKey(para)) {
            throw new IllegalArgumentException("Moeda inválida.");
        }
        double valorEmUsd = valor / taxasConversao.get(de);
        return valorEmUsd * taxasConversao.get(para);
    }
}
