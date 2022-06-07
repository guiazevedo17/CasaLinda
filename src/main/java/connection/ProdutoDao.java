package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Produto;

public class ProdutoDao {

	public void adicionaProduto(Produto produto){
		Connection connection =  new ConexaoDao().conectaDataBase();

		try {
			String query = "insert into produto(dia, mes, ano, nome, tipo, fornecedor, descricao, quantidade, preco_custo, preco_venda) values('"+ produto.getDia() +"','" + produto.getMes() +"','" + produto.getAno() +"','"+ produto.getNome() +"','"+ produto.getTipo() +"','"+ produto.getFornecedor() +"','"+ produto.getDescricao() +"','"+ produto.getQuantidade() +"','"+ produto.getPreco_custo() +"','"+ produto.getPreco_venda() +"')";
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.execute();
			statement.close();

		} catch (SQLException throwables) {
			
			throwables.printStackTrace();
		
		}
	}

	public ResultSet procuraProdutoEstoque(Produto produto){
        Connection connection =  new ConexaoDao().conectaDataBase();

        try {
			String query = "select * from estoque where nome = '" +produto.getNome()+ "'";
			            
			PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            return rs;
        }catch (SQLException e){
            return null;
        }
    }

	public ResultSet procuraProduto(String nome){
		Connection connection =  new ConexaoDao().conectaDataBase();

		try {
			String query = "select * from produto where nome = '" +nome+ "'";
			
			PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            return rs;
        }catch (SQLException throwables){
			throwables.printStackTrace();
            return null;
        }
	}

	public void atualizaProduto(String nome, String tipo, String fornecedor, String descricao, String quantidade, String custo, String venda, int cod){
		Connection connection = new ConexaoDao().conectaDataBase();
		
		try {			
			String query = "update produto set nome = '" +nome+ "', tipo = '" +tipo+ "', fornecedor = '" +fornecedor+ "', descricao = '" +descricao+ "', quantidade = '" +quantidade+ "', preco_custo = '" +custo+ "', preco_venda = '" +venda+ "'  where cod_prod = '" +cod+ "'";
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.execute();
			statement.close();
			
		} catch (SQLException throwables) {
			System.out.println("Problema atualiza PRODUTO");
			throwables.printStackTrace();
		}
	}
}