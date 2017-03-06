package br.com.teclogica.model;

public class Requisito {
	private Cargo cargo;
	private LinguagemProgramacao linguagemProgramacao;

	public LinguagemProgramacao getLinguagemProgramacao() {
		return linguagemProgramacao;
	}

	public void setLinguagemProgramacao(LinguagemProgramacao linguagemProgramacao) {
		this.linguagemProgramacao = linguagemProgramacao;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
}
