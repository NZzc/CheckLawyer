package Exception;

public class CampoVazioException extends Exception {
    public CampoVazioException(String campo) {
        super("O campo \"" + campo + "\" é obrigatório e não pode estar vazio.");
    }
}
