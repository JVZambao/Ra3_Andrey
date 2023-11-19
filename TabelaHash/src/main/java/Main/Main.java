package Main;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Tamanhos das tabelas hash
        int[] tamanhos = {10, 100, 1000, 10000, 100000};

        // Tamanhos dos conjuntos de dados
        int[] tamanhosConjuntos = {20000, 100000, 500000, 1000000, 5000000};

        // Gerando conjuntos de dados aleatórios
        Random random = new Random();
        for (int tamanhoConjunto : tamanhosConjuntos) {
            Registro[] conjunto = new Registro[tamanhoConjunto];
            for (int i = 0; i < tamanhoConjunto; i++) {
                int codigo = random.nextInt(900000000) + 100000000; // Gerando códigos de 9 dígitos
                conjunto[i] = new Registro(codigo);
            }

            for (int tamanho : tamanhos) {
                // Tabela Hash com tratamento de colisões por divisão
                TabelaHashDivisao tabelaDivisao = new TabelaHashDivisao(tamanho);
                medirDesempenho(tamanho, tamanhoConjunto, conjunto, tabelaDivisao, random);

                // Tabela Hash com tratamento de colisões por multiplicação
                TabelaHashMultiplicacao tabelaMultiplicacao = new TabelaHashMultiplicacao(tamanho);
                medirDesempenho(tamanho, tamanhoConjunto, conjunto, tabelaMultiplicacao, random);

                // Tabela Hash com tratamento de colisões por dobramento
                TabelaHashDobramento tabelaDobramento = new TabelaHashDobramento(tamanho);
                medirDesempenho(tamanho, tamanhoConjunto, conjunto, tabelaDobramento, random);
            }
        }
    }

    private static void medirDesempenho(int tamanho, int tamanhoConjunto, Registro[] conjunto,
                                        TabelaHashDivisao tabelaHash, Random random) {
        long startTime = System.currentTimeMillis();
        for (Registro registro : conjunto) {
            tabelaHash.inserir(registro);
        }
        long endTime = System.currentTimeMillis();
        long tempoInsercao = endTime - startTime;

        // Medindo o tempo de busca e contando comparações
        int numBuscas = 5;
        startTime = System.currentTimeMillis();
        for (int j = 0; j < numBuscas; j++) {
            int codigoBusca = conjunto[random.nextInt(tamanhoConjunto)].getCodigo();
            tabelaHash.buscar(codigoBusca);
        }
        endTime = System.currentTimeMillis();
        long tempoBusca = endTime - startTime;

        // Exibindo resultados
        System.out.println("Tamanho da tabela (Divisão): " + tamanho);
        System.out.println("Tamanho do conjunto: " + tamanhoConjunto);
        System.out.println("Tempo de inserção: " + tempoInsercao + " ms");
        System.out.println("Tempo de busca médio: " + (tempoBusca / numBuscas) + " ms");
        System.out.println("-----");
    }

    private static void medirDesempenho(int tamanho, int tamanhoConjunto, Registro[] conjunto,
                                        TabelaHashMultiplicacao tabelaHash, Random random) {
        long startTime = System.currentTimeMillis();
        for (Registro registro : conjunto) {
            tabelaHash.inserir(registro);
        }
        long endTime = System.currentTimeMillis();
        long tempoInsercao = endTime - startTime;

        // Medindo o tempo de busca e contando comparações
        int numBuscas = 5;
        startTime = System.currentTimeMillis();
        for (int j = 0; j < numBuscas; j++) {
            int codigoBusca = conjunto[random.nextInt(tamanhoConjunto)].getCodigo();
            tabelaHash.buscar(codigoBusca);
        }
        endTime = System.currentTimeMillis();
        long tempoBusca = endTime - startTime;

        // Exibindo resultados
        System.out.println("Tamanho da tabela (Multiplicação): " + tamanho);
        System.out.println("Tamanho do conjunto: " + tamanhoConjunto);
        System.out.println("Tempo de inserção: " + tempoInsercao + " ms");
        System.out.println("Tempo de busca médio: " + (tempoBusca / numBuscas) + " ms");
        System.out.println("-----");
    }

    private static void medirDesempenho(int tamanho, int tamanhoConjunto, Registro[] conjunto,
                                        TabelaHashDobramento tabelaHash, Random random) {
        long startTime = System.currentTimeMillis();
        for (Registro registro : conjunto) {
            tabelaHash.inserir(registro);
        }
        long endTime = System.currentTimeMillis();
        long tempoInsercao = endTime - startTime;

        // Medindo o tempo de busca e contando comparações
        int numBuscas = 5;
        startTime = System.currentTimeMillis();
        for (int j = 0; j < numBuscas; j++) {
            int codigoBusca = conjunto[random.nextInt(tamanhoConjunto)].getCodigo();
            tabelaHash.buscar(codigoBusca);
        }
        endTime = System.currentTimeMillis();
        long tempoBusca = endTime - startTime;

        // Exibindo resultados
        System.out.println("Tamanho da tabela (Dobramento): " + tamanho);
        System.out.println("Tamanho do conjunto: " + tamanhoConjunto);
        System.out.println("Tempo de inserção: " + tempoInsercao + " ms");
        System.out.println("Tempo de busca médio: " + (tempoBusca / numBuscas) + " ms");
        System.out.println("-----");
    }
}
