package br.com.scheid.dao;

import java.util.HashMap;
import java.util.Map;

import br.com.scheid.dto.QueryDTO;
import br.com.scheid.filters.IngressoFilter;
import br.com.scheid.model.Ingresso;

public class IngressoQueryGenerator {

	public QueryDTO getQuery(IngressoFilter ingf){
		
		Map<String, Object> params = new HashMap<>();
		StringBuilder sql = new StringBuilder();
		sql.append("Select x from " + Ingresso.class.getName() + " x ");
		sql.append(" where 1=1");
		if (ingf.getNome() != null && !ingf.getNome().equals("")) {

			sql.append(" and UPPER(x.nome) LIKE :pNome ");
			params.put("pNome", "%" + ingf.getNome().toUpperCase() + "%");

		}

		
		

		return new QueryDTO(sql.toString(), params);
	}
	
}
