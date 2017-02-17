package br.com.scheid.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.scheid.dao.GenericDAO;
import br.com.scheid.filters.IngressoFilter;
import br.com.scheid.model.Ingresso;
import br.com.scheid.viewmodel.IU02_01ViewModel;

@ManagedBean
@ViewScoped
public class IU02_01MBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String linguaEscolhida = "en";
	public Locale locale;
	
	public Ingresso ingresso;
	public List<Ingresso> ingressosSelecionados;
	public List<Ingresso> ingressosCadastrados;
	public Ingresso ingressoPesquisar;
	public GenericDAO dao;
	public IU02_01ViewModel viewModel;
	public IngressoFilter filter;
	
	
	public String init(){
		locale = new Locale(linguaEscolhida);
		FacesContext instance = FacesContext.getCurrentInstance();
		instance.getViewRoot().setLocale(locale);
		
		dao = new GenericDAO();
		ingressosCadastrados = new ArrayList<Ingresso>();
		ingressosSelecionados = new ArrayList<Ingresso>();
		ingresso = new Ingresso();
		this.filter = new IngressoFilter();
		this.viewModel = new IU02_01ViewModel();
		
		this.onBuscar();
		return "config?faces-redirect=true";
	}
	
	public void onAtivarIngresso(){
		for(int i=0; i < this.ingressosSelecionados.size();i++){
			if( ingressosSelecionados.get(i).getAtivo() == false){
				ingressosSelecionados.get(i).setAtivo(true);
			} else{
				ingressosSelecionados.get(i).setAtivo(false);
			}
			dao.salvar(ingressosSelecionados.get(i));
		}
		ingressosSelecionados.clear();
		this.onBuscar();
	}
	
	public void onExcluirIngresso(){
		for(int i=0; i< this.ingressosSelecionados.size();i++){
			this.viewModel.removerIngresso(this.ingressosSelecionados.get(i).getId());
		}
		this.onBuscar();
	}
	
	public void onBuscar(){
		this.ingressosCadastrados = this.viewModel.buscarIngresso(this.filter);
	}
	
	public void onEditIngresso(){
		dao.salvar(ingressosSelecionados.get(0));
		ingresso = new Ingresso();
		this.onBuscar();
	}
	
	public void onSaveNovoIngresso(){
		dao.salvar(ingresso);
		ingresso = new Ingresso();
		this.onBuscar();
	}
	
	public void onNovoIngresso(){
		ingresso = new Ingresso();
	}
	public Boolean buttonEditIsDisable(){
		return this.ingressosSelecionados.size() != 1;
	}
	
	public Boolean buttonExcluirIsDisable(){
		return this.ingressosSelecionados.size() == 0;
	}
	
	public IU02_01MBean(){
		this.init();
	}
	
	public String getLinguaEscolhida() {
		return linguaEscolhida;
	}
	public void setLinguaEscolhida(String linguaEscolhida) {
		this.linguaEscolhida = linguaEscolhida;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public Ingresso getIngresso() {
		return ingresso;
	}
	public void setIngresso(Ingresso ingresso) {
		this.ingresso = ingresso;
	}
	public List<Ingresso> getIngressosSelecionados() {
		return ingressosSelecionados;
	}
	public void setIngressosSelecionados(List<Ingresso> ingressosSelecionados) {
		this.ingressosSelecionados = ingressosSelecionados;
	}
	public List<Ingresso> getIngressosCadastrados() {
		return ingressosCadastrados;
	}
	public void setIngressosCadastrados(List<Ingresso> ingressosCadastrados) {
		this.ingressosCadastrados = ingressosCadastrados;
	}
	public Ingresso getIngressoPesquisar() {
		return ingressoPesquisar;
	}
	public void setIngressoPesquisar(Ingresso ingressoPesquisar) {
		this.ingressoPesquisar = ingressoPesquisar;
	}
	public GenericDAO getDao() {
		return dao;
	}
	public void setDao(GenericDAO dao) {
		this.dao = dao;
	}
	public IU02_01ViewModel getViewModel() {
		return viewModel;
	}
	public void setViewModel(IU02_01ViewModel viewModel) {
		this.viewModel = viewModel;
	}
	public IngressoFilter getFilter() {
		return filter;
	}
	public void setFilter(IngressoFilter filter) {
		this.filter = filter;
	}
	
	
}
