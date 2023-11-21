package Main;

public class TabelaHashDivisao {
    private ListaEncadeada[] tabela;
    private int tamanho;
    private int colisoesInsercao;
    private int colisoesBusca;

    public TabelaHashDivisao(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new ListaEncadeada[tamanho];
        this.colisoesInsercao = 0;
        this.colisoesBusca = 0;
    }

    private int hash(int codigo) {
        return codigo % tamanho;
    }

    public void inserir(Registro registro) {
        int posicao = hash(registro.getCodigo());

        if (tabela[posicao] == null) {
            tabela[posicao] = new ListaEncadeada();
            tabela[posicao].add(registro);
        } else {
            colisoesInsercao++;
            tabela[posicao].add(registro);
        }
    }

    public boolean buscar(int codigo) {
        int posicao = hash(codigo);
        return (tabela[posicao].buscar(codigo) != null);
    }

    public int getColisoesBusca() {
        return colisoesBusca;
    }

    public int getColisoesInsercao() {
        return colisoesInsercao;
    }
}
