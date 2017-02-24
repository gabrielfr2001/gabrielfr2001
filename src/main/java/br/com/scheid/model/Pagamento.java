package br.com.scheid.model;

public class Pagamento {
	
	private float valor;
	private String tipoDePagamento;
	
	public void setPagamento(float v, String t){
		this.valor = v;
		this.tipoDePagamento = t;
	}
	
	public float getValor(){
		return this.valor;
	}
	public String getTipoDePagamento(){
		return this.tipoDePagamento;
	}
}
