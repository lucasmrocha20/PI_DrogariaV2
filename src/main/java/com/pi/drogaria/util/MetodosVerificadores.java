package com.pi.drogaria.util;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetodosVerificadores extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2339728491328381767L;

	public static boolean verificacaoCpf(String CPF) {

		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;

		int sm, i, r, num, peso;

		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}
			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere
											// numerico
			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}
			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);
			// Verifica se os digitos calculados conferem com os digitos
			// informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public static String imprimeCPF(String CPF) {
		return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-"
				+ CPF.substring(9, 11));
	}

	public static boolean verificacaoNome(String nome) {

		boolean verificacao = false;

		if (nome != null) {
			nome = nome.replaceAll("[^A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]", "");

			if (!nome.equals("")) {
				verificacao = true;
			}
			if (nome == "") {
				verificacao = false;
			}
		}
		return verificacao;
	}

	public static boolean verificacaoTelefone(String numeroTelefone) {

		boolean verificacao = false;

		numeroTelefone = numeroTelefone.replaceAll("[^0-9]", "");

		if (numeroTelefone.length() >= 11 && numeroTelefone.length() <= 14) {
			if (numeroTelefone.length() == 12) {
				numeroTelefone = numeroTelefone.substring(0, 3) + "-" + numeroTelefone.substring(3, 4) + "-"
						+ numeroTelefone.substring(4, 8) + "-" + numeroTelefone.substring(8, 12);
			}
			if (numeroTelefone.length() == 11) {
				numeroTelefone = numeroTelefone.substring(0, 2) + "-" + numeroTelefone.substring(2, 3) + "-"
						+ numeroTelefone.substring(3, 7) + "-" + numeroTelefone.substring(7, 11);
			}
			if (!numeroTelefone.equals("")) {
				verificacao = true;
			}
			if (numeroTelefone == "") {
				verificacao = false;
			}
		} else {
			verificacao = false;
		}
		return verificacao;
	}

	public static boolean verificacaoCelular(String numeroCelular) {

		boolean verificacao = false;

		numeroCelular = numeroCelular.replaceAll("[^0-9]", "");

		if (numeroCelular.length() >= 11 && numeroCelular.length() <= 14) {
			if (numeroCelular.length() == 12) {
				numeroCelular = numeroCelular.substring(0, 3) + "-" + numeroCelular.substring(3, 4) + "-"
						+ numeroCelular.substring(4, 8) + "-" + numeroCelular.substring(8, 12);
			}
			if (numeroCelular.length() == 11) {
				numeroCelular = numeroCelular.substring(0, 2) + "-" + numeroCelular.substring(2, 3) + "-"
						+ numeroCelular.substring(3, 7) + "-" + numeroCelular.substring(7, 11);
			}
			if (!numeroCelular.equals("")) {
				verificacao = true;
			}
			if (numeroCelular == "") {
				verificacao = false;
			}
		} else {
			verificacao = false;
		}
		return verificacao;
	}

	public static boolean verificacaoRg(String rg) {

		boolean verificacao = false;

		rg = rg.replaceAll("[^0-9]", "");

		if (rg.length() >= 7 && rg.length() <= 14) {
			if (!rg.equals("")) {
				verificacao = true;
			}
			if (rg == "") {
				verificacao = false;
			}
		} else {
			verificacao = false;
		}
		return verificacao;
	}

	public static boolean verificacaoEmail(String email) {

		if ((email == null) || (email.trim().length() == 0))

			return false;

		String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";

		Pattern pattern = Pattern.compile(emailPattern);

		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	public static boolean verificacaoInteiro(int numero) {

		boolean verificacao = false;

		Integer var = numero;

		if (numero < 0 || var == null) {
			verificacao = false;
		} else {
			verificacao = true;
		}
		return verificacao;
	}

	public static boolean verificacaoNumeroReal(double numero) {

		boolean verificacao = false;

		if (numero >= 0.0) {
			verificacao = true;
		} else {
			verificacao = false;
		}
		return verificacao;
	}
}