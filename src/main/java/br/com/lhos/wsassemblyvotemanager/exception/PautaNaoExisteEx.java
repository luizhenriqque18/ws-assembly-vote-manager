package br.com.lhos.wsassemblyvotemanager.exception;

public class PautaNaoExisteEx extends Exception {
    private static final long serialVersionUID = 1L;

    public PautaNaoExisteEx() {
        super();
    }

    public PautaNaoExisteEx(String message) {
        super(message);
    }

    public PautaNaoExisteEx(String message, Throwable cause) {
        super(message, cause);
    }


}
