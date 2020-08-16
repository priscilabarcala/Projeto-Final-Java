package br.com.serratec.folhaPagamento.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.serratec.folhaPagamento.exceptions.DependenteException;
import br.com.serratec.folhaPagamento.interfaces.CalcularImpostoRenda;

public final class Funcionario extends Pessoa implements CalcularImpostoRenda {

	private String rg;
	private double salarioBruto;
	private double descontoINSS;
	private double descontoIR;
	private double salarioLiquido;
	private List<Dependente> listaDependente = new ArrayList<Dependente>();
	

	public Funcionario(String nome, String cpf, String rg, LocalDate dataNascimento, double salarioBruto) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;
		this.rg = rg;
		super.verificarTamCpf();
	}	

	
	public void listarDependentes() {
		for (Dependente dp : listaDependente) {
			System.out.println(dp.toString());
		}
	}

	final public void adicionarDependente(Dependente dp) {
		listaDependente.add(dp);
	}

	public String getRg() {
		return rg;
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public double getDescontoINSS() {
		return descontoINSS;
	}

	public double getDescontoIR() {
		return descontoIR;
	}

	public double getSalarioLiquido() {
		return salarioLiquido;
	}


	public List<Dependente> getListaDependente() {
		return listaDependente;
	}

	final public void verificarCpfRepetidoDependente() {
		for (Dependente ld : listaDependente) {
			int cont = 0;
			for (Dependente dp : listaDependente) {
				if (dp.getCpf().equals(ld.getCpf())) {
					cont++;
				}
				if (cont >= 2) {
					throw new DependenteException(
							"Dependente: " + dp.getNome() + " \neste cpf ja foi cadastrado em nosso sistema");
				}
			}
		}
	}


	
	@Override
	public void calcularINSS() {

		if (salarioBruto <= 1751.81) {
			descontoINSS = salarioBruto * 0.08;

		} else if (salarioBruto >= 1751.82 && salarioBruto <= 2919.72) {
			descontoINSS = salarioBruto * 0.09;

		} else if (salarioBruto >= 2919.73 && salarioBruto <= 5839.45) {
			descontoINSS = salarioBruto * 0.11;

		} else if (salarioBruto >= 5839.45) {
			descontoINSS = 5839.45 * 0.11;
		}
	}

	@Override
	public void calcularImpostoRenda() {
		this.calcularINSS();

		descontoIR = salarioBruto - descontoINSS - 189.59 * listaDependente.size();

		if (descontoIR >= 4664.68) {
			descontoIR = descontoIR * 0.275 - 869.36;

		} else if (descontoIR >= 3751.06) {
			descontoIR = descontoIR * 0.225 - 636.13;

		} else if (descontoIR >= 2826.66) {
			descontoIR = descontoIR * 0.15 - 354.80;

		} else if (descontoIR >= 1903.98) {
			descontoIR = descontoIR * 0.075 - 142.80;

		} else {
			descontoIR = 0;
		}

		this.calcularSalarioLiquido();

	}

	public double calcularSalarioLiquido() {
		return salarioLiquido = salarioBruto - descontoINSS - descontoIR;
	}
	
	public LocalDate mostrarData() {
		return dataNascimento;
	}

	@Override
	public String toString() {
		return "Nome funcionario:		" + nome + "\n" + 
				"Cpf:		                " + cpf + "\n" + 
				"Salario bruto:  	        " + salarioBruto + "\n" + 
				"RG:       	                " + rg + 
				"\nData de nascimento:	        " + dataNascimento +
				"\nDependentes:                    " + listaDependente;
	}
	public static void main(String[] args) {
		
	}

}
