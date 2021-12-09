package br.com.banco.devinhouse.conta;

import br.com.banco.devinhouse.enumerators.AgenciaEnum;
import br.com.banco.devinhouse.enumerators.InvetimentosEnum;

public class ContaInvestimento extends Conta {
    private InvetimentosEnum tipoInvestimento;

    public ContaInvestimento(String nome, String cpf, double rendaMensal, AgenciaEnum agencia, InvetimentosEnum tipoInvestimento) {
        super(nome, cpf, rendaMensal, agencia);
        this.tipoInvestimento = tipoInvestimento;
    }

    public String getTipoInvestimento() {
        return tipoInvestimento.getDescricao() + " - " + tipoInvestimento.getTaxa();
    }

    public void calculaRendimentoAnual() {
        double rendimentoAnual = this.getSaldo() * (this.tipoInvestimento.getTaxa() * 12);
        String rendimentoAnualFormatado = String.format("%.2f", rendimentoAnual);
        System.out.println("O rendimento anual do seu investimento é de: R$ " + rendimentoAnualFormatado);
    }


}
