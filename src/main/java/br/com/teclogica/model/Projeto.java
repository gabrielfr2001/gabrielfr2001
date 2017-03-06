package br.com.teclogica.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.teclogica.enums.Status;

public class Projeto extends Entidade {

	private String descricao;
	private Date dataInicio;
	private Date dataFinal;

	private Status status;
	private List<Requisito> requisitos = new ArrayList<Requisito>();
	private List<Funcionario> envolvidos = new ArrayList<Funcionario>();

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Requisito> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(List<Requisito> requisitos) {
		this.requisitos = requisitos;
	}

	public List<Funcionario> getEnvolvidos() {
		return envolvidos;
	}

	public void setEnvolvidos(List<Funcionario> envolvidos) {
		this.envolvidos = envolvidos;
	}

}
