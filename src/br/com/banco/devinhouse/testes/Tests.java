package br.com.banco.devinhouse.testes;

import br.com.banco.devinhouse.cpf.Cpf;
import br.com.banco.devinhouse.enumerators.AgenciaEnum;

import java.util.Arrays;

public class Tests {
    public static void main(String[] args) {
        // Teste validade cpf
        String cpfInvalido = "123.456.789-00";
        String cpfMenorQue11Digitos = "123.456.789-0";
        String cpfValido = "09355872925";

        System.out.print("CPF inválido: ");
        Cpf instanciaCpfInvalido = new Cpf(cpfInvalido);
        System.out.print("\nCPF inválido menor que 11 Digitos: ");
        Cpf instanciaCpfMenorQue11Digitos = new Cpf(cpfMenorQue11Digitos);
        System.out.println("\nCPF válido: ");
        Cpf instanciaCpfValido = new Cpf(cpfValido);

        // Teste formtacao cpf
        String cpfFormatado = "093.558.729-25";
        Boolean cpfFormatadoCorretamente = instanciaCpfValido.getCpf().equals(cpfFormatado);
        System.out.println("\nCPF formatado corretamente: " + cpfFormatadoCorretamente);

        for (AgenciaEnum agencia : AgenciaEnum.values()) {
            System.out.println(agencia.ordinal());
        }
        AgenciaEnum value = AgenciaEnum.values()[0];
//        AgenciaEnum.valueOf(value);
        System.out.println(value);
    }
}
