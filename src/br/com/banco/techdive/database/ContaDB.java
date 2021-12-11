package br.com.banco.techdive.database;

import br.com.banco.techdive.conta.Conta;
import br.com.banco.techdive.conta.ContaCorrente;
import br.com.banco.techdive.conta.ContaInvestimento;
import br.com.banco.techdive.conta.ContaPoupanca;
import br.com.banco.techdive.enumerators.AgenciaEnum;
import br.com.banco.techdive.enumerators.InvetimentosEnum;
import br.com.banco.techdive.menu.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class ContaDB {
    private static ArrayList<Conta> contas = new ArrayList<>();

    public static ArrayList<Conta> getContas() {
        return contas;
    }

    public static void addConta(Conta conta) {
        ContaDB.contas.add(conta);
    }

    public static void listarTodasContasCorrentes() {
        for (Conta conta : contas) {
            if (conta instanceof ContaCorrente) {
                Utils.separaLinha();
                System.out.println(conta.toString());
            }
        }
    }

    public static void listarTodasContasPoupanca() {
        for (Conta conta : contas) {
            if (conta instanceof ContaPoupanca) {
                Utils.separaLinha();
                System.out.println(conta.toString());
            }
        }
    }

    public static void listarTodasContasInvestimento() {
        for (Conta conta : contas) {
            if (conta instanceof ContaInvestimento) {
                Utils.separaLinha();
                System.out.println(conta.toString());
            }
        }
    }

    public static void listarTodasContas() {
        for (Conta conta : contas) {
            Utils.separaLinha();
            System.out.println(conta.toString());
        }
    }

    public static void listarContaComSaldoNegativo() {
        for (Conta conta : contas) {
            if (conta.getSaldo() < 0) {
                Utils.separaLinha();
                System.out.println(conta.toString());
            }
        }
    }

    public static void listarContaPorValorInvestido() {
        ArrayList<ContaInvestimento> contasInvestimento = new ArrayList<>();
        for (Conta conta : contas) {
            if (conta instanceof ContaInvestimento) {
                if (conta.getSaldo() > 0) {
                    contasInvestimento.add((ContaInvestimento) conta);
                }
            }
        }
        contasInvestimento.sort((o1, o2) -> (int) (o2.getSaldo() - o1.getSaldo()));
        for (ContaInvestimento contaInvestimento : contasInvestimento) {
            Utils.separaLinha();
            System.out.println(contaInvestimento.toString());
        }
    }

    public static Conta getContaByNumero(int numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumeroConta() == numeroConta) {
                Utils.separaLinha();
                return conta;
            }
        }
        return null;
    }
}
