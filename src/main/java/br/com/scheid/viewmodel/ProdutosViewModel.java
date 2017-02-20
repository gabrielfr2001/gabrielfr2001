package br.com.scheid.viewmodel;

import java.util.List;

import br.com.scheid.dao.GenericDAO;
import br.com.scheid.dao.ProdutoQueryGenerator;
import br.com.scheid.filters.ProdutoFilter;
import br.com.scheid.model.Produto;

public class ProdutosViewModel {

	private GenericDAO dao;
	
	public ProdutosViewModel(){
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
	
	public void ativarProdutos(List<Produto> produtos){
		for(int i=0; i < produtos.size();i++){
			if(produtos.get(i).getAtivo() == false){
				produtos.get(i).setAtivo(true);
			} else{
				produtos.get(i).setAtivo(false);
			}
			this.dao.salvar(produtos.get(i));
		}
	}
}
