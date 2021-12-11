package br.com.banco.techdive.enumerators;

public enum InvetimentosEnum {
    TESOURO_DIRETO(1, "Tesouro Direto", 0.0799),
    CDB(2, "Certificado de Depósito Bancário", 0.0714),
    LCI(3, "Letra de Crédito Imobiliário", 0.09);

    private final double taxa;
    private final int codigo;
    private final String descricao;

    private InvetimentosEnum(int codigo, String descricao, double taxa) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.taxa = taxa;
    }

    public double getTaxa() {
        return taxa;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
