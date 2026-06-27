package Exception;

public class RegistroDuplicadoException extends Exception {
    public RegistroDuplicadoException(String campo, String valor) {
        super("Já existe um registro com " + campo + " \"" + valor + "\". Informe outro valor.");
    }
}
