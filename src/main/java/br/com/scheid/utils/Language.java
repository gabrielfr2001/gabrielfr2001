package br.com.scheid.utils;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class Language {
	
	public static String linguaEscolhida = "en";
	public static Locale locale = new Locale(linguaEscolhida);
	public static FacesContext instance = FacesContext.getCurrentInstance();
	
	public static void Idioma(){
		locale = new Locale(linguaEscolhida);
		instance.getViewRoot().setLocale(locale);
	}
	public static String getLinguaescolhida() {
		return linguaEscolhida;
	}
	public static Locale getLocale() {
		return locale;
	}
	public static String getLinguaEscolhida() {
		return linguaEscolhida;
	}
	public static void setLinguaEscolhida(String linguaEscolhida) {
		Language.linguaEscolhida = linguaEscolhida;
	}
	public static void setLocale(Locale locale) {
		Language.locale = locale;
	}
	
	
}
