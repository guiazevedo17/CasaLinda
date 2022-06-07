package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovimentacaoDao {

	public void adicionaMovimentacao(int codigo, String nome, int quantidade, String status, String dia, String mes, String ano, String pagamento, String preco_custo){
		Connection connection =  new ConexaoDao().conectaDataBase();
		
		try {			
			String query = "insert into movimentacao(cod_prod, nome, quantidade, status, dia, mes, ano, forma_pagamento, valor) values ('" +codigo+ "','" +nome+ "','" +quantidade+ "','"+ status +"','" +dia+ "','" +mes+ "','" +ano+ "','" +pagamento+ "','" +preco_custo+ "')";
						
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.execute();
			statement.close();
			
		} catch (SQLException throwables) {
			
			throwables.printStackTrace();
		
		}
	}

	public ResultSet listMovimentacao(){
		Connection connection =  new ConexaoDao().conectaDataBase();

		try {			
			String query = "select * from movimentacao";
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet rs = statement.executeQuery();
			
			return rs;
		} catch (SQLException throwables) {
			
			throwables.printStackTrace();
			return null;
		}
	}

	public ResultSet listMovimentacaoMes(String mes){
		Connection connection =  new ConexaoDao().conectaDataBase();

		try {			
			String query = "select * from movimentacao where mes = '" +mes+ "'";
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet rs = statement.executeQuery();
			
			return rs;
		} catch (SQLException throwables) {
			
			throwables.printStackTrace();
			return null;
		}
	}

	public ResultSet listMovimentacaoAno(String ano){
		Connection connection =  new ConexaoDao().conectaDataBase();

		try {			
			String query = "select * from movimentacao where ano = '" +ano+ "'";
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet rs = statement.executeQuery();
			
			return rs;
		} catch (SQLException throwables) {
			
			throwables.printStackTrace();
			return null;
		}
	}
}


