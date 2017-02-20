package br.com.scheid.dao;

import java.util.HashMap;
import java.util.Map;

import br.com.scheid.dto.QueryDTO;
import br.com.scheid.filters.ProdutoFilter;
import br.com.scheid.model.Produto;

public class ProdutoQueryGenerator {

	public QueryDTO getQuery(ProdutoFilter pf){
		
		Map<String, Object> params = new HashMap<>();
		StringBuilder sql = new StringBuilder();
		sql.append("Select x from " + Produto.class.getName() + " x ");
		sql.append(" where 1=1");
		if (pf.getNome() != null && !pf.getNome().equals("")) {
			sql.append(" and UPPER(x.nome) LIKE :pNome ");
			params.put("pNome", "%" + pf.getNome().toUpperCase() + "%");
		}

		if (pf.getDescricao() != null && !pf.getDescricao().equals("")) {
			sql.append(" and UPPER(x.descricao) LIKE :pDescricao ");
			params.put("pDescricao", "%" + pf.getDescricao().toUpperCase() + "%");
		}
		return new QueryDTO(sql.toString(), params);
	}
	
}
