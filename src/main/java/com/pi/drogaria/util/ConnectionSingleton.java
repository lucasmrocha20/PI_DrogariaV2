package com.pi.drogaria.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.omnifaces.util.Messages;

public class ConnectionSingleton {

	private Connection conexao;
	private String url = "jdbc:postgresql://localhost:5432/Drogaria";
	private String user = "postgres";
	private String senha = "12345";
	private static ConnectionSingleton instancy;
	
	private ConnectionSingleton(){
		try{
			Class.forName("org.postgresql.Driver");
		}catch(Exception e){
			System.out.println("Drive nao encontrado.");
		}
		
		try{
			this.conexao = DriverManager.getConnection(url, user, senha);
		}catch(Exception e){
			Messages.addGlobalError("Houve um erro ao tentar conectar ao banco de dados");
		}
	}
	
	public static ConnectionSingleton getInstancy(){
		if(instancy == null){
			instancy = new ConnectionSingleton();
		}
		return instancy;
	}
	
	public Connection getConexao(){
		return this.conexao;
	}
}
