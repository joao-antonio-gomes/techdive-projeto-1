package br.com.banco.devinhouse.conta;

import br.com.banco.devinhouse.enumerators.AgenciaEnum;

public class ContaPoupanca extends Conta {
    private final double jurosRentabilidade = 0.03;

    public ContaPoupanca(String nome, String cpf, double rendaMensal, AgenciaEnum agencia) {
        super(nome, cpf, rendaMensal, agencia);
    }

    public void calculaRentabilidadePorNumeroDeMeses(int meses) {
        double juros = this.getSaldo() * (jurosRentabilidade * meses);
        String jurosFormatado = String.format("%.2f", juros);
        double saldoAtual = this.getSaldo();
        String saldoAtualFormatado = String.format("%.2f", saldoAtual);
        double saldoFinal = saldoAtual + juros;
        String saldoFinalFormatado = String.format("%.2f", saldoFinal);
        System.out.println("Saldo atual: R$ " + saldoAtualFormatado);
        System.out.println("Juros no período de " + meses + " meses: R$ " + jurosFormatado);
        System.out.println("Saldo final depois de " + meses + " meses: R$ " + saldoFinalFormatado);
    }
}
