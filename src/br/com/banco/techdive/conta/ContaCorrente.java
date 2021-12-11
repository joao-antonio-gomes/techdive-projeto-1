package br.com.banco.techdive.conta;

import br.com.banco.techdive.enumerators.AgenciaEnum;
import br.com.banco.techdive.enumerators.TipoOperacoesEnum;
import br.com.banco.techdive.menu.Utils;

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
        Utils.separaLinha();
        System.out.println("Saque realizado com sucesso!");
        System.out.println("\n\n");
        LogOperacoes logOperacoes = new LogOperacoes(this, this, valor, TipoOperacoesEnum.SAQUE);
        logOperacoes.toString();
        return true;
    }
}
