package br.com.scheid.mbean;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.scheid.dao.GenericDAO;
import br.com.scheid.filters.IngressoFilter;
import br.com.scheid.model.Ingresso;
import br.com.scheid.model.Produto;
import br.com.scheid.utils.StringUtil;
import br.com.scheid.viewmodel.IngressosViewModel;

@ManagedBean
@ViewScoped
public class IngressosMBean extends AbstractCommonMBean implements Serializable {
	private static final long serialVersionUID = 1L;
		
	public Ingresso ingresso;
	public List<Ingresso> ingressosSelecionados;
	public List<Ingresso> ingressosCadastrados;
	public Ingresso ingressoPesquisar;
	public GenericDAO dao;
	public IngressosViewModel viewModel;
	public IngressoFilter filter;
	
	
	public void init(){
		ingressosCadastrados = new ArrayList<Ingresso>();
		ingressosSelecionados = new ArrayList<Ingresso>();
		ingresso = new Ingresso();
		this.filter = new IngressoFilter();
		this.viewModel = new IngressosViewModel();
		this.onBuscar();
	}
	
	public Boolean isIngressoValido(){
		Boolean valido = Boolean.TRUE;
		if(StringUtil.isNull(this.ingresso.getNome())){
			valido = Boolean.FALSE;
			this.addMessage(this.getLabel("msg_nome_obrigatorio"), FacesMessage.SEVERITY_ERROR);
		}
		
		if(this.ingresso.getPreco() == 0){
			valido = Boolean.FALSE;
			this.addMessage(this.getLabel("msg_preco_obrigatorio"), FacesMessage.SEVERITY_ERROR);
		}
		
		if(this.ingresso.getEstoque() == null && this.usaEstoque()){
			valido = Boolean.FALSE;
			this.addMessage(this.getLabel("msg_estoque_obrigatorio"), FacesMessage.SEVERITY_ERROR);
		}
		return valido;
	}
	
	public void onAtivarIngresso(){
		for(int i=0; i < this.ingressosSelecionados.size();i++){
			if( ingressosSelecionados.get(i).getAtivo() == false){
				ingressosSelecionados.get(i).setAtivo(true);
			} else{
				ingressosSelecionados.get(i).setAtivo(false);
			}
			this.viewModel.salvarIngresso(ingressosSelecionados.get(i));
		}
		ingressosSelecionados.clear();
		this.onBuscar();
	}
	
	public void onExcluirIngresso(){
		Integer qtdExcluida = 0;
		for(int i=0; i< this.ingressosSelecionados.size();i++){
			qtdExcluida++;
			this.viewModel.removerIngresso(this.ingressosSelecionados.get(i).getId());
		}
		this.addMessage(MessageFormat.format(this.getLabel("msg_excluir"), qtdExcluida.toString()), FacesMessage.SEVERITY_ERROR);
		this.init();;
	}
	
	public void onBuscar(){
		this.ingressosCadastrados = this.viewModel.buscarIngresso(this.filter);
	}
	
	public void onEditIngresso(){
		this.ingresso = this.ingressosSelecionados.get(0);
	}
	
	public void onSaveNovoIngresso(){
		if(this.isIngressoValido()){
			if(this.ingresso.getId() == null){
				this.addMessage(this.getLabel("msg_salvar"), FacesMessage.SEVERITY_ERROR);
			}else {
				this.addMessage(MessageFormat.format(this.getLabel("msg_editar"), this.getIngresso().getNome()), FacesMessage.SEVERITY_ERROR);
			}
			
			this.viewModel.salvarIngresso(ingresso);
			ingresso = new Ingresso();
			this.onBuscar();
			this.fecharDialog();
		}
	}
	
	private void fecharDialog(){
		RequestContext.getCurrentInstance().execute("PF('dialogManutencaoIngressoWV').hide();");
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
	
	public IngressosMBean(){
		this.init();
		this.viewModel = new IngressosViewModel();
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
	public IngressosViewModel getViewModel() {
		return viewModel;
	}
	public void setViewModel(IngressosViewModel viewModel) {
		this.viewModel = viewModel;
	}
	public IngressoFilter getFilter() {
		return filter;
	}
	public void setFilter(IngressoFilter filter) {
		this.filter = filter;
	}

	@Override
	public String getBundleDir() {
		return "br/com/scheid/locale/ingressos";
	}
	
	
}
