package br.com.banco.techdive.menu;

import br.com.banco.techdive.conta.Conta;
import br.com.banco.techdive.conta.ContaCorrente;
import br.com.banco.techdive.conta.ContaInvestimento;
import br.com.banco.techdive.conta.ContaPoupanca;
import br.com.banco.techdive.cpf.Cpf;
import br.com.banco.techdive.enumerators.AgenciaEnum;
import br.com.banco.techdive.enumerators.InvetimentosEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MenuCadastro {
    static Scanner scanner = new Scanner(System.in);

    public static void menuPrincipalCadastro() {
        Utils.separaParagrafo();
        System.out.println("\nEscolha o tipo de conta para cadastrar: ");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupança");
        System.out.println("3 - Conta Investimento");
        System.out.println("0 - Voltar ao menu principal");

        int tipoConta = scanner.nextInt();
        ArrayList<Integer> opcoes = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        while (!opcoes.contains(tipoConta)) {
            Utils.separaParagrafo();
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
        Utils.separaParagrafo();
        System.out.println("\nDigite o nome do cliente: ");
        String nome = scanner.next();
        Utils.separaLinha();
        System.out.println("\nDigite o CPF do cliente: ");
        String cpf = scanner.next();
        Utils.separaLinha();
        while (!Cpf.cpfEhValido(cpf)) {
            System.out.println("\nDigite um CPF válido: ");
            cpf = scanner.next();
        }
        Utils.separaLinha();
        System.out.println("\nDigite a renda do cliente: ");
        double renda = scanner.nextDouble();
        Utils.separaLinha();
        System.out.println("\nDigite o número da agência do cliente: ");
        int codigoAgencia = scanner.nextInt();
        ArrayList<Integer> opcoes = new ArrayList<>();
        for (AgenciaEnum agencia : AgenciaEnum.values()) {
            opcoes.add(agencia.ordinal());
        }
        for (AgenciaEnum agencia : AgenciaEnum.values()) {
            System.out.println(agencia.ordinal() + " - Cidade: " + agencia.getCidade() + " - Código: " + agencia.getCodigo());
        }
        while (!opcoes.contains(scanner.nextInt())) {
            System.out.println("\nDigite um código válido: ");
            for (AgenciaEnum agencia : AgenciaEnum.values()) {
                System.out.println(agencia.ordinal() + " - Cidade: " + agencia.getCidade() + " - Código: " + agencia.getCodigo());
            }
        }

        AgenciaEnum agencia = AgenciaEnum.values()[codigoAgencia];


        Conta conta = null;

        if (tipoConta == 3) {
            Utils.separaLinha();
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
        Utils.separaParagrafo();
        System.out.println("\nConta cadastrada com sucesso!");
        System.out.println("\nDados da conta: ");
        System.out.println("\n\n");
        System.out.println(conta.toString());
        Utils.pressioneEnter();
        System.out.println("\nDeseja cadastrar outra conta? (S/N)");
        String resposta = scanner.next();
        if (resposta.equalsIgnoreCase("S")) {
            menuPrincipalCadastro();
        }
        MenuPrincipal.listarMenuPrincipal();
    }
}
