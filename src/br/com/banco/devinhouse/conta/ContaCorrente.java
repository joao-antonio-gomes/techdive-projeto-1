package br.com.banco.devinhouse.conta;

import br.com.banco.devinhouse.enumerators.AgenciaEnum;
import br.com.banco.devinhouse.exceptions.TransacaoException;

public class ContaCorrente extends Conta {
    private double chequeEspecial;

    public ContaCorrente(String nome, String cpf, double rendaMensal, AgenciaEnum agencia) {
        super(nome, cpf, rendaMensal, agencia);
        this.chequeEspecial = rendaMensal * 0.5;
    }

    @Override
    public void sacar(double valor) throws TransacaoException {
        if (valor > (this.getSaldo() + this.chequeEspecial)) {
            throw new TransacaoException("Saldo insuficiente");
        }
        this.sacar(this.getSaldo() - valor);
    }
}
