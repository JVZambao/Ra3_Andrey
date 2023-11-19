package Main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Tamanhos das tabelas hash
        int[] tamanhos = {10, 100, 1000, 10000, 100000};

        // Tamanhos dos conjuntos de dados
        int[] tamanhosConjuntos = {20000, 100000, 500000, 1000000, 5000000};

        // Gerando conjuntos de dados aleatórios
        Random random = new Random();

        try {
            FileWriter csvWriter = new FileWriter("resultados.csv");
            csvWriter.append("Tamanho da Tabela,Tamanho do Conjunto,");
            csvWriter.append("Tempo de Inserção Divisão (ms),Tempo de Busca Médio Divisão (ms),Colisões Inserção Divisão,Colisões Busca Divisão,");
            csvWriter.append("Tempo de Inserção Multiplicação (ms),Tempo de Busca Médio Multiplicação (ms),Colisões Inserção Multiplicação,Colisões Busca Multiplicação,");
            csvWriter.append("Tempo de Inserção Dobramento (ms),Tempo de Busca Médio Dobramento (ms),Colisões Inserção Dobramento,Colisões Busca Dobramento\n");

            for (int tamanhoConjunto : tamanhosConjuntos) {
                Registro[] conjunto = new Registro[tamanhoConjunto];
                for (int i = 0; i < tamanhoConjunto; i++) {
                    int codigo = random.nextInt(900000000) + 100000000;
                    conjunto[i] = new Registro(codigo);
                }

                for (int tamanho : tamanhos) {
                    TabelaHashDivisao tabelaDivisao = new TabelaHashDivisao(tamanho);
                    medirDesempenho(tamanho, tamanhoConjunto, conjunto, tabelaDivisao, random, csvWriter);

                    TabelaHashMultiplicacao tabelaMultiplicacao = new TabelaHashMultiplicacao(tamanho);
                    medirDesempenho(tamanho, tamanhoConjunto, conjunto, tabelaMultiplicacao, random, csvWriter);

                    TabelaHashDobramento tabelaDobramento = new TabelaHashDobramento(tamanho);
                    medirDesempenho(tamanho, tamanhoConjunto, conjunto, tabelaDobramento, random, csvWriter);
                }
            }

            csvWriter.close();
            System.out.println("Performance data written to resultados.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void medirDesempenho(int tamanho, int tamanhoConjunto, Registro[] conjunto, TabelaHashDivisao tabelaHash,
            Random random, FileWriter csvWriter) throws IOException {
        long startTime = System.currentTimeMillis();
        for (Registro registro : conjunto) {
            tabelaHash.inserir(registro);
        }
        long endTime = System.currentTimeMillis();
        long tempoInsercao = endTime - startTime;
        int colisoesInsercaoDivisao = tabelaHash.getColisoesInsercao();
        int colisoesBuscaDivisao = tabelaHash.getColisoesBusca();

        // Medindo o tempo de busca e contando comparações
        int numBuscas = 5;
        startTime = System.currentTimeMillis();
        for (int j = 0; j < numBuscas; j++) {
            int codigoBusca = conjunto[random.nextInt(tamanhoConjunto)].getCodigo();
            tabelaHash.buscar(codigoBusca);
        }
        endTime = System.currentTimeMillis();
        long tempoBusca = endTime - startTime;

        // Escrevendo no arquivo CSV
        csvWriter.append(tamanho + ",");
        csvWriter.append(tamanhoConjunto + ",");
        csvWriter.append(tempoInsercao + ",");
        csvWriter.append(tempoBusca / numBuscas + ",");
        csvWriter.append(colisoesInsercaoDivisao + ",");
        csvWriter.append(colisoesBuscaDivisao + ",");
    }

    private static void medirDesempenho(int tamanho, int tamanhoConjunto, Registro[] conjunto, TabelaHashMultiplicacao tabelaHash,
            Random random, FileWriter csvWriter) throws IOException {
        long startTime = System.currentTimeMillis();
        for (Registro registro : conjunto) {
            tabelaHash.inserir(registro);
        }
        long endTime = System.currentTimeMillis();
        long tempoInsercao = endTime - startTime;
        int colisoesInsercaoMultiplicacao = tabelaHash.getColisoesInsercao();
        int colisoesBuscaMultiplicacao = tabelaHash.getColisoesBusca();

        // Medindo o tempo de busca e contando comparações
        int numBuscas = 5;
        startTime = System.currentTimeMillis();
        for (int j = 0; j < numBuscas; j++) {
            int codigoBusca = conjunto[random.nextInt(tamanhoConjunto)].getCodigo();
            tabelaHash.buscar(codigoBusca);
        }
        endTime = System.currentTimeMillis();
        long tempoBusca = endTime - startTime;

        // Escrevendo no arquivo CSV
        csvWriter.append(tempoInsercao + ",");
        csvWriter.append(tempoBusca / numBuscas + ",");
        csvWriter.append(colisoesInsercaoMultiplicacao + ",");
        csvWriter.append(colisoesBuscaMultiplicacao + ",");
    }

    private static void medirDesempenho(int tamanho, int tamanhoConjunto, Registro[] conjunto, TabelaHashDobramento tabelaHash,
            Random random, FileWriter csvWriter) throws IOException {
        long startTime = System.currentTimeMillis();
        for (Registro registro : conjunto) {
            tabelaHash.inserir(registro);
        }
        long endTime = System.currentTimeMillis();
        long tempoInsercao = endTime - startTime;
        int colisoesInsercaoDobramento = tabelaHash.getColisoesInsercao();
        int colisoesBuscaDobramento = tabelaHash.getColisoesBusca();

        // Medindo o tempo de busca e contando comparações
        int numBuscas = 5;
        startTime = System.currentTimeMillis();
        for (int j = 0; j < numBuscas; j++) {
            int codigoBusca = conjunto[random.nextInt(tamanhoConjunto)].getCodigo();
            tabelaHash.buscar(codigoBusca);
        }
        endTime = System.currentTimeMillis();
        long tempoBusca = endTime - startTime;

        // Escrevendo no arquivo CSV
        csvWriter.append(tempoInsercao + ",");
        csvWriter.append(tempoBusca / numBuscas + ",");
        csvWriter.append(colisoesInsercaoDobramento + ",");
        csvWriter.append(colisoesBuscaDobramento + "\n");
    }
}
