package br.com.scheid.mbean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.scheid.dao.GenericDAO;
import br.com.scheid.filters.ProdutoFilter;
import br.com.scheid.model.LineType;
import br.com.scheid.model.Order;
import br.com.scheid.model.OrderLine;
import br.com.scheid.model.Produto;
import br.com.scheid.viewmodel.IU01_01ViewModel;

@ManagedBean
@ViewScoped
public class IU01_01MBean implements Serializable{
	private static final long serialVersionUID = 1L;

	
	public Produto produto;
	public List<Produto> produtosSelecionados;
	public List<Produto> produtosCadastrados;
	public Produto produtoPesquisar;
	public GenericDAO dao;
	public IU01_01ViewModel viewModel;
	public ProdutoFilter filter;
	public String linguaEscolhida = "en";
	public Locale locale;
	
	public void onExcluirProduto(){
		for(int i=0; i < this.produtosSelecionados.size();i++){
			this.viewModel.removerProduto(this.produtosSelecionados.get(i).getId());
		}
		this.init();
	}
	
	public void onAtivarProduto(){
		for(int i=0; i < this.produtosSelecionados.size();i++){
			if( produtosSelecionados.get(i).getAtivo() == false){
				produtosSelecionados.get(i).setAtivo(true);
			} else{
				produtosSelecionados.get(i).setAtivo(false);
			}
			System.out.println("Batata");
			dao.salvar(produtosSelecionados.get(i));
		}
		this.onBuscar();
	}
	
	public void onEditProduto(){
		dao.salvar(produtosSelecionados.get(0));
		produto = new Produto();
		this.onBuscar();
	}
	
	public void onNovoProduto(){
		produto = new Produto();
		produto.setAtivo(Boolean.TRUE);
	}
	
	
	public void onSaveNovoProduto(){
		dao.salvar(produto);
		produto = new Produto();
		this.onBuscar();
		this.fecharDialog();
	}

	public String init(){
		locale = new Locale(linguaEscolhida);
		FacesContext instance = FacesContext.getCurrentInstance();
		instance.getViewRoot().setLocale(locale);
		dao = new GenericDAO();
		produtosSelecionados = new ArrayList<Produto>();
		produtosCadastrados = new ArrayList<Produto>();
		produto = new Produto();
		this.filter = new ProdutoFilter();
		this.viewModel = new IU01_01ViewModel();
		this.onBuscar();
		return "config?faces-redirect=true";
	}
	
	public Boolean buttonEditIsDisable(){
		return this.produtosSelecionados.size() != 1;
	}
	
	public Boolean buttonExcluirIsDisable(){
		return this.produtosSelecionados.size() == 0;
	}
	public void onBuscar(){
		this.produtosCadastrados = this.viewModel.buscarProduto(this.filter);
	}
	
	public IU01_01MBean() {
		this.init();
		OrderLine line = new OrderLine();
		line.setDescription("MacBook Pro");
		line.setPrice(new BigDecimal(2000));
		line.setType(LineType.PRODUCT);
		order.getLines().add(line);
		this.viewModel = new IU01_01ViewModel();
	}
	
	
	private void fecharDialog(){
		RequestContext.getCurrentInstance().execute("PF('dialogNovoProdutoWV').hide();");
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

	public List<Produto> getProdutosCadastrados() {
		return produtosCadastrados;
	}

	public void setProdutosCadastrados(List<Produto> produtosCadastrados) {
		this.produtosCadastrados = produtosCadastrados;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutosSelecionados() {
		return produtosSelecionados;
	}

	public void setProdutosSelecionados(List<Produto> produtosSelecionados) {
		this.produtosSelecionados = produtosSelecionados;
	}
	
	
	
	public Produto getProdutoPesquisar() {
		return produtoPesquisar;
	}


	public void setProdutoPesquisar(Produto produtoPesquisar) {
		this.produtoPesquisar = produtoPesquisar;
	}


	public GenericDAO getDao() {
		return dao;
	}


	public void setDao(GenericDAO dao) {
		this.dao = dao;
	}


	public IU01_01ViewModel getViewModel() {
		return viewModel;
	}


	public void setViewModel(IU01_01ViewModel viewModel) {
		this.viewModel = viewModel;
	}


	public ProdutoFilter getFilter() {
		return filter;
	}


	public void setFilter(ProdutoFilter filter) {
		this.filter = filter;
	}



	private Order order = new Order();
	
	private OrderLine line;
	
	
	
	public void newLine() {
		line = new OrderLine();
	}
	
	public void addLine() {
		order.getLines().add(line);
	}
	
	public void save() {
		order = new Order();
		
		FacesMessage msg = new FacesMessage("Order saved successfully!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Order getOrder() {
		return order;
	}

	public OrderLine getLine() {
		return line;
	}
}
