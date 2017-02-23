package br.com.scheid.to;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.scheid.model.Ingresso;
@Entity
public class IngressoVendido implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Long id;
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
