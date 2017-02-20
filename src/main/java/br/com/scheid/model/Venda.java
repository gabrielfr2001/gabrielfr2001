package br.com.scheid.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Venda extends AbstractModel<Long> implements Serializable {
	
	private static final long serialVersionUID = -5447895010436459923L;
	
	@Id @GeneratedValue
	private Long id;
	private Date data;
	
	@Override
	public Long getId() {
		return id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
