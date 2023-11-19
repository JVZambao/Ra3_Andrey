package Main;

import java.util.List;
import java.util.Objects;

public class TabelaHashDivisao {
    private Object[] tabela; // Usar Object para permitir tanto Registro quanto ListaColisao
    private int tamanho;

    public TabelaHashDivisao(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new Object[tamanho];
    }

    private int hash(int codigo) {
        return codigo % tamanho;
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
