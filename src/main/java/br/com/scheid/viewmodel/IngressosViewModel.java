package br.com.scheid.viewmodel;

import java.util.List;

import br.com.scheid.dao.GenericDAO;
import br.com.scheid.dao.IngressoQueryGenerator;
import br.com.scheid.filters.IngressoFilter;
import br.com.scheid.model.Ingresso;

public class IngressosViewModel {

	private GenericDAO dao;
	
	public IngressosViewModel(){
		this.dao = new GenericDAO();
	}
	
	public void salvarIngresso(Ingresso ingresso){
		this.dao.salvar(ingresso);
	}
	
	public void removerIngresso(Long idIngresso){
		this.dao.deletar(Ingresso.class, idIngresso);
	}
	
	public List<Ingresso> buscarIngresso(IngressoFilter ingf){
		IngressoQueryGenerator iqg = new IngressoQueryGenerator();
		return dao.listarTodos(Ingresso.class, iqg.getQuery(ingf));
	}
}
