package br.com.banco.devinhouse.enumerators;

public enum AgenciaEnum {
    FLORIANOPOLIS("001", "Florianópolis"),
    SAO_JOSE("002", "São José");

    private final String codigo;
    private final String cidade;

    private AgenciaEnum(String codigo, String cidade) {
        this.codigo = codigo;
        this.cidade = cidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCidade() {
        return cidade;
    }
}
