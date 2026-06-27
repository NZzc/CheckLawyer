package Exception;

public class FormatoInvalidoException extends Exception {
    public FormatoInvalidoException(String campo, String formatoEsperado) {
        super("Formato inválido para o campo \"" + campo + "\". Esperado: " + formatoEsperado);
    }
}
