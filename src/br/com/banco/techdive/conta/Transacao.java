package br.com.banco.techdive.conta;

import br.com.banco.techdive.enumerators.TipoOperacoesEnum;
import br.com.banco.techdive.menu.Utils;

import java.util.Date;

public class Transacao {
    private Conta contaOrigem;
    private Conta contaDestino;
    private double valor;
    private TipoOperacoesEnum tipoOperacao;
    private String dataFormatada;
    private Date data;

    public Transacao(Conta contaOrigem, Conta contaDestino, double valor, TipoOperacoesEnum tipoOperacao) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.tipoOperacao = tipoOperacao;
        this.data = new Date();
        criaTransacao();
    }

    public Transacao(Conta contaOrigem, double valor, TipoOperacoesEnum tipoOperacao) {
        this.contaOrigem = contaOrigem;
        this.valor = valor;
        this.tipoOperacao = tipoOperacao;
        this.data = new Date();
        criaTransacao();
    }

    private void criaTransacao() {
    	switch (tipoOperacao) {
            case DEPOSITO:
                depositar();
                break;
            case SAQUE:
                sacar();
                break;
            case TRANSFERENCIA:
                transferir();
                break;
        }
    }

    private void depositar() {
        if (valor < 0) {
            System.out.println("Por favor, informe um valor acima de zero!");
            return;
        }
        contaOrigem.setSaldo(contaOrigem.getSaldo() + valor);
        Utils.separaLinha();
        System.out.println("Depósito realizado com sucesso!");
        LogOperacoes logOperacoes = new LogOperacoes(contaOrigem, contaOrigem, valor, TipoOperacoesEnum.DEPOSITO);
        System.out.print("\n");
        System.out.println(logOperacoes.toString());
    }

    private void sacar() {
        if (!isValorValido()) {
            return;
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
        Utils.separaLinha();
        System.out.println("Saque realizado com sucesso!");
        LogOperacoes logOperacoes = new LogOperacoes(contaOrigem, contaOrigem, valor, tipoOperacao);
        System.out.print("\n");
        System.out.println(logOperacoes.toString());
    }

    private void transferir() {
        if (!isValorValido()) {
            return;
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
        contaDestino.setSaldo(contaDestino.getSaldo() + valor);
        Utils.separaLinha();
        System.out.println("Transferência realizada com sucesso!\n");
        LogOperacoes logOperacoes = new LogOperacoes(contaOrigem, contaDestino, valor, tipoOperacao);
        System.out.print("\n");
        System.out.println(logOperacoes.toString());
    }

    private boolean isValorValido() {
        if (valor < 0) {
            System.out.println("Por favor, informe um valor acima de zero!");
            return false;
        }
        if (contaOrigem instanceof ContaCorrente && valor > (contaOrigem.getSaldo() + ((ContaCorrente) contaOrigem).getChequeEspecial())) {
            System.out.println("Saldo insuficiente");
            return false;
        }
        if (contaOrigem.getSaldo() < valor) {
            System.out.println("Saldo insuficiente!");
            return false;
        }

        return true;
    }
}
