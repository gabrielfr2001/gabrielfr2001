package br.com.scheid.to;

import br.com.scheid.model.Ingresso;

public class IngressoVendido {
	
	private Integer quantidade;
	private Ingresso ingresso;
	
	public IngressoVendido(){}
	
	public IngressoVendido(Ingresso ingresso, Integer quantidade) {
		super();
		this.quantidade = quantidade;
		this.ingresso = ingresso;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Ingresso getIngresso() {
		return ingresso;
	}
	public void setIngresso(Ingresso ingresso) {
		this.ingresso = ingresso;
	}
}
