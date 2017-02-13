package br.com.scheid.viewmodel;

import java.util.List;

import br.com.scheid.dao.GenericDAO;
import br.com.scheid.dao.ProdutoQueryGenerator;
import br.com.scheid.filters.ProdutoFilter;
import br.com.scheid.model.Produto;

public class IU01_01ViewModel {

	private GenericDAO dao;
	
	public IU01_01ViewModel(){
		this.dao = new GenericDAO();
	}
	
	public void salvarProduto(Produto produto){
		this.dao.salvar(produto);
	}
	
	public void removerProduto(Long idProduto){
		this.dao.deletar(Produto.class, idProduto);
	}
	
	public List<Produto> buscarProduto(ProdutoFilter pf){
		ProdutoQueryGenerator pqg = new ProdutoQueryGenerator();
		return dao.listarTodos(Produto.class, pqg.getQuery(pf));
	}
}
