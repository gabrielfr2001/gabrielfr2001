package br.com.scheid.mbean;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

import br.com.scheid.filters.ProdutoFilter;
import br.com.scheid.model.Produto;
import br.com.scheid.utils.StringUtil;
import br.com.scheid.viewmodel.ProdutosViewModel;

@ManagedBean
@ViewScoped
public class ProdutosMBean extends AbstractCommonMBean implements Serializable{
    private static final long serialVersionUID = 1L;

	public Produto produto;
	public List<Produto> produtosSelecionados;
	public List<Produto> produtosCadastrados;
	public ProdutosViewModel viewModel;
	public ProdutoFilter filter;
	
	public void onExcluirProduto(){
		Integer quantidadeExcluida = 0;
		for(int i=0; i < this.produtosSelecionados.size();i++){
			quantidadeExcluida ++;
			this.viewModel.removerProduto(this.produtosSelecionados.get(i).getId());
		}
		this.addMessage(MessageFormat.format(this.getLabel("msg_excluir"), quantidadeExcluida.toString()), FacesMessage.SEVERITY_ERROR);
		this.init();
	}
	
	public void onAtivarProduto(){
		this.viewModel.ativarProdutos(this.produtosSelecionados);
		this.onBuscar();
	} 
	
	public void onEditProduto(){
		this.produto = this.produtosSelecionados.get(0);
	}
	
	public void onNovoProduto(){
		this.produto = new Produto();
		this.produto.setAtivo(Boolean.TRUE);
	}
	
	public void onSaveProduto(){
		if(this.isProdutoValido()){
			if(this.produto.getId() == null){
				this.addMessage(this.getLabel("msg_salvar"), FacesMessage.SEVERITY_ERROR);
			}else {
				this.addMessage(MessageFormat.format(this.getLabel("msg_editar"), this.getProduto().getNome()), FacesMessage.SEVERITY_ERROR);
			}
			
			this.viewModel.salvarProduto(produto);
			produto = new Produto();
			this.onBuscar();
			this.fecharDialog();
		}
	}

	public void init(){
		produtosSelecionados = new ArrayList<Produto>();
		produtosCadastrados = new ArrayList<Produto>();
		produto = new Produto();
		this.filter = new ProdutoFilter();
		this.viewModel = new ProdutosViewModel();
		this.onBuscar();
	}
	
	public void onNovaPesquisa(){
		this.filter = new ProdutoFilter();
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
	
	public ProdutosMBean() {
		this.init();
		this.viewModel = new ProdutosViewModel();
	}
	
	private void fecharDialog(){
		RequestContext.getCurrentInstance().execute("PF('dialogManutencaoProdutoWV').hide();");
	}
	
	private Boolean isProdutoValido(){
		Boolean valido = Boolean.TRUE;
		if(StringUtil.isNull(this.produto.getNome())){
			valido = Boolean.FALSE;
			this.addMessage(this.getLabel("msg_nome_obrigatorio"), FacesMessage.SEVERITY_ERROR);
		}
		
		if(this.produto.getPreco() == 0){
			valido = Boolean.FALSE;
			this.addMessage(this.getLabel("msg_preco_obrigatorio"), FacesMessage.SEVERITY_ERROR);
		}
		
		if(this.produto.getEstoque() == null && this.usaEstoque()){
			valido = Boolean.FALSE;
			this.addMessage(this.getLabel("msg_estoque_obrigatorio"), FacesMessage.SEVERITY_ERROR);
		}
		return valido;
	}
	
	@Override
	public String getBundleDir() {
		return "br/com/scheid/locale/produtos";
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

	public ProdutosViewModel getViewModel() {
		return viewModel;
	}

	public void setViewModel(ProdutosViewModel viewModel) {
		this.viewModel = viewModel;
	}

	public ProdutoFilter getFilter() {
		return filter;
	}

	public void setFilter(ProdutoFilter filter) {
		this.filter = filter;
	}
}
