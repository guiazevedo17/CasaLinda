package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Estoque;
import entity.Produto;

public class EstoqueDao {

	public void adicionaProdutoEstoque(Produto produto){ // Adiciona produto cadastrado ao ESTOQUE
		Connection connection =  new ConexaoDao().conectaDataBase();
		
		try {			
			String query = "insert into estoque(nome, tipo, quantidade, preco) values ('" +produto.getNome()+ "','" +produto.getTipo()+ "','" +produto.getQuantidade()+ "','" +produto.getPreco_venda()+ "')";;
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.execute();
			statement.close();
			

		} catch (SQLException throwables) {
			
			throwables.printStackTrace();
		
		}
	}

	public ResultSet listEstoque(Estoque estoque){ // Lista todos os produtos na tabela ESTOQUE
		Connection connection =  new ConexaoDao().conectaDataBase();

		try {			
			String query = "select * from estoque";
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet rs = statement.executeQuery();
			
			return rs;
		} catch (SQLException throwables) {
			
			throwables.printStackTrace();
			return null;
		}
	}

	public ResultSet verificaCodigo(int codigo){ // Verifica se o produto em questão existe em ESTOQUE
		Connection connection = new ConexaoDao().conectaDataBase();
		
		try {			
			String query = "select * from estoque where cod_prod = '" +codigo+ "'";
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet rs = statement.executeQuery();
			
			return rs;
		} catch (SQLException throwables) {
			
			throwables.printStackTrace();
			return null;
		}
	}

	public ResultSet verificaNome(String nome){ // Verifica se o produto em questão existe em ESTOQUE
		Connection connection = new ConexaoDao().conectaDataBase();
		
		try {			
			String query = "select * from estoque where nome = '" +nome+ "'";
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet rs = statement.executeQuery();
			
			return rs;
		} catch (SQLException throwables) {
			
			throwables.printStackTrace();
			return null;
		}
	}

	public void atualizaQuantidadeEstoque(int quantidade, int cod_prod){
		Connection connection = new ConexaoDao().conectaDataBase();
		
		try {			
			String query = "update estoque set quantidade = '" +quantidade+ "' where cod_prod = '" +cod_prod+ "'";
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.execute();
			statement.close();
			
		} catch (SQLException throwables) {
			
			throwables.printStackTrace();
		}
	}

	public void atualizaEstoque(String nome, String tipo, String quantidade, String preco, int cod){
		Connection connection = new ConexaoDao().conectaDataBase();
		
		try {			
			String query = "update estoque set nome = '" +nome+ "', tipo = '" +tipo+ "', quantidade = '" +quantidade+ "', preco = '" +preco+ "'  where cod_prod = '" +cod+ "'";
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.execute();
			statement.close();
			
		} catch (SQLException throwables) {
			System.out.println("Problema atualiza ESTOQUE");
			throwables.printStackTrace();
		}
	}

	public void excluiProdutoEstoque(int cod_prod){
		Connection connection = new ConexaoDao().conectaDataBase();
		
		try {			
			String query = "delete from estoque where cod_prod = '" +cod_prod+ "'";
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.execute();
			statement.close();

		} catch (SQLException throwables) {
			
			throwables.printStackTrace();
		}
	}
}
