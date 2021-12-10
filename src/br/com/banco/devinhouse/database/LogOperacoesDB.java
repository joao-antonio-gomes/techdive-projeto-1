package br.com.banco.devinhouse.database;

import br.com.banco.devinhouse.conta.Conta;
import br.com.banco.devinhouse.conta.LogOperacoes;

import java.util.ArrayList;

public class LogOperacoesDB {
    private static ArrayList<LogOperacoes> logOperacoes = new ArrayList<>();

    public static ArrayList<LogOperacoes> getLogOperacoes() {
        return logOperacoes;
    }

    public static void addLogOperacoes(LogOperacoes logOperacoes) {
        LogOperacoesDB.logOperacoes.add(logOperacoes);
    }

    public static void getLogOperacoesByConta(Conta conta) {
        for (LogOperacoes logOperacoes : LogOperacoesDB.logOperacoes) {
            if(logOperacoes.getNumeroContaOrigem() == conta.getNumeroConta()) {
                System.out.println(logOperacoes.toString());
            }
        }
    }
}
