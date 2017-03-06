package br.com.teclogica.enums;

public enum Status {
	planejamento("Planejamento"), execucao("Execução"), encerrado("Encerrado");

	private String descricao;

	Status(String str) {
		this.setDescricao(str);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
