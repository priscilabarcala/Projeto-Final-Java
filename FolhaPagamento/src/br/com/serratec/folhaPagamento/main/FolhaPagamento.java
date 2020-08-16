package br.com.serratec.folhaPagamento.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.serratec.folhaPagamento.classes.Dependente;
import br.com.serratec.folhaPagamento.classes.Funcionario;
import br.com.serratec.folhaPagamento.enums.Parentesco;
import br.com.serratec.folhaPagamento.exceptions.DependenteException;

public class FolhaPagamento {

	public static void exibirMenu() {
		System.out.println("_______Sistema para Calculo de Folha de Pagamento_______\n");
		System.out.println("1 - Para adicionar funcionarios e dependentes via console");
		System.out.println("2 - Para listar funcionarios e dependentes adicionados via console");
		System.out.println("3 - Para adicionar e calcular funcionarios e dependentes via arquivo .csv");
		System.out.println("0 - Para sair do menu");
	}

	public static void main(String[] args) {

			
		int menu;
		String nome, cpf, rg, dataString, tipoDependente,  diretorio, diretorioFixed;
		double salarioBruto;
		LocalDate dataNascimento;

		Scanner inMenu = new Scanner(System.in);
		Scanner entradaDp = new Scanner(System.in);
		Scanner entradaDiretorio = new Scanner(System.in);
		Scanner entradaF = new Scanner(System.in);
		Scanner dp = new Scanner(System.in);
		
		
		System.out.println("Bem vindo ao sistema de Calculo IR para funcion�rios!!!\n");

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		List<Funcionario> listaFuncionarioInput = new ArrayList<Funcionario>();

		do {
			exibirMenu();
			menu = inMenu.nextInt();
			switch (menu) {
			case 1: {
				System.out.println("Digite o nome do funcionario: ");
				nome = entradaF.nextLine();
				System.out.println("Digite o CPF do funcionario. (CPF valido somente de 11 digitos)");
				cpf = entradaF.nextLine();
				System.out.println("Digite o RG do funcionario");
				rg = entradaF.nextLine();
				System.out.println("Digite a data de nascimento do funcionario yyyy-MM-dd  ex: 2020-01-01");
				dataString = entradaF.nextLine();
				dataNascimento = LocalDate.parse(dataString, format);
				System.out.println("Digite o salario bruto do funcionario");
				salarioBruto = entradaF.nextDouble();
				
				Funcionario funcionario = new Funcionario(nome, cpf, rg, dataNascimento, salarioBruto);
				listaFuncionarioInput.add(funcionario);
				
				System.out.println("Funcionario possui dependentes? sim/nao");
				
				String resposta1 = dp.nextLine().toLowerCase();

				while (resposta1.equals("sim")) {
					System.out.println("Digite o nome do dependente: ");
					nome = entradaDp.nextLine();
					System.out.println("Digite o CPF do dependente. (CPF valido somente de 11 digitos)");
					cpf = entradaDp.nextLine();
					System.out.println("Digite a data de nascimento do dependente yyyy-MM-dd  ex: 2020-01-01");			
					dataString = entradaDp.nextLine();
					dataNascimento = LocalDate.parse(dataString, format);
					System.out.println("Qual o tipo de dependente? OUTROS/FILHO/SOBRINHO");
					tipoDependente = entradaDp.nextLine().toUpperCase();

					Dependente dependente = new Dependente(nome, cpf, dataNascimento,
							Parentesco.valueOf(tipoDependente));
					funcionario.adicionarDependente(dependente);
					funcionario.verificarCpfRepetidoDependente();
					System.out.println("Funcionario possui mais dependentes? sim/nao");
					resposta1 = dp.nextLine().toLowerCase();
				}
				
				funcionario.calcularImpostoRenda();	
				
				String basePath2 = new File("").getAbsolutePath().concat("\\src\\br\\com\\serratec\\folhaPagamento\\arquivos\\calculoPagamentoViaInput.csv");

				
				try {
					BufferedWriter bf2 = new BufferedWriter(new FileWriter(basePath2));
					
				for (Funcionario func : listaFuncionarioInput) {
					bf2.write(func.getNome() + ";" + func.getCpf() + ";" +  String.format("%.2f", func.getDescontoINSS()) + ";" + String.format("%.2f", func.getDescontoIR()) + ";" + String.format("%.2f", func.getSalarioLiquido()) +";"+ "\r\n \r\n");
					bf2.close();
					} 
				}catch (IOException e) {
				// TODO Auto-generated catch block
						e.printStackTrace();
					}				
			}break;
			case 2:{
				
				System.out.println("Lista de Funcionario com seus dependentes");
				for (Funcionario func : listaFuncionarioInput) {
					/*
            		System.out.println(func.getNome());
            		System.out.printf("Salario Bruto: %.2f", func.getSalarioBruto());
            		System.out.printf("\nDesconto INSS: %.2f", func.getDescontoINSS());
            		System.out.printf("\nData Nascimento: ", func.mostrarData());
            		System.out.printf("\nDesconto IR: %.2f", func.getDescontoIR());
            		System.out.printf("\nSalario Liq: %.2f", func.getSalarioLiquido());
            		System.out.println(func.getListaDependente());*/
					
					System.out.println(func.toString());
					}
			}break;
			case 3:				
				System.out.println("Selecione o caminho absoluto para entrada de arquivo csv");
				diretorio = entradaDiretorio.nextLine();
				
				
				diretorioFixed = diretorio.replace("\"", "\\");

				File arquivoEntrada = new File(diretorioFixed);

				List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();

				Funcionario funcionario = null;
				try {
					Scanner entradaArq = new Scanner(arquivoEntrada);

					while (entradaArq.hasNextLine()) {

						while (!entradaArq.equals(1) && entradaArq.hasNext()) {
							String[] pessoa = entradaArq.nextLine().split(";");
							if (pessoa.length == 5) {
								
								funcionario = new Funcionario(pessoa[0], pessoa[1], pessoa[2], LocalDate.parse(pessoa[3], formatter), Double.parseDouble(pessoa[4]));
								listaFuncionario.add(funcionario);
								
							} else if (pessoa.length == 4) {
								
								Dependente dependente = new Dependente(pessoa[0], pessoa[1],LocalDate.parse(pessoa[2], formatter), Parentesco.valueOf(pessoa[3]));
								funcionario.adicionarDependente(dependente);
							}

							funcionario.calcularImpostoRenda();
							funcionario.verificarCpfRepetidoDependente();
						} 

						for (Funcionario fun : listaFuncionario) {
							int cont = 0;
							for (Funcionario func : listaFuncionario) {
								if (fun.getCpf().equals(func.getCpf())) {
									cont++;
								}
								if (cont >= 2) {
									throw new DependenteException("Dependente: " + func.getNome()+ " \neste cpf ja foi cadastrado em nosso sistema");
								}
							}
						}						

						
						String basePath = new File("").getAbsolutePath().concat("\\src\\br\\com\\serratec\\folhaPagamento\\arquivos\\calculoPagamento.csv");

						BufferedWriter bf = new BufferedWriter(new FileWriter(basePath));
						
						for (Funcionario func : listaFuncionario) {
							
							System.out.println(func.getNome());							
							System.out.printf("Salario Bruto: %.2f", func.getSalarioBruto());
							System.out.printf("\nDesconto INSS: %.2f", func.getDescontoINSS());
							System.out.printf("\nDesconto IR: %.2f", func.getDescontoIR());
							System.out.printf("\nSalario Liq: %.2f", func.getSalarioLiquido());
							System.out.println(func.getListaDependente());
							bf.write(func.getNome() + ";" + func.getCpf() + ";" +  String.format("%.2f", func.getDescontoINSS()) + ";" + String.format("%.2f", func.getDescontoIR()) + ";" + String.format("%.2f", func.getSalarioLiquido()) +";"+ "\r\n \r\n");
						}
						
						bf.close();
						
					} 
					entradaArq.close();
					
				}catch (FileNotFoundException e) {
					System.out.println("Arquivo nao encontrado! Digite um diretorio valido");;
				} catch (IOException e) {
					System.out.println("Erro ao criar arquivo de saída.");
					e.printStackTrace();
				}
				break;
			case 0:
				System.out.println("Sistema encerrado.");
				break;
			default: System.out.println("Digite um valor valido\n");
				
			}
			
		}while(menu!=0);

	entradaDiretorio.close();inMenu.close();entradaDp.close();entradaF.close();dp.close();

}

}