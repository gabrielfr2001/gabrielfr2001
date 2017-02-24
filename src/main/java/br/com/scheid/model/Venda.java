package br.com.scheid.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Venda extends AbstractModel<Long> implements Serializable {
	
	private static final long serialVersionUID = -5447895010436459923L;
	
	@Id @GeneratedValue
	private Long id;
	private LocalDate data;
	@OneToMany
	private List<Pagamento> pagamentos;
	
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

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
	
}
