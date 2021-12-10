package br.com.banco.devinhouse.conta;

import br.com.banco.devinhouse.enumerators.AgenciaEnum;
import br.com.banco.devinhouse.enumerators.TipoOperacoesEnum;

public class ContaCorrente extends Conta {
    private double chequeEspecial;

    public ContaCorrente(String nome, String cpf, double rendaMensal, AgenciaEnum agencia) {
        super(nome, cpf, rendaMensal, agencia);
        this.chequeEspecial = rendaMensal * 0.5;
    }

    @Override
    public boolean sacar(double valor) {
        if (valor > (this.getSaldo() + this.chequeEspecial)) {
            System.out.println("Saldo insuficiente");
            return false;
        }
        this.setSaldo(this.getSaldo() - valor);
        System.out.println("Saque realizado com sucesso!");
        new LogOperacoes(this, this, valor, TipoOperacoesEnum.SAQUE);
        return true;
    }
}
