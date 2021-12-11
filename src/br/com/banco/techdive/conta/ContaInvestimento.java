package br.com.banco.techdive.conta;

import br.com.banco.techdive.enumerators.AgenciaEnum;
import br.com.banco.techdive.enumerators.InvetimentosEnum;

public class ContaInvestimento extends Conta {
    private InvetimentosEnum tipoInvestimento;

    public ContaInvestimento(String nome, String cpf, double rendaMensal, AgenciaEnum agencia, InvetimentosEnum tipoInvestimento) {
        super(nome, cpf, rendaMensal, agencia);
        this.tipoInvestimento = tipoInvestimento;
    }

    public String getTipoInvestimento() {
        return "Seu investimento é do tipo " + tipoInvestimento.getDescricao() + ", com taxa de " + tipoInvestimento.getTaxa() * 100 + "% ao ano.";
    }

    public void calculaRendimentoAnual() {
        double rendimentoAnual = this.getSaldo() * (this.tipoInvestimento.getTaxa() * 12);
        String rendimentoAnualFormatado = String.format("%.2f", rendimentoAnual);
        System.out.println("O rendimento anual do seu investimento é de: R$ " + rendimentoAnualFormatado);
    }


}
