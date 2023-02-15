package br.com.lhos.wsassemblyvotemanager.exception;


/**
 * Classe que implementa a SessaoVotacaoNãoExisteEx na API
 *
 * @author Luiz Souza
 * @since 15/02/2023
 */
public class SessaoVotacaoNaoExisteEx extends Exception {

    private static final long serialVersionUID = 1L;

    public SessaoVotacaoNaoExisteEx() {
        super();
    }

    public SessaoVotacaoNaoExisteEx(String message) {
        super(message);
    }

    public SessaoVotacaoNaoExisteEx(String message, Throwable cause) {
        super(message, cause);
    }

}
