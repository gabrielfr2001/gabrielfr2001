package br.com.teclogica.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Funcionario extends Entidade {
	private Date dataNascimento;
	private Cargo cargo;
	private List<LinguagemProgramacao> linguagensDominio = new ArrayList<LinguagemProgramacao>();

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<LinguagemProgramacao> getLinguagensDominio() {
		return linguagensDominio;
	}

	public void setLinguagensDominio(List<LinguagemProgramacao> linguagensDominio) {
		this.linguagensDominio = linguagensDominio;
	}

}
