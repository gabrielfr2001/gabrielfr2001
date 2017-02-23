package br.com.scheid.model;

public class Configuracoes {
	
	private String lingua;
	private Boolean utilizarEstoque;
	private String empresa;
	
	public Configuracoes(){
		this.utilizarEstoque = Boolean.TRUE;
		this.lingua = "pt";
	}

	public String getLingua() {
		return lingua;
	}

	public void setLingua(String lingua) {
		this.lingua = lingua;
	}

	public Boolean getUtilizarEstoque() {
		return utilizarEstoque;
	}

	public void setUtilizarEstoque(Boolean utilizarEstoque) {
		this.utilizarEstoque = utilizarEstoque;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}


	
	
}
