package br.com.scheid.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue(value = "ingresso")
public class VendaIngresso extends Venda{

	private static final long serialVersionUID = 6801458021474423692L;
	
    public VendaIngresso() {
		this.ingressos = new ArrayList<>();
	}
	
	@ManyToMany
	private List<Ingresso> ingressos;

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	
}
