package br.com.scheid.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionUtil {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	
	public static EntityManagerFactory getEntityManagerFactory(){
		if(entityManagerFactory == null){
			entityManagerFactory = Persistence.createEntityManagerFactory("Open-Bar");
		}
		return entityManagerFactory;
	}
	
	public static EntityManager getEntityManager(){
		if(entityManager == null){
			entityManager = getEntityManagerFactory().createEntityManager();
		}
		return entityManager;
	}
}