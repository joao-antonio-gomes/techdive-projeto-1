package br.com.banco.devinhouse.conta;

import br.com.banco.devinhouse.cpf.Cpf;
import br.com.banco.devinhouse.database.ContaDB;
import br.com.banco.devinhouse.enumerators.AgenciaEnum;
import br.com.banco.devinhouse.enumerators.TipoOperacoesEnum;

public abstract class Conta {
    private static int numeroContas = 0;
    private String nome;
    private Cpf cpf;
    private double rendaMensal;
    private int numeroConta;
    private AgenciaEnum agencia;
    private double saldo;

    public Conta(String nome, String cpf, double rendaMensal, AgenciaEnum agencia) {
        this.nome = nome;
        this.cpf = new Cpf(cpf);
        this.rendaMensal = rendaMensal;
        this.agencia = agencia;
        this.saldo = 0;
        this.numeroConta = ++numeroContas;

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

    public int getNumeroConta() {
        return numeroConta;
    }

    public void obterDadosCadastrais() {
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.println("Renda Mensal: " + this.rendaMensal);
        System.out.println("Conta: " + this.numeroConta);
        System.out.println("Agencia: " + this.agencia);
        System.out.println("Saldo: " + this.saldo);
    }

    public boolean depositar(double valor) {
        if (valor < 0) {
            System.out.println("Por favor, informe um valor acima de zero!");
            return false;
        }
        this.saldo += valor;
        System.out.println("Depósito realizado com sucesso!");
        new LogOperacoes(this, this, valor, TipoOperacoesEnum.DEPOSITO);
        return true;
    }

    public boolean sacar(double valor) {
        if (valor < 0) {
            System.out.println("Por favor, informe um valor acima de zero!");
            return false;
        }
        if (this.saldo < valor) {
            System.out.println("Saldo insuficiente!");
            return false;
        }
        this.saldo -= valor;
        System.out.println("Saque realizado com sucesso!");
        new LogOperacoes(this, this, valor, TipoOperacoesEnum.SAQUE);
        return true;
    }

    public boolean transferir(Conta contaDestino, double valor) {
        if (valor < 0) {
            System.out.println("Por favor, informe um valor acima de zero!");
            return false;
        }
        if (this.saldo < valor) {
            System.out.println("Saldo insuficiente!");
            return false;
        }
        this.setSaldo(this.getSaldo() - valor);
        contaDestino.setSaldo(contaDestino.getSaldo() + valor);
        System.out.println("Transferência realizada com sucesso!");
        new LogOperacoes(this, contaDestino, valor, TipoOperacoesEnum.TRANSFERENCIA);
        return true;
    }

    public void extrato() {
        System.out.println("Extrato:");
        String saldo = String.format("%.2f", this.saldo);
        System.out.println("Saldo: R$ " + saldo);
    }

    public boolean alterarDadosCadastrais(int escolha, String novoValor) {
        boolean bool = true;
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
                break;
            default:
                System.out.println("Opção inválida!");
                bool = false;
                break;
        }
        System.out.println("Dados alterados com sucesso!");
        return bool;
    }


    @Override
    public String toString() {
        return "Titular : " + this.nome + "\nCPF : " + this.cpf.getCpf() + "\nRenda Mensal : " + this.rendaMensal + "\nConta : " + this.numeroConta + "\nAgencia : " + this.agencia.getCidade() + " - " + this.agencia.getCodigo() + "\nSaldo : " + this.saldo;
    }

    protected void setSaldo(double valor) {
        this.saldo = valor;
    }
}
