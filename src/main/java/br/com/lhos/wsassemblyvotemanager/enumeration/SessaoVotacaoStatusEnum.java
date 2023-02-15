package br.com.lhos.wsassemblyvotemanager.enumeration;


/**
 * Enum que classifica o estatu do @{@link br.com.lhos.wsassemblyvotemanager.domain.SessaoVotacao}.
 * @author Luiz Souza
 * @since 14/02/2023
 */
public enum SessaoVotacaoStatusEnum {
    PENDING("PENDING"), OPEN("OPEN"), CLOSED("CLOSED"), CANCELED("CANCELED");

    private String value;

    SessaoVotacaoStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
