package br.com.scheid.dto;

import java.util.Map;

public class QueryDTO {
	
	private String query;
	private Map<String, Object> params;
	
	public QueryDTO(String query, Map<String, Object> params) {
		super();
		this.query = query;
		this.params = params;
	}
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}
