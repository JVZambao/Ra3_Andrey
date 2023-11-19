package Main;

import java.util.List;
import java.util.Objects;

public class TabelaHashDobramento {
    private Object[] tabela;
    private int tamanho;

    public TabelaHashDobramento(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new Object[tamanho];
    }

    private int hash(int codigo) {
        String strCodigo = String.valueOf(codigo);
        int meio = strCodigo.length() / 2;
        String parte1 = strCodigo.substring(0, meio);
        String parte2 = strCodigo.substring(meio);
        return Integer.parseInt(parte1) + Integer.parseInt(parte2);
    }

    public void inserir(Registro registro) {
        int posicao = hash(registro.getCodigo());

        if (tabela[posicao] == null) {
            tabela[posicao] = registro;
        } else {
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
                return ((ListaColisao) tabela[posicao]).buscar(codigo);
            } else {
                return tabela[posicao].equals(new Registro(codigo));
            }
        }

        return false;
    }
}
