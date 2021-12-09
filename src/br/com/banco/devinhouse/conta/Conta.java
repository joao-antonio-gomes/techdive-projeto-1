package br.com.banco.devinhouse.conta;

import br.com.banco.devinhouse.cpf.Cpf;
import br.com.banco.devinhouse.database.ContaDB;
import br.com.banco.devinhouse.enumerators.AgenciaEnum;
import br.com.banco.devinhouse.exceptions.TransacaoException;

public abstract class Conta {
    private static int numeroContas = 0;
    private String nome;
    private Cpf cpf;
    private double rendaMensal;
    private int conta;
    private AgenciaEnum agencia;
    private double saldo;

    public Conta(String nome, String cpf, double rendaMensal, AgenciaEnum agencia) {
        this.nome = nome;
        this.cpf = new Cpf(cpf);
        this.rendaMensal = rendaMensal;
        this.agencia = agencia;
        this.saldo = 0;
        this.conta = ++numeroContas;

        ContaDB.addConta(this);
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf.getCpf();
    }

    public double getRendaMensal() {
        return rendaMensal;
    }

    public String getAgencia() {
        return agencia.getCodigo() + " - " + agencia.getCidade();
    }

    public double getSaldo() {
        return saldo;
    }

    public int getConta() {
        return conta;
    }

    public void obterDadosCadastrais() {
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.println("Renda Mensal: " + this.rendaMensal);
        System.out.println("Conta: " + this.conta);
        System.out.println("Agencia: " + this.agencia);
        System.out.println("Saldo: " + this.saldo);
    }

    public void depositar(double valor) throws TransacaoException {
        if (valor < 0) {
            throw new TransacaoException("Por favor, informe um valor acima de zero!");
        }
        this.saldo += valor;
        System.out.println("Depósito realizado com sucesso!");
        new LogOperacoes(this, this, valor);
    }

    public void sacar(double valor) throws TransacaoException {
        if (valor < 0) {
            throw new TransacaoException("Por favor, informe um valor acima de zero!");
        }
        if (this.saldo < valor) {
            throw new TransacaoException("Saldo insuficiente!");
        }
        this.saldo -= valor;
        System.out.println("Saque realizado com sucesso!");
        new LogOperacoes(this, this, valor);
    }

    public void transferir(Conta contaDestino, double valor) throws TransacaoException {
        if (valor < 0) {
            throw new TransacaoException("Por favor, informe um valor acima de zero!");
        }
        if (this.saldo < valor) {
            throw new TransacaoException("Saldo insuficiente!");
        }
        this.sacar(valor);
        contaDestino.depositar(valor);
        System.out.println("Transferência realizada com sucesso!");
        new LogOperacoes(this, contaDestino, valor);
    }

    public void extrato() {
        System.out.println("Extrato:");
        String saldo = String.format("%.2f", this.saldo);
        System.out.println("Saldo: R$ " + saldo);
    }

    public void alterarDadosCadastrais(int escolha, String novoValor) throws TransacaoException {
        switch (escolha) {
            case 1:
                this.nome = novoValor;
                break;
            case 2:
                this.rendaMensal = Double.parseDouble(novoValor);
                break;
            case 3:
                int valor = Integer.parseInt(novoValor);
                this.agencia = AgenciaEnum.values()[valor];
                ;
                break;
            default:
                throw new TransacaoException("Opção inválida!");
        }
        System.out.println("Dados alterados com sucesso!");
    }


    @Override
    public String toString() {
        return "Titular : " + this.nome + "\nCPF : " + this.cpf + "\nRenda Mensal : " + this.rendaMensal + "\nConta : " + this.conta + "\nAgencia : " + this.agencia + "\nSaldo : " + this.saldo;
    }
}
