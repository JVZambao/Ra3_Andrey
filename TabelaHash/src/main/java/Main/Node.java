package Main;

public class Node {
    private Registro valor;
    private Node anterior;
    private Node proximo;

    public Node (){}

    public Registro getValor() {
        return valor;
    }

    public void setValor(Registro valor) {
        this.valor = valor;
    }

    public Node getAnterior() {
        return anterior;
    }

    public void setAnterior(Node anterior) {
        this.anterior = anterior;
    }

    public Node getProximo() {
        return proximo;
    }

    public void setProximo(Node proximo) {
        this.proximo = proximo;
    }
}
