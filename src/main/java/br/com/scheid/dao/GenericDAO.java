package br.com.scheid.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.scheid.dto.QueryDTO;
import br.com.scheid.model.AbstractModel;
import br.com.scheid.utils.ConnectionUtil;

public class GenericDAO {
	
	public <T extends AbstractModel<PK>, PK extends Serializable> void salvar(T entitade) {
		EntityManager entityManager = ConnectionUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entitade);
		transaction.commit();
	}

	public <T extends AbstractModel<PK>, PK extends Serializable> void deletar(Class<T> clazz, PK id) {
		T entidade = this.buscarPorId(clazz, id);
		if (entidade != null) {
			EntityManager entityManager = ConnectionUtil.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.remove(entidade);
			transaction.commit();
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends AbstractModel<?>> List<T> listarTodos(Class<T> clazz, QueryDTO qdto) {

		EntityManager entityManager = ConnectionUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Query query = entityManager.createQuery(qdto.getQuery());
		Map<String, Object> params= qdto.getParams();
		for(Map.Entry<String, Object> param : params.entrySet() ){
			query.setParameter(param.getKey(), param.getValue());
		}
		transaction.commit();
		return query.getResultList();
	}

	public <T extends AbstractModel<?>> T buscarPorId(Class<T> clazz, Serializable id) {
		EntityManager entityManager = ConnectionUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		T retorno = ConnectionUtil.getEntityManager().find(clazz, id);
		transaction.commit();
		return retorno;
	}
}
