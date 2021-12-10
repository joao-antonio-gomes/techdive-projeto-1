package br.com.banco.devinhouse.database;

import br.com.banco.devinhouse.conta.Conta;
import br.com.banco.devinhouse.conta.ContaCorrente;
import br.com.banco.devinhouse.conta.ContaInvestimento;
import br.com.banco.devinhouse.conta.ContaPoupanca;

import java.util.ArrayList;

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
                System.out.println(conta.toString());
            }
        }
    }

    public static void listarTodasContasPoupanca() {
        for (Conta conta : contas) {
            if (conta instanceof ContaPoupanca) {
                System.out.println(conta.toString());
            }
        }
    }

    public static void listarTodasContasInvestimento() {
        for (Conta conta : contas) {
            if (conta instanceof ContaInvestimento) {
                System.out.println(conta.toString());
            }
        }
    }

    public static void listarTodasContas() {
        for (Conta conta : contas) {
            System.out.println(conta.toString());
        }
    }

    public static void listarContaComSaldoNegativo() {
        for (Conta conta : contas) {
            if (conta.getSaldo() < 0) {
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
        contasInvestimento.forEach(ContaInvestimento::toString);
    }

    public static Conta getContaByNumero(int numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumeroConta() == numeroConta) {
                return conta;
            }
        }
        return null;
    }
}
