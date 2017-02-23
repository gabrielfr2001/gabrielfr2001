package br.com.scheid.mbean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.scheid.model.Configuracoes;

@ManagedBean
@SessionScoped
public class ApplicationMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Configuracoes configuracoes;
	private Locale locale;
	
	public ApplicationMBean() {
		this.configuracoes = new Configuracoes();
		this.alterarIdioma();
	}
	
	public String init(){
		return "/pages/config/config?faces-redirect=true";
	}
	
	public void alterarIdioma(){
		this.locale = new Locale(this.configuracoes.getLingua());
	}
	
	public Configuracoes getConfiguracoes() {
		return configuracoes;
	}

	public void setConfiguracoes(Configuracoes configuracoes) {
		this.configuracoes = configuracoes;
	}

	public Locale getLocale() {
		return locale;
	}
	
	public String getHoraServidor() {
		return LocalDate.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
	}
	
}
