package br.com.scheid.model; 

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produto extends AbstractModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id @GeneratedValue
	private Long id;
	private String nome;
	private String descricao;
	private float preco;
	private Long estoque;
	private Boolean ativo;
	
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	//Gets And sets
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public Long getEstoque() {
		return estoque;
	}
	public void setEstoque(Long estoque) {
		this.estoque = estoque;
	}
	
	
}
