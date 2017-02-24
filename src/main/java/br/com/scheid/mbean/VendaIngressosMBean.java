package br.com.scheid.mbean;

import java.io.Serializable; 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.scheid.enums.EnumTipoPagamento;
import br.com.scheid.filters.IngressoFilter;
import br.com.scheid.model.Ingresso;
import br.com.scheid.model.Pagamento;
import br.com.scheid.model.VendaIngresso;
import br.com.scheid.to.IngressoVendido;
import br.com.scheid.viewmodel.VendaIngressosViewModel;

@ManagedBean
@ViewScoped
public class VendaIngressosMBean extends AbstractCommonMBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<Ingresso> todosIngressos;
	public List<Ingresso> todosIngressosAtivados;
	public IngressoFilter filter;
	public VendaIngressosViewModel viewModel;
	public Ingresso ingressoSelecionado;
	public int linhas;
	public Long idIngressoRemover;
	public List<Ingresso> ingressosSelecionados;
	public List<IngressoVendido> ingressosVendidos;
	public float totalComanda;
	public VendaIngresso venda;
	public Pagamento pagamento;
	public String tipoPagamento;
	public List<Pagamento> pagamentos;
	public float totalPendente;
	public List<Float> pags;
	public String valor;
	public float subTotal;
	
	public void salvarVenda(){
		for(Pagamento p : this.pagamentos){
			this.viewModel.salvar(p);
		}
		
		this.venda.setData(new Date());
		this.venda.setPagamentos(pagamentos);
		for(IngressoVendido ingressoVendido : this.ingressosVendidos){
			for(int i = 0; i < ingressoVendido.getQuantidade(); i++){
				this.venda.getIngressos().add(ingressoVendido.getIngresso());
			}
		}
		this.viewModel.salvar(this.venda);
	}
	
	public float calcularTotal(){
		float v = 0;
		v = this.subTotal + (this.subTotal * 0.1f);
		return v;
	}
	
	public float calcularTotalPago(){
		float total = 0;
		for(int i=0; i<pagamentos.size();i++){
			total += pagamentos.get(i).getValor();
		}
		return total; 
	}
	public void calcularSubTotal(){
		this.subTotal = 0;
		for(int i=0; i<ingressosVendidos.size();i++){
			subTotal += ingressosVendidos.get(i).getQuantidade() * ingressosVendidos.get(i).getIngresso().getPreco();
		}
	}
	
	public void onFinalizarCompras(){
		venda = new VendaIngresso();
		this.calcularSubTotal();
		totalPendente = this.subTotal + (this.subTotal * 0.1f);
	}
	
	public Boolean btnIsDisibled(){
		return totalPendente != 0;
	}
	
	public void onAdicionarValor(){
		this.pagamento.setPagamento(Float.parseFloat(valor), EnumTipoPagamento.values()[Integer.parseInt(this.tipoPagamento)]);
		this.pagamentos.add(pagamento);
		this.totalPendente -= Float.parseFloat(valor);
	}
	public void Init(){
		
 		this.pagamento = new Pagamento();
		this.filter = new IngressoFilter();
		this.viewModel = new VendaIngressosViewModel();
		this.pagamentos = new ArrayList<Pagamento>();
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
	
	public void onAdicionarPagamento(){
		if(this.pagamento == null){
			this.pagamento = new Pagamento();
		}
		this.pagamento.setPagamento(20, EnumTipoPagamento.values()[Integer.parseInt(this.tipoPagamento)]);
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

	@Override
	public String getBundleDir() {
		return "br/com/scheid/locale/venderIngressos";
	}
	
	public String getHora(){
		LocalDateTime d = LocalDateTime.now();
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:SS");
		return d.format(f);
	}


	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public float getTotalPendente() {
		return totalPendente;
	}

	public void setTotalPendente(float totalPendente) {
		this.totalPendente = totalPendente;
	}

	public List<Float> getPags() {
		return pags;
	}

	public void setPags(List<Float> pags) {
		this.pags = pags;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}
	
	
}

