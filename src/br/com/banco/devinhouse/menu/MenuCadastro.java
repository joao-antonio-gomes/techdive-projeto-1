package br.com.banco.devinhouse.menu;

import br.com.banco.devinhouse.conta.Conta;
import br.com.banco.devinhouse.conta.ContaCorrente;
import br.com.banco.devinhouse.conta.ContaInvestimento;
import br.com.banco.devinhouse.conta.ContaPoupanca;
import br.com.banco.devinhouse.cpf.Cpf;
import br.com.banco.devinhouse.enumerators.AgenciaEnum;
import br.com.banco.devinhouse.enumerators.InvetimentosEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MenuCadastro {
    static Scanner scanner = new Scanner(System.in);

    public static void menuPrincipalCadastro() {
        System.out.println("\nEscolha o tipo de conta para cadastrar: ");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupança");
        System.out.println("3 - Conta Investimento");
        System.out.println("0 - Voltar ao menu principal");

        int tipoConta = scanner.nextInt();
        ArrayList<Integer> opcoes = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        while (!opcoes.contains(tipoConta)) {
            System.out.println("\nEscolha um tipo de conta válido: ");
            System.out.println("1 - Conta Corrente");
            System.out.println("2 - Conta Poupança");
            System.out.println("3 - Conta Investimento");
            System.out.println("0 - Voltar ao menu principal");
            tipoConta = scanner.nextInt();
        }
        if (tipoConta == 0) {
            MenuPrincipal.listarMenuPrincipal();
        }
        dadosCadastro(tipoConta);
    }

    public static void dadosCadastro(int tipoConta) {
        System.out.println("\nDigite o nome do cliente: ");
        String nome = scanner.next();
        System.out.println("\nDigite o CPF do cliente: ");
        String cpf = scanner.next();
        if (!Cpf.cpfEhValido(cpf)) {
            do {
                System.out.println("\nDigite um CPF válido: ");
                cpf = scanner.next();
            } while (!Cpf.cpfEhValido(cpf));
        }
        System.out.println("\nDigite a renda do cliente: ");
        double renda = scanner.nextDouble();
        System.out.println("\nDigite o número da agência do cliente: ");
        for (AgenciaEnum agencia : AgenciaEnum.values()) {
            System.out.println(agencia.ordinal() + " - Cidade: " + agencia.getCidade() + " - Código: " + agencia.getCodigo());
        }
        int codigoAgencia = scanner.nextInt();
        AgenciaEnum agencia = AgenciaEnum.values()[codigoAgencia];

        Conta conta = null;

        if (tipoConta == 3) {
            System.out.println("\nEscolha seu tipo de investimento:");
            for (InvetimentosEnum investimento : InvetimentosEnum.values()) {
                System.out.println(investimento.ordinal() + " - " + investimento.getDescricao());
            }
            int codigoInvestimento = scanner.nextInt();
            InvetimentosEnum investimento = InvetimentosEnum.values()[codigoInvestimento];
            conta = new ContaInvestimento(nome, cpf, renda, agencia, investimento);
        }

        if (tipoConta == 2) {
            conta = new ContaPoupanca(nome, cpf, renda, agencia);
        }

        if (tipoConta == 1) {
            conta = new ContaCorrente(nome, cpf, renda, agencia);
        }

        System.out.println(conta.toString());
        System.out.println("\nDeseja cadastrar outra conta? (S/N)");
        String resposta = scanner.next();
        if (resposta.equalsIgnoreCase("S")) {
            menuPrincipalCadastro();
        }
        MenuPrincipal.listarMenuPrincipal();
    }
}
