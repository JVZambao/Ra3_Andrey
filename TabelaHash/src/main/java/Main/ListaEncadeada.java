package Main;

public class ListaEncadeada {
    private Node head;
    private int length;
    public ListaEncadeada(){
        this.length = 0;
    }
    public void add(Registro registro) {
        if(this.head == null) {
            Node n = new Node();
            n.setValor(registro);
            this.head = n;
        } else {
            Node n = new Node();
            n.setValor(registro);
            n.setProximo(this.head);
            this.head = n;
        }
        this.length++;
    }

    public Registro buscar(int codigo) {
        Node h = this.head;
        while(h.getValor().getCodigo() != codigo) {
          h = h.getProximo();
        }
        return h.getValor();
    }

    public Node getHead() {
        return head;
    }
}
