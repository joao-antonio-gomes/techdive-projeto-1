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

    public double getChequeEspecial() {
        return chequeEspecial;
    }
}
