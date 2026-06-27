package Exception;

public class SelecionarItemException extends Exception {
    public SelecionarItemException(String entidade) {
        super("Selecione um(a) " + entidade + " na tabela antes de continuar.");
    }
}