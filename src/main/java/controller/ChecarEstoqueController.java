package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import connection.EstoqueDao;
import entity.Estoque;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tables.TabelaEstoque;

public class ChecarEstoqueController implements Initializable {

    @FXML
    private Button btnRetorna, btnBuscarNome, btnCancelarBusca, btnEditar;

	@FXML
    private TextField txtNome;

	@FXML // tabela do relatório (movimentacao)
    private TableView<TabelaEstoque> tbEstoque;

    @FXML // colunas da tabela do relatorio (movimentacao) - cod
    private TableColumn<TabelaEstoque, Integer> tbclmCod;

	@FXML // colunas da tabela do relatorio (movimentacao) - nome
    private TableColumn<TabelaEstoque, String> tbclmNome;
	
	@FXML // colunas da tabela do relatorio (movimentacao) - tipo
    private TableColumn<TabelaEstoque, String>tbclmTipo;
	
	@FXML // colunas da tabela do relatorio (movimentacao) - quantia
    private TableColumn<TabelaEstoque, Integer>tbclmQuantia;
	
	@FXML // colunas da tabela do relatorio (movimentacao) - preço
    private TableColumn<TabelaEstoque, Float>tbclmPreco;

	public static String nomeProd = "";

	public void listarProdutosEstoque() {
		tbEstoque.getItems().clear();

		tbclmCod.setCellValueFactory(new PropertyValueFactory("cod_prod"));
		tbclmNome.setCellValueFactory(new PropertyValueFactory("nome"));
		tbclmTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
		tbclmQuantia.setCellValueFactory(new PropertyValueFactory("quantidade"));
		tbclmPreco.setCellValueFactory(new PropertyValueFactory("preco"));
	
		Estoque estoque = new Estoque();
		EstoqueDao daoEstq = new EstoqueDao();	
		ResultSet rsdaoEstq = daoEstq.listEstoque(estoque);
		ObservableList<TabelaEstoque> listTabelaEstoque = FXCollections.observableArrayList();

		try{
			while(rsdaoEstq.next()){

				TabelaEstoque  t = new TabelaEstoque(rsdaoEstq.getInt("cod_prod"),rsdaoEstq.getString("nome"),rsdaoEstq.getString("tipo"),rsdaoEstq.getInt("quantidade"),rsdaoEstq.getFloat("preco"));
				listTabelaEstoque.add(t);
				tbEstoque.setItems(listTabelaEstoque);
	
			}
		} catch (Exception e) {
			System.out.println(e);
			
		}
	}

	@FXML
    void buscaNome(ActionEvent event) {
		EstoqueDao daoEstq = new EstoqueDao();

		ResultSet rsdaoEstq = daoEstq.verificaNome(txtNome.getText()); // retorna o produto com nome digitado

		nomeProd = txtNome.getText();

		try {
			if(rsdaoEstq.next()){
				tbEstoque.getItems().clear();

				tbclmCod.setCellValueFactory(new PropertyValueFactory("cod_prod"));
				tbclmNome.setCellValueFactory(new PropertyValueFactory("nome"));
				tbclmTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
				tbclmQuantia.setCellValueFactory(new PropertyValueFactory("quantidade"));
				tbclmPreco.setCellValueFactory(new PropertyValueFactory("preco"));

				ObservableList<TabelaEstoque> listTabelaEstoque = FXCollections.observableArrayList();

				try{
		
					TabelaEstoque  t = new TabelaEstoque(rsdaoEstq.getInt("cod_prod"),rsdaoEstq.getString("nome"),rsdaoEstq.getString("tipo"),rsdaoEstq.getInt("quantidade"),rsdaoEstq.getFloat("preco"));
					listTabelaEstoque.add(t);
					tbEstoque.setItems(listTabelaEstoque);
			
					
				} catch (Exception e) {
					System.out.println(e);
					
				}

			} else {
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setTitle("CASA LINDA - Nome Inválido !");

				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/nomeInvalido.fxml"));
				Parent root = fxmlLoader.load();
				Scene scene = new Scene(root);

				scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
				window.setScene(scene);
				
				window.centerOnScreen();
				window.show();
			}
		} catch (Exception e) {
			//TODO: handle exception
		}
    }

	@FXML
    void cancelaBuscaNome(ActionEvent event) {
		txtNome.setText("");
		listarProdutosEstoque();
    }

    @FXML // Retorna para o Menu
    void retornaMenu(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setTitle("CASA LINDA - Controle de Estoque");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/menu.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
		window.setScene(scene);
		
		window.centerOnScreen();
		window.show();
    }

	@FXML
    void editaProduto(ActionEvent event) throws IOException {

		if(nomeProd.equals("")){
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setTitle("CASA LINDA - Nenhum Produto Selecionado");

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/nenhumProdutoSelecionado.fxml"));
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
			window.setScene(scene);
			
			window.centerOnScreen();
			window.show();
		} else {
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setTitle("CASA LINDA - Edita Produto");

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/editaProduto.fxml"));
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
			window.setScene(scene);
			
			window.centerOnScreen();
			window.show();
		}
    }
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		listarProdutosEstoque();
		nomeProd =  "";
	}
	
}