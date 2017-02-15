package br.com.scheid.mbean;

import java.io.Serializable; 
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class UsuarioMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public String linguaEscolhida = "pt";
	public Locale locale;
	public Boolean gerenciarEstoque = false;
	
	
	
	public Boolean getGerenciarEstoque() {
		return gerenciarEstoque;
	}

	public void setGerenciarEstoque(Boolean gerenciarEstoque) {
		this.gerenciarEstoque = gerenciarEstoque;
	}

	public String init(){
		return "config?faces-redirect=true";
	}
	
	public String alterarIdioma(){
		locale = new Locale(linguaEscolhida);
		FacesContext instance = FacesContext.getCurrentInstance();
		instance.getViewRoot().setLocale(locale);
		return "config?faces-redirect=true";
	}


	public String getLinguaEscolhida() {
		return linguaEscolhida;
	}


	public void setLinguaEscolhida(String linguaEscolhida) {
		this.linguaEscolhida = linguaEscolhida;
	}


	public Locale getLocale() {
		if(locale==null){
			locale = new Locale(linguaEscolhida);
		}
		return locale;
	}


	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	

}
