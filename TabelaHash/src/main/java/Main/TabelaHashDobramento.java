package Main;

public class TabelaHashDobramento {
    private ListaEncadeada[] tabela;
    private int tamanho;
    private int colisoesInsercao;
    private int colisoesBusca;

    public TabelaHashDobramento(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new ListaEncadeada[tamanho];
        this.colisoesInsercao = 0;
        this.colisoesBusca = 0;
    }

    private int hash(int codigo) {
        String strCodigo = String.valueOf(codigo);
        int meio = strCodigo.length() / 2;
        String parte1 = strCodigo.substring(0, meio);
        String parte2 = strCodigo.substring(meio);
        int hash = (Integer.parseInt(parte1) + Integer.parseInt(parte2)) % tamanho;
        return hash;
    }

    public void inserir(Registro registro) {
        int posicao = hash(registro.getCodigo());

        if (tabela[posicao] == null) {
            System.out.println("Primeira inserção |" + tamanho + " : Pos | " + posicao);
            tabela[posicao] = new ListaEncadeada();
            tabela[posicao].add(registro);
        } else {
//            System.out.println("Existe" + tabela[posicao].getHead().getValor().getCodigo());
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
