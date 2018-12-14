package com.pi.drogaria.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private SessionFactory sessionFactory;
	
	private static HibernateUtil instance;
	
	private HibernateUtil() {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		} 	
	}

	public static HibernateUtil getInstance() {
		
		if (instance == null) {
			instance = new HibernateUtil();
			
		}
		
		return instance;
	}
	
	public SessionFactory getSessionFactory() {
				
		return sessionFactory;
	}
}