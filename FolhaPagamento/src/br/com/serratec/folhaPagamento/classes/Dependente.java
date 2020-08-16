package br.com.serratec.folhaPagamento.classes;

import java.time.LocalDate;
import java.time.Period;

import br.com.serratec.folhaPagamento.enums.Parentesco;
import br.com.serratec.folhaPagamento.exceptions.DependenteException;


public final class Dependente extends Pessoa {

    private Parentesco parentesco;
    private final LocalDate diaAtual = LocalDate.now();
    private int idade;

    public Dependente(String nome, String cpf, LocalDate dataNascimento, Parentesco parentesco) {
        super(nome, cpf, dataNascimento);
        this.parentesco = parentesco;
        this.idade = Period.between(super.dataNascimento, this.diaAtual).getYears();
        this.verificarIdade();
        super.verificarTamCpf();
    }
    
    public void verificarIdade() {
        if (this.idade > 18) {
            System.out.println("O dependente possui " +idade+" anos de idade");
            throw new DependenteException("Idade limite ultrapassada.");
        }
    }

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "\nDependente nome: 		" + nome+ "\n" + 
				"Parentesco:			" + parentesco + "\n" + 
				"Idade:                	        " + idade +" anos" + "\n" + 
				"CPF:   				" + cpf +"\n" + 
				"Data Nascimento:  		" + dataNascimento + "\n";
	}
        
    
    
}
