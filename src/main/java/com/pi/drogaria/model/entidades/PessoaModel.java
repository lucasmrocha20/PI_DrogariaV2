package com.pi.drogaria.model.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class PessoaModel extends Pessoa{

	public class ValidateString {

		// Objetivo: Retornar quantidade de numeros em uma string;

		// @return count: Quantidade de numeros presentes em uma string;

		public int qtdNumberString(String str) {

			int count = 0; // armazena quantidade de numeros encontrados na
							// string;

			char c[] = str.toCharArray();

			for (int i = 0; i < c.length; i++) {

				if (c[i] >= '0' && c[i] <= '9') {// verificando se caractere são
													// um numero;

					count++;

				}

			}

			return count;

		}

		public String ListToString(List<Integer> list) {

			String result = new String();

			result = "";

			for (int str : list) {

				result += str;

			}

			return result;

		}

		public String MapToString(Map<Integer, Integer> map) {

			String result = new String();

			result = "";

			for (int str : map.keySet()) {

				result += str;

			}

			return result;

		}

	}

	public class ValidateGenerator {

		private ValidateString Validate_Str = new ValidateString();

		public String cpfGenerator() {

			List<Integer> cpfParcial = new ArrayList();

			int totalSomatoria = 0;

			int restoDv;

			int peso = 10;

			// geranado a parte dos 9 primeiros numeros do CPF

			for (int i = 0; i < 9; i++) {

				cpfParcial.add(this.NumberGenerator()); // gera os nove numeros
														// e insere num vetor

			}

			/* ** Calculando o primeiro dígito - INICIO ** */

			for (int item : cpfParcial) {

				totalSomatoria += (peso * item); // multiplicando os valores de
													// acordo com o seu peso

				peso--;

			}

			restoDv = (totalSomatoria % 11);

			if (restoDv < 2) {

				cpfParcial.add(0);

			} else {

				cpfParcial.add(11 - restoDv);

			}

			/* ** Calculando o primeiro dígito - FIM ** */

			/* ** Calculando o segundo dígito - INICIO ** */

			peso = 11;

			totalSomatoria = 0;

			for (int item : cpfParcial) {

				totalSomatoria += (peso * item); // multiplicando os valores de
													// acordo com o seu peso

				peso--;

			}

			restoDv = (totalSomatoria % 11);

			if (restoDv < 2) {

				cpfParcial.add(0);

			} else {

				cpfParcial.add(11 - restoDv);

			}

			return this.Validate_Str.ListToString(cpfParcial);

		}

		public boolean validaCPF(String cpf) {

			int i, j = 0, n, dgt1, vetor[] = new int[11];

			int result = 0, rest;

			if (this.Validate_Str.qtdNumberString(cpf) < 11) {

				return false;

			}

			if (cpf.length() < 11 && cpf.length() > 14) {

				return false;

			}

			String[] arrayCpf = cpf.split("-");

			for (i = 0; i < arrayCpf[0].length(); i++) {

				try {

					n = Integer.parseInt(String.valueOf(arrayCpf[0].charAt(i)));

					vetor[j] = n;

					j++;

				} catch (NumberFormatException ex) {

				}

			}

			for (i = 0; i < 9; i++) {

				result += (10 - i) * vetor[i];

			}

			rest = result % 11;

			if (rest < 2) {

				dgt1 = 0;

			} else {

				dgt1 = 11 - rest;

			}

			if ((vetor[9] != dgt1)
					&& (arrayCpf.length < 2 || dgt1 != Integer.parseInt(String.valueOf(arrayCpf[1].charAt(0))))) {

				return false;

			}

			vetor[9] = dgt1;

			result = 0;

			for (i = 0; i < 10; i++) {

				result += (11 - i) * vetor[i];

			}

			rest = result % 11;

			if (rest < 2) {

				dgt1 = 0;

			} else {

				dgt1 = 11 - rest;

			}

			if ((vetor[10] != dgt1)
					&& (arrayCpf.length < 2 || dgt1 != Integer.parseInt(String.valueOf(arrayCpf[1].charAt(1))))) {

				return false;

			}

			cpf = "";

			vetor[10] = dgt1;

			for (i = 0; i < 11; i++) {

				cpf += vetor[i];

			}

			return true;

		}

		public int NumberGenerator() {

			int number = (int) (Math.random() * 10);

			return number;

		}

	}

	public class ValidateGntr {

		private ValidateString Validate_Str = new ValidateString();

		public String rgGenerator() {

			List<Integer> rgParcial = new ArrayList();

			int totalSomatoria = 0;

			int restoDv;

			int peso = 6;

			for (int i = 0; i < 6; i++) {

				rgParcial.add(this.NumberGenerator()); // gera os nove numeros
														// e insere num vetor

			}

			/* ** Calculando o primeiro dígito - INICIO ** */

			for (int item : rgParcial) {

				totalSomatoria += (peso * item); // multiplicando os valores de
													// acordo com o seu peso

				peso--;

			}

			restoDv = (totalSomatoria % 7);

			if (restoDv < 2) {

				rgParcial.add(0);

			} else {

				rgParcial.add(7 - restoDv);

			}

			/* ** Calculando o primeiro dígito - FIM ** */

			/* ** Calculando o segundo dígito - INICIO ** */

			peso = 11;

			totalSomatoria = 0;

			for (int item : rgParcial) {

				totalSomatoria += (peso * item); // multiplicando os valores de
													// acordo com o seu peso

				peso--;

			}

			restoDv = (totalSomatoria % 7);

			if (restoDv < 2) {

				rgParcial.add(0);

			} else {

				rgParcial.add(7 - restoDv);

			}

			return this.Validate_Str.ListToString(rgParcial);

		}

		public boolean validaRG(String rg) {

			int i, j = 0, n, dgt1, vetor[] = new int[7];

			int result = 0, rest;

			if (this.Validate_Str.qtdNumberString(rg) < 7) {

				return false;

			}

			if (rg.length() < 7 && rg.length() > 10) {

				return false;

			}

			String[] arrayRg = rg.split("-");

			for (i = 0; i < arrayRg[0].length(); i++) {

				try {

					n = Integer.parseInt(String.valueOf(arrayRg[0].charAt(i)));

					vetor[j] = n;

					j++;

				} catch (NumberFormatException ex) {

				}

			}

			for (i = 0; i < 7; i++) {

				result += (7 - i) * vetor[i];

			}

			rest = result % 7;

			if (rest < 2) {

				dgt1 = 0;

			} else {

				dgt1 = 7 - rest;

			}

			if ((vetor[7] != dgt1)
					&& (arrayRg.length < 2 || dgt1 != Integer.parseInt(String.valueOf(arrayRg[1].charAt(0))))) {

				return false;

			}

			vetor[7] = dgt1;

			result = 0;

			for (i = 0; i < 6; i++) {

				result += (7 - i) * vetor[i];

			}

			rest = result % 7;

			if (rest < 2) {

				dgt1 = 0;

			} else {

				dgt1 = 7 - rest;

			}

			if ((vetor[7] != dgt1)
					&& (arrayRg.length < 2 || dgt1 != Integer.parseInt(String.valueOf(arrayRg[1].charAt(1))))) {

				return false;

			}

			rg = "";

			vetor[7] = dgt1;

			for (i = 0; i < 7; i++) {

				rg += vetor[i];

			}

			return true;

		}

		public int NumberGenerator() {

			int number = (int) (Math.random() * 7);

			return number;

		}

	}

	public class VldtGenerator {

		private ValidateString Validate_Str = new ValidateString();

		public String cepGenerator() {

			List<Integer> cepParcial = new ArrayList();

			int totalSomatoria = 0;

			int restoDv;

			int peso = 7;

			for (int i = 0; i < 7; i++) {

				cepParcial.add(this.NumberGenerator()); // gera os nove numeros
														// e insere num vetor

			}

			/* ** Calculando o primeiro dígito - INICIO ** */

			for (int item : cepParcial) {

				totalSomatoria += (peso * item); // multiplicando os valores de
													// acordo com o seu peso

				peso--;

			}

			restoDv = (totalSomatoria % 8);

			if (restoDv < 2) {

				cepParcial.add(0);

			} else {

				cepParcial.add(8 - restoDv);

			}

			/* ** Calculando o primeiro dígito - FIM ** */

			/* ** Calculando o segundo dígito - INICIO ** */

			peso = 8;

			totalSomatoria = 0;

			for (int item : cepParcial) {

				totalSomatoria += (peso * item); // multiplicando os valores de
													// acordo com o seu peso

				peso--;

			}

			restoDv = (totalSomatoria % 8);

			if (restoDv < 2) {

				cepParcial.add(0);

			} else {

				cepParcial.add(11 - restoDv);

			}

			return this.Validate_Str.ListToString(cepParcial);

		}

		public boolean validaCEP(String cep) {

			int i, j = 0, n, dgt1, vetor[] = new int[8];

			int result = 0, rest;

			if (this.Validate_Str.qtdNumberString(cep) < 8) {

				return false;

			}

			if (cep.length() < 8 && cep.length() > 11) {

				return false;

			}

			String[] arrayCep = cep.split("-");

			for (i = 0; i < arrayCep[0].length(); i++) {

				try {

					n = Integer.parseInt(String.valueOf(arrayCep[0].charAt(i)));

					vetor[j] = n;

					j++;

				} catch (NumberFormatException ex) {

				}

			}

			for (i = 0; i < 8; i++) {

				result += (7 - i) * vetor[i];

			}

			rest = result % 8;

			if (rest < 2) {

				dgt1 = 0;

			} else {

				dgt1 = 8 - rest;

			}

			if ((vetor[8] != dgt1)
					&& (arrayCep.length < 2 || dgt1 != Integer.parseInt(String.valueOf(arrayCep[1].charAt(0))))) {

				return false;

			}

			vetor[8] = dgt1;

			result = 0;

			for (i = 0; i < 7; i++) {

				result += (8 - i) * vetor[i];

			}

			rest = result % 8;

			if (rest < 2) {

				dgt1 = 0;

			} else {
				dgt1 = 8 - rest;
			}
			if ((vetor[8] != dgt1)
					&& (arrayCep.length < 2 || dgt1 != Integer.parseInt(String.valueOf(arrayCep[1].charAt(1))))) {

				return false;
			}
			cep = "";

			vetor[7] = dgt1;

			for (i = 0; i < 8; i++) {

				cep += vetor[i];
			}
			return true;
		}

		public int NumberGenerator() {

			int number = (int) (Math.random() * 7);

			return number;
		}
	}
}
