package br.com.lhos.wsassemblyvotemanager.exception;

public class AssociadoNaoExisteEx extends Exception{
    private static final long serialVersionUID = 1L;

    public AssociadoNaoExisteEx(String message) {
        super(message);
    }
}
