package br.com.scheid.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.scheid.dao.GenericDAO;
import br.com.scheid.filters.IngressoFilter;
import br.com.scheid.model.Ingresso;
import br.com.scheid.model.Venda;
import br.com.scheid.to.IngressoVendido;
import br.com.scheid.viewmodel.VendaIngressosViewModel;

@ManagedBean
@ViewScoped
public class VendaIngressosMBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<Ingresso> todosIngressos;
	public List<Ingresso> todosIngressosAtivados;
	public List<List<Ingresso>> ingressos5em5;
	public GenericDAO dao;
	public IngressoFilter filter;
	public VendaIngressosViewModel viewModel;
	public Ingresso ingressoSelecionado;
	public int linhas;
	public Long idIngressoRemover;
	public List<Ingresso> ingressosSelecionados;
	public List<IngressoVendido> ingressosVendidos;
	public float totalComanda;
	public Venda venda;
	
	public void onFinalizarCompras(){
		venda = new Venda();
	}
	
	public void Init(){
		
 		dao = new GenericDAO();
		this.filter = new IngressoFilter();
		this.viewModel = new VendaIngressosViewModel();
		this.totalComanda = 0;
		this.ingressosVendidos = new ArrayList<IngressoVendido>();
		this.ingressosSelecionados = new ArrayList<Ingresso>();
		this.todosIngressos = new ArrayList<Ingresso>();
		this.todosIngressosAtivados = new ArrayList<Ingresso>();
		this.todosIngressos = this.viewModel.buscarIngresso(this.filter);
		for(int i=0;i<todosIngressos.size();i++){
			if(todosIngressos.get(i).getAtivo())
				todosIngressosAtivados.add(todosIngressos.get(i));
		}
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
		
		this.ingressoSelecionado = new Ingresso();
	}
	

	public void adicionarIngresso(){
		Boolean existe = false;
		for(IngressoVendido iv : this.ingressosVendidos){
			if(iv.getIngresso().getId() == this.ingressoSelecionado.getId()){
				iv.setQuantidade(iv.getQuantidade() + 1);
				existe = true;
				break;
			}
		}
		
		if(!existe){
			this.ingressosVendidos.add(new IngressoVendido(this.getIngressoSelecionado(), 1));
		}
		
		this.calcularComanda();
	}
	
	public void onRemoverIngresso(){
		for(IngressoVendido iv : ingressosVendidos){
			if(iv.getIngresso().getId() == this.idIngressoRemover){
				if(iv.getQuantidade() > 1){
					iv.setQuantidade(iv.getQuantidade() - 1);
				}else {
					this.ingressosVendidos.remove(iv);
				}
				this.calcularComanda();
				break;
			}
		}
	}
	
	public void onCancelar(){
		this.ingressosVendidos.clear();
	}
	
	public void calcularComanda(){
		float total = 0;
		for(IngressoVendido iv : ingressosVendidos){
				total += iv.getIngresso().getPreco() * iv.getQuantidade();
		}
		this.totalComanda = total;
	}
	
	public VendaIngressosMBean(){
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

	public Ingresso getIngressoSelecionado() {
		return ingressoSelecionado;
	}

	public List<Ingresso> getIngressosSelecionados() {
		return ingressosSelecionados;
	}

	public void setIngressosSelecionados(List<Ingresso> ingressosSelecionados) {
		this.ingressosSelecionados = ingressosSelecionados;
	}

	public List<IngressoVendido> getIngressosVendidos() {
		return ingressosVendidos;
	}

	public Float getTotalComanda() {
		return totalComanda;
	}

	public void setTotalComanda(Float totalComanda) {
		this.totalComanda = totalComanda;
	}

	public List<Ingresso> getTodosIngressosAtivados() {
		return todosIngressosAtivados;
	}

	public void setTodosIngressosAtivados(List<Ingresso> todosIngressosAtivados) {
		this.todosIngressosAtivados = todosIngressosAtivados;
	}

	public void setIngressosVendidos(List<IngressoVendido> ingressosVendidos) {
		this.ingressosVendidos = ingressosVendidos;
	}
	public void setTotalComanda(float totalComanda) {
		this.totalComanda = totalComanda;
	}

	public Long getIdIngressoRemover() {
		return idIngressoRemover;
	}
	public void setIdIngressoRemover(Long idIngressoRemover) {
		this.idIngressoRemover = idIngressoRemover;
	}
	
}

