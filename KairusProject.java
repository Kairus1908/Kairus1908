package PI_2023;

import java.util.Scanner;
import java.time.LocalDate;

public class ProjetoIntegrador {

	// Scanner Global
	static Scanner ler = new Scanner(System.in);

	// Variáveis Globais

	static int i = 0, j = 0;
	static int ano = LocalDate.now().getYear();
	static int quantidadeUsuario = 0, quantidadeVacinas = 0, quantidadeRegistro = 0;
	static int anoUsuario, faixaUsuario;

	// Matrizes Globais

	static String CadastroUsuario[][] = new String[10][4];
	static String CadastroVacina[][] = new String[10][5];
	static String Registro[][] = new String[5][4];

	public static void main(String[] args) {

		// Menu inicial:

		while (true) {

			System.out.println("Menu Inicial: ");
			System.out.println();
			System.out.println("1. Registrar Paciente. ");
			System.out.println("2. Cadastrar Vacinas. ");
			System.out.println("3. Vincular Vacina ao Paciente. ");
			System.out.println("4. Estatistica de Vacina. ");
			System.out.println("5. Sair");
			System.out.println();
			System.out.println("Escoha uma opção: ");

			int opcao = ler.nextInt();
			System.out.println();
			ler.nextLine();

			// Opções do Menu - Escolha/Caso:

			switch (opcao) {
			case 1:
				RegistroPaciente(ler);
				break;
			case 2:
				RegistroVacina(ler);
				break;
			case 3:
				VacinaUsuario(ler);
				break;
			case 4:
				Estatistica(ler);
				break;
			case 5:
				System.out.println("Saindo do programa...");
				System.exit(0);
				break;
			default:
				System.out.println("Opção incorreta. Verifique e tente novamente.");
			}
		}
	}

	// Funções:

	// Cadastro Paciente

	private static void RegistroPaciente(Scanner ler) {
		int i = 0;
		for (i = 0; i < 10; i++) {
			String Paciente;
			String op;
			op = "n";

			while (op.equals("n")) {

				System.out.print("Nome do paciente: ");
				Paciente = ler.nextLine();

				if (!VerificarLetras(Paciente)) {
					System.out.print("Paciente inválido. Tente novamente. ");
					continue;
				}
				CadastroUsuario[i][0] = Paciente;

				boolean teste = true;
				String CPF;
				System.out.print("Informe o CPF: ");
				while (teste) {
					CPF = ler.nextLine();

					if (CPF.length() != 11) {
						System.out.println("Digite os 11 dígitos do CPF.");
						continue;
					}

					if (!VerificarNumero(CPF)) {
						System.out.print("CPF inválido. Tente novamente. ");
						continue;
					}

					if (!validarCPF(CPF)) {
						System.out.println("CPF inválido. Tente novamente.");
						continue;
					} else {
						CadastroUsuario[i][1] = CPF;
						teste = false;
					}
				}

				teste = true;

				String Dia, Ano, Mes;
				System.out.print("Dia do nascimento: ");
				Dia = ler.nextLine();
				System.out.print("Mes do nascimento: ");
				Mes = ler.nextLine();
				System.out.print("Ano de nascimento: ");
				Ano = ler.nextLine();

				anoUsuario = Integer.parseInt(Ano);
				faixaUsuario = ano - anoUsuario;
				CadastroUsuario[i][3] = Integer.toString(faixaUsuario);

				if (!VerificarNumero(Ano)) {
					System.out.println();
					System.out.print("Ano inválido. Tente novamente. ");
					System.out.println();
					System.out.println();
					continue;
				}

				CadastroUsuario[i][2] = Ano;

				System.out.println();
				System.out
						.println("------------------- Por favor, verifique os dados cadastrados: -------------------");
				System.out.println();
				System.out.println("Paciente Cadastrado: " + CadastroUsuario[i][0]);
				System.out.println("CPF: " + CadastroUsuario[i][1]);
				System.out.println("Data de Nascimento: " + Dia + "/" + Mes + "/" + Ano);
				System.out.println("Idade do Paciente: " + CadastroUsuario[i][3]);
				System.out.println();
				System.out
						.println("----------------------------------------------------------------------------------");
				System.out.println();

				System.out.print("Todos os dados estão corertos? s/n ");
				System.out.println();

				op = ler.nextLine();
			}
			if (op.equals("n")) {
				break;
			}
			System.out.println("Paciente cadastrado!");
			System.out.println();

			quantidadeUsuario++;

			System.out.print("Gostaria de cadastrar mais usuários? s/n ");
			System.out.println();
			String opc;

			opc = ler.nextLine();

			if (opc.equals("n")) {
				break;
			}
		}

	}

	// Cadastro das vacinas

	private static void RegistroVacina(Scanner ler) {
		int i = 0;
		for (i = 0; i < 10; i++) {

			String Vacina;
			String op2;
			op2 = "n";
			while (op2.equals("n")) {

				System.out.print("Nome da Vacina: ");
				Vacina = ler.nextLine();
				CadastroVacina[i][0] = Vacina;

				String Lote;
				System.out.print("Lote: ");
				Lote = ler.nextLine();
				CadastroVacina[i][1] = Lote;

				String Validade;
				System.out.print("Data de vencimento da vacina: ");
				Validade = ler.nextLine();
				CadastroVacina[i][3] = Validade;

				System.out.println();
				System.out
						.println("------------------- Por favor, verifique os dados cadastrados: -------------------");
				System.out.println();
				System.out.println("Vacina Cadastrada: " + CadastroVacina[i][0]);
				System.out.println("Lote: " + CadastroVacina[i][1]);
				System.out.println("Data de Vencimento: " + CadastroVacina[i][3]);
				System.out.println();
				System.out
						.println("----------------------------------------------------------------------------------");
				System.out.println();

				System.out.print("Todos os dados estão corretos? s/n ");
				System.out.println();

				op2 = ler.nextLine();
			}
			if (op2.equals("n")) {
				break;
			}

			System.out.println();
			System.out.println("Vacina Cadastrada!");
			System.out.println();

			quantidadeVacinas++;

			System.out.print("Gostaria de cadastrar mais uma vacina? s/n ");
			String opc;

			opc = ler.nextLine();

			if (opc.equals("n")) {
				break;
			}
		}

	}

	// Vincular Vacina ao Usuário

	private static void VacinaUsuario(Scanner ler) {

		String opc;
		j = 0;
		do {
            
			for (int i = 0; i < quantidadeUsuario; i++) {
				System.out.println((i + 1) + " - " + "Paciente: " + CadastroUsuario[i][0] + " | " + "CPF: " + CadastroUsuario[i][1]);
			}
			i = ler.nextInt();
			i--;
			Registro[j][0] = CadastroUsuario[i][0];

			System.out.println();
			System.out.println();
			System.out.println("------------------- Por favor, verifique os dados cadastrados: -------------------");
			System.out.println();
			System.out.println("Paciente selecionado: " + CadastroUsuario[i][0]);
			System.out.println();
			System.out.println("----------------------------------------------------------------------------------");

			System.out.println();
			System.out.println();

			System.out.println("Vacinas disponíves: ");

			for (i = 0; i < quantidadeVacinas; i++) {

				System.out.println((i + 1) + " - " + "Vacina: " + CadastroVacina[i][0] + " | " + "Lote: " + CadastroVacina[i][1] + " | " + "Vencimento: " + CadastroVacina[i][3]);

			}

			int k;
			k = ler.nextInt();
		    k--;
			Registro[j][1] = CadastroVacina[k][0];
			
            
            System.out.println();
			String Dose;
			System.out.print("Dose: ");
			Dose = ler.next();
			Registro[j][2] = Dose;

			System.out.println();
			System.out.println();
			System.out.println("------------------- Por favor, verifique os dados cadastrados: -------------------");
			System.out.println();
			System.out.println("Vacina selecionada: " + Registro[j][1]);
			System.out.println("Dose: " + Registro[j][2]);
			System.out.println();
			System.out.println("----------------------------------------------------------------------------------");

			System.out.println();
			System.out.println();

			System.out.print("Gostaria de associar vacinas à pacientes? s/n ");

			opc = ler.next();
            quantidadeRegistro++;
			j++;
		} while (!opc.equals("n"));
	}
	// Verificações:

	// Nome = Letras

	private static boolean VerificarLetras(String Letras) {
		return Letras != null && Letras.matches("^[a-z A-Z]*$");
	}
	// CPF = Número

	private static boolean VerificarNumero(String Numero) {
		return Numero != null && Numero.matches("^[0-9]*$");
		}

	// Valida os dados do CPF

	public static boolean validarCPF(String CPF) {

		int[] numeros = new int[11];
		char primeiroDigito = CPF.charAt(0);

		// Verifica se todos os dígitos são iguais

		for (int i = 1; i < CPF.length(); i++) {
			if (CPF.charAt(i) != primeiroDigito) {
				break;
			}

			// Caso todos os dígitos são iguais, CPF inválido

			if (i == CPF.length() - 1) {
				return false;

			}
		}

		for (int i = 0; i < 11; i++) {
			numeros[i] = Character.getNumericValue(CPF.charAt(i));
		}

		int soma = 0;
		int Calculo1, Calculo2;

		for (int i = 0, peso = 10; i < 9; i++, peso--) {
			soma += numeros[i] * peso;
		}

		Calculo1 = 11 - (soma % 11);
		if (Calculo1 > 9) {
			Calculo1 = 0;
		}

		soma = 0;

		for (int i = 0, peso = 11; i < 10; i++, peso--) {
			soma += numeros[i] * peso;
		}

		Calculo2 = 11 - (soma % 11);
		if (Calculo2 > 9) {
			Calculo2 = 0;
		}
		return Calculo1 == numeros[9] && Calculo2 == numeros[10];
	}

	private static void Estatistica(Scanner ler) {

		// Matriz temporária
		String Estat[][] = new String[9][2];
		String op;
		int l;
		int b = 0;
		do {

			for (int j = 0; j < quantidadeUsuario; j++) {
				System.out.println((j + 1) + " - " + "Paciente: " + CadastroUsuario[j][0] + " | " + "CPF: " + CadastroUsuario[j][1]);
			}
			b = ler.nextInt();
			b--;
			int k;
			l = 0;
			
			for (k = 0; k < quantidadeRegistro; k++) {
				if (CadastroUsuario[b][0].equals(Registro[k][0])) {
					Estat[l][0] = Registro[k][1];
					Estat[l][1] = Registro[k][2];
					l++;
				}
			}
			
			
			int m, y;
			int Dose = 0, total;

			
			for (m = 0; m < quantidadeVacinas; m++) {
				Dose = 0;
				total = 0;
				for (y = 0; y < l; y++) {
					if (CadastroVacina[m][0].equals(Estat[y][0])) {
						Dose = Integer.parseInt(Estat[y][1]);
						total = total + Dose;
					}
				}
				System.out.println("O " + CadastroUsuario[b][0] + " tomou " + total + " doses da vacina " + CadastroVacina[m][0]);
			}
			op = "n";
			System.out.print("Gostaria de visualizar outro registro? s/n ");
			op = ler.next();	
		
		} while (!op.equals("n"));
	}	
}