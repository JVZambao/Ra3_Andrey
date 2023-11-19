package Main;

import java.util.LinkedList;
import java.util.List;

public class ListaColisao extends LinkedList<Registro> {
    public boolean buscar(int codigo) {
        for (Registro registro : this) {
            if (registro.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    public void adicionar(Registro registro) {
        add(registro);
    }
}
