package br.com.lhos.wsassemblyvotemanager.enumeration;

public enum VotoSituacaoEnum {
    SIM("SIM"), NAO("NAO");

    private String value;

    VotoSituacaoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
