package br.com.scheid.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.scheid.dao.GenericDAO;
import br.com.scheid.filters.IngressoFilter;
import br.com.scheid.model.Ingresso;
import br.com.scheid.viewmodel.IU03_01ViewModel;

@ManagedBean
@ViewScoped
public class IU03_01MBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<Ingresso> todosIngressos;
	public List<List<Ingresso>> ingressos5em5;
	public GenericDAO dao;
	public IngressoFilter filter;
	public IU03_01ViewModel viewModel;
	public Ingresso ingressoSelecionado;
	public int linhas;
	public List<Ingresso> ingressosSelecionados;
	
	
	public void Init(){
		
		dao = new GenericDAO();
		this.filter = new IngressoFilter();
		this.viewModel = new IU03_01ViewModel();
		
		this.todosIngressos = new ArrayList<Ingresso>();
		this.todosIngressos = this.viewModel.buscarIngresso(this.filter);
		this.ingressos5em5 = new ArrayList<List<Ingresso>>();
		
		int i = 0;
		this.linhas = this.todosIngressos.size() / 5;
		if( this.todosIngressos.size() % 5 != 0 ){
			while(i<this.linhas){
				this.ingressos5em5.add(i, todosIngressos.subList(i*5,((i+1)*5)));
				i++;
			}
			this.ingressos5em5.add(i, todosIngressos.subList(i*5, todosIngressos.size()));
		}else{
			while(i<this.linhas){
				this.ingressos5em5.add(i, todosIngressos.subList(i*5,((i+1)*5)));
				i++;
			}
		}
		
		
	}
	
	public IU03_01MBean(){
		this.Init();
	}

	public List<Ingresso> getTodosIngressos() {
		return todosIngressos;
	}

	public void setTodosIngressos(List<Ingresso> todosIngressos) {
		this.todosIngressos = todosIngressos;
	}

	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public List<List<Ingresso>> getIngressos5em5() {
		return ingressos5em5;
	}

	public void setIngressos5em5(List<List<Ingresso>> ingressos5em5) {
		this.ingressos5em5 = ingressos5em5;
	}

	public void setIngressoSelecionado(Ingresso ingressoSelecionado) {
		this.ingressoSelecionado = ingressoSelecionado;
	}

	public List<Ingresso> getIngressosSelecionados() {
		return ingressosSelecionados;
	}

	public void setIngressosSelecionados(List<Ingresso> ingressosSelecionados) {
		this.ingressosSelecionados = ingressosSelecionados;
	}
}
