package Exception;

public class ValorNegativoException extends Exception {
    public ValorNegativoException(String campo) {
        super("O campo \"" + campo + "\" deve ser um valor positivo maior que zero.");
    }
}
