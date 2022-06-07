package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;
import connection.EstoqueDao;
import connection.ProdutoDao;
import javafx.fxml.Initializable;

public class EditaProdutoController implements Initializable {

	@FXML
    private Button btnExcluir, btnSalvar;

    @FXML
    private Label lblDia, lblMes, lblAno;

	@FXML 
    private TextField txtNome, txtTipo,txtFornecedor, txtQuantidade, txtPrecoCusto, txtPrecoVenda;

    @FXML 
    private TextArea txtaDescricao;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ProdutoDao daoProd = new ProdutoDao();
		
		ResultSet rsdaoProd = daoProd.procuraProduto(ChecarEstoqueController.nomeProd);
		
		try {
			if(rsdaoProd.next()){

					lblDia.setText(Integer.toString(rsdaoProd.getInt("dia")));
					lblMes.setText(Integer.toString(rsdaoProd.getInt("mes")));
					lblAno.setText(Integer.toString(rsdaoProd.getInt("ano")));
					txtNome.setText(rsdaoProd.getString("nome"));
					txtTipo.setText(rsdaoProd.getString("tipo"));
					txtFornecedor.setText(rsdaoProd.getString("fornecedor"));
					txtaDescricao.setText(rsdaoProd.getString("descricao"));
					txtQuantidade.setText(Integer.toString(rsdaoProd.getInt("quantidade")));
					txtPrecoCusto.setText(Float.toString(rsdaoProd.getFloat("preco_custo")));
					txtPrecoVenda.setText(Float.toString(rsdaoProd.getFloat("preco_venda")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
    void excluiProduto(ActionEvent event) throws IOException {
		EstoqueDao daoEstqNome = new EstoqueDao();

		ResultSet rsdaoEstqNome = daoEstqNome.verificaNome(ChecarEstoqueController.nomeProd);

		try {
			if(rsdaoEstqNome.next()){
				int codProd = rsdaoEstqNome.getInt("cod_prod");

				daoEstqNome.excluiProdutoEstoque(codProd);
			}
			
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setTitle("CASA LINDA - Produto Excluido");

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/confirmacaoExclusao.fxml"));
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
			window.setScene(scene);
			
			window.centerOnScreen();
			window.show();

		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
    void salvaAlteracao(ActionEvent event) throws IOException {
		ProdutoDao daoProd = new ProdutoDao();
		EstoqueDao daoEstq = new EstoqueDao();

		ResultSet rsdaoProd = daoProd.procuraProduto(ChecarEstoqueController.nomeProd);
		ResultSet rsdaoEstq = daoEstq.verificaNome(ChecarEstoqueController.nomeProd);
		
		try {
			if(rsdaoProd.next() && rsdaoEstq.next()){
				daoProd.atualizaProduto(txtNome.getText(), txtTipo.getText(), txtFornecedor.getText(), txtaDescricao.getText(), txtQuantidade.getText(), txtPrecoCusto.getText(), txtPrecoVenda.getText(), rsdaoProd.getInt("cod_prod"));
				daoEstq.atualizaEstoque(txtNome.getText(), txtTipo.getText(), txtQuantidade.getText(), txtPrecoVenda.getText(), rsdaoEstq.getInt("cod_prod"));
			}
			
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setTitle("CASA LINDA - Produto Editado");

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/confirmacaoEdicao.fxml"));
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
			window.setScene(scene);
			
			window.centerOnScreen();
			window.show();

		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
}
