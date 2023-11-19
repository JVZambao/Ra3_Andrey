package Main;

import java.util.Objects;

public class Registro {
    private int codigo;

    public Registro(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registro registro = (Registro) o;
        return codigo == registro.codigo;
    }

    @Override
    public int hashCode() {
        // Implementação manual do hashCode
        return Objects.hash(codigo);
    }
}
