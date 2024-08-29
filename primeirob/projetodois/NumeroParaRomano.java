package primeirob.projetodois;

public class NumeroParaRomano {
    
    public static String converteParaRomano(int numero) {
        if (numero < 1 || numero > 3999) {
            throw new IllegalArgumentException("O número deve estar entre 1 e 3999.");
        }

        String[] milhares = {"", "M", "MM", "MMM"};
        String[] centenas = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] dezenas = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] unidades = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return milhares[numero / 1000] +
                centenas[(numero % 1000) / 100] +
                dezenas[(numero % 100) / 10] +
                unidades[numero % 10];
    }
}
