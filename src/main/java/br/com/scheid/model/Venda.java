package br.com.scheid.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Venda extends AbstractModel<Long> implements Serializable {
	
	private static final long serialVersionUID = -5447895010436459923L;
	
	@Id @GeneratedValue
	private Long id;
	private LocalDate data;
	
	@Override
	public Long getId() {
		return id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
