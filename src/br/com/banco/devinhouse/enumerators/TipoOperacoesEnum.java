package br.com.banco.devinhouse.enumerators;

public enum TipoOperacoesEnum {
    SAQUE("Saque", "001"),
    DEPOSITO("Depósito", "002"),
    TRANSFERENCIA("Transferência", "003");

    private String descricao;
    private String codigo;

    private TipoOperacoesEnum(String descricao, String codigo) {
        this.descricao = descricao;
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
