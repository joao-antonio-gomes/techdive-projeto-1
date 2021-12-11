package br.com.banco.techdive.conta;

import br.com.banco.techdive.cpf.Cpf;
import br.com.banco.techdive.database.ContaDB;
import br.com.banco.techdive.enumerators.AgenciaEnum;
import br.com.banco.techdive.enumerators.TipoOperacoesEnum;
import br.com.banco.techdive.menu.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

    public void extrato() {
        System.out.println("Extrato:");
        String saldo = String.format("%.2f", this.saldo);
        System.out.println("Saldo: R$ " + saldo);
    }

    public boolean alterarDadosCadastrais() {
        ArrayList<Integer> escolhas = new ArrayList<>(Arrays.asList(1, 2, 3, 0));
        System.out.println("Qual dado você deseja alterar?");
        System.out.println("1 - Nome");
        System.out.println("2 - Renda");
        System.out.println("3 - Agencia");
        System.out.println("0 - Sair");
        int escolha = new Scanner(System.in).nextInt();
        while (!escolhas.contains(escolha)) {
            System.out.println("Por favor, informe uma opção válida!");
            this.alterarDadosCadastrais();
        }
        String confirmacao;
        boolean bool = true;
        Utils.separaLinha();
        switch (escolha) {
            case 1:
                System.out.println("Informe o novo nome:");
                String novoValor = new Scanner(System.in).nextLine();
                System.out.println("Novo nome será: " + novoValor);
                Utils.separaLinha();
                this.nome = novoValor;
                System.out.println("Nome alterado com sucesso!");
                break;
            case 2:
                System.out.println("Informe a nova renda:");
                double novaRenda = new Scanner(System.in).nextDouble();
                System.out.println("Nova renda será: " + novaRenda);
                Utils.separaLinha();
                this.rendaMensal = novaRenda;
                System.out.println("Renda alterada com sucesso!");
                break;
            case 3:
                System.out.println("Informe a nova agência:");
                for (AgenciaEnum agencia : AgenciaEnum.values()) {
                    System.out.println(agencia.ordinal() + " - Cidade: " + agencia.getCidade() + " - Código: " + agencia.getCodigo());
                }
                int codigoAgencia = new Scanner(System.in).nextInt();
                ArrayList<Integer> opcoes = new ArrayList<>();
                for (AgenciaEnum agencia : AgenciaEnum.values()) {
                    opcoes.add(agencia.ordinal());
                }
                while (!opcoes.contains(codigoAgencia)) {
                    System.out.println("\nDigite um código válido: ");
                    for (AgenciaEnum agencia : AgenciaEnum.values()) {
                        System.out.println(agencia.ordinal() + " - Cidade: " + agencia.getCidade() + " - Código: " + agencia.getCodigo());
                    }
                    codigoAgencia = new Scanner(System.in).nextInt();
                }
                AgenciaEnum agencia = AgenciaEnum.values()[codigoAgencia];
                Utils.separaLinha();
                this.agencia = agencia;
                System.out.println("Agencia alterada com sucesso!");
                break;
            default:
                System.out.println("Opção inválida!");
                bool = false;
                break;
        }
        return bool;
    }

    @Override
    public String toString() {
        return "Titular: " + this.nome +
                "\nCPF: " + this.cpf.getCpf() +
                "\nRenda Mensal: " + this.rendaMensal +
                "\nConta: " + this.numeroConta +
                "\nAgencia: " + this.agencia.getCidade() + " - " + this.agencia.getCodigo() +
                "\nSaldo: " + this.saldo;
    }

    protected void setSaldo(double valor) {
        this.saldo = valor;
    }
}
