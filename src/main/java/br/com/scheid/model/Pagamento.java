package br.com.scheid.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.scheid.enums.EnumTipoPagamento;

@Entity
public class Pagamento extends AbstractModel<Long> implements Serializable{

	private static final long serialVersionUID = 2357565319395324063L;
	
	@Id @GeneratedValue
	private Long id;
	private float valor;
	@Enumerated
	private EnumTipoPagamento tipoDePagamento;
	
	public void setPagamento(float v, EnumTipoPagamento t){
		this.valor = v;
		this.tipoDePagamento = t;
	}
	
	public float getValor(){
		return this.valor;
	}

	public EnumTipoPagamento getTipoDePagamento() {
		return tipoDePagamento;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
