package br.com.banco.techdive.database;

import br.com.banco.techdive.conta.Conta;
import br.com.banco.techdive.conta.LogOperacoes;
import br.com.banco.techdive.enumerators.TipoOperacoesEnum;
import br.com.banco.techdive.menu.Utils;

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

        ArrayList<LogOperacoes> logOperacoesConta = new ArrayList<>();
        for (LogOperacoes logOperacoes : LogOperacoesDB.logOperacoes) {
            if(logOperacoes.getNumeroContaOrigem() == conta.getNumeroConta() ||
                    (logOperacoes.getNumeroContaDestino() == conta.getNumeroConta() && logOperacoes.getTipoOperacao() == TipoOperacoesEnum.TRANSFERENCIA)) {
                logOperacoesConta.add(logOperacoes);
            }

        }
        if (logOperacoesConta.size() > 0) {
            for (LogOperacoes logOperacoes : logOperacoesConta) {
                Utils.separaLinha();
                System.out.println(logOperacoes.toString());
            }
            return;
        }

        Utils.separaLinha();
        System.out.println("Nenhuma operação encontrada para a conta informada.");
    }
}
