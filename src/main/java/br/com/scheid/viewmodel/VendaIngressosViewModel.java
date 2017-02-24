package br.com.scheid.viewmodel;

import java.util.List;

import br.com.scheid.dao.GenericDAO;
import br.com.scheid.dao.IngressoQueryGenerator;
import br.com.scheid.filters.IngressoFilter;
import br.com.scheid.model.Ingresso;
import br.com.scheid.model.Pagamento;
import br.com.scheid.model.VendaIngresso;

public class VendaIngressosViewModel {
	
	public GenericDAO dao;
	
	public VendaIngressosViewModel(){
		this.dao = new GenericDAO();
	}
	
	public void salvar(VendaIngresso venda){
		this.dao.salvar(venda);
	}
	
	public void salvar(Pagamento venda){
		this.dao.salvar(venda);
	}
	
	public List<Ingresso> buscarIngresso(IngressoFilter ingf){
		IngressoQueryGenerator iqg = new IngressoQueryGenerator();
		return dao.listarTodos(Ingresso.class, iqg.getQuery(ingf));
	}
}
