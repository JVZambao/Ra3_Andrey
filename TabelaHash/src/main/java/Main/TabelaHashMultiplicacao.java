package Main;

import java.util.Objects;

public class TabelaHashMultiplicacao {
    private Object[] tabela;
    private int tamanho;
    private int colisoesInsercao;
    private int colisoesBusca;

    public TabelaHashMultiplicacao(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new Object[tamanho];
        this.colisoesInsercao = 0;
        this.colisoesBusca = 0;
    }

    private int hash(int codigo) {
        double A = 0.618;
        double resultado = A * codigo;
        return (int) Math.floor(tamanho * (resultado - Math.floor(resultado)));
    }

    public void inserir(Registro registro) {
        int posicao = hash(registro.getCodigo());

        if (tabela[posicao] == null) {
            tabela[posicao] = registro;
        } else {
            colisoesInsercao++;
            if (tabela[posicao] instanceof ListaColisao) {
                ((ListaColisao) tabela[posicao]).adicionar(registro);
            } else {
                ListaColisao listaColisao = new ListaColisao();
                listaColisao.adicionar((Registro) tabela[posicao]);
                listaColisao.adicionar(registro);
                tabela[posicao] = listaColisao;
            }
        }
    }

    public boolean buscar(int codigo) {
        int posicao = hash(codigo);

        if (tabela[posicao] != null) {
            if (tabela[posicao] instanceof ListaColisao) {
                colisoesBusca++;
                return ((ListaColisao) tabela[posicao]).buscar(codigo);
            } else {
                return tabela[posicao].equals(new Registro(codigo));
            }
        }

        return false;
    }

    public int getColisoesBusca() {
        return colisoesBusca;
    }

    public int getColisoesInsercao() {
        return colisoesInsercao;
    }
}
