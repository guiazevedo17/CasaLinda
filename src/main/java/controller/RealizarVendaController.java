package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import connection.EstoqueDao;
import connection.MovimentacaoDao;
import connection.ProdutoDao;
import entity.Estoque;
import entity.Produto;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tables.TabelaEstoque;

public class RealizarVendaController implements Initializable {

    @FXML // botões
    private Button btnRetorna, btnSelecionar, btnCanelarBusca, btnRealizaVenda;

    @FXML
    private TextField txtDia, txtMes, txtAno, txtCod, txtQuantidade;

	@FXML
    private ComboBox<String> cbPagamento;
	
	private ObservableList<String> MetodoPagamento = FXCollections.observableArrayList("cartão de crédito","cartão de débito","pix");

	@FXML // tabela do estoque
    private TableView<TabelaEstoque> tbEstoque;

    @FXML // colunas da tabela do estoque - inteiros (cod, quantia)
    private TableColumn<TabelaEstoque, Integer> tbclmCod, tbclmQuantia;

	@FXML // colunas da tabela do estoque - strings (nome, tipo)
    private TableColumn<TabelaEstoque, String> tbclmNome, tbclmTipo;
	
	@FXML // colunas da tabela do estoque - float (preco)
    private TableColumn<TabelaEstoque, Float>tbclmPreco;

	int codProdVenda; // código do produto que será vendido
	String nomeProdVenda; // nome do produto que será vendido
	int qntdProdVenda; // quantidade do produto que será vendido

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
    void buscaCodigo(ActionEvent event) throws IOException {
		EstoqueDao daoEstq = new EstoqueDao();

		if(txtCod.getText() == ""){ // caso nenhum código tenha sido digitado
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setTitle("CASA LINDA - Código Inválido !");

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/codigoInvalido.fxml"));
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
			window.setScene(scene);
				
			window.centerOnScreen();
			window.show();
			return;
		} 

		if(eNumero(txtCod.getText()) == false){
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setTitle("CASA LINDA - Código Inválido !");

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/codigoInvalido.fxml"));
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
			window.setScene(scene);
				
			window.centerOnScreen();
			window.show();
			return;
		}


		ResultSet rsdaoEstq = daoEstq.verificaCodigo(Integer.parseInt(txtCod.getText())); // retorna o produto com código digitado

		try {
			if(rsdaoEstq.next()){

				codProdVenda = rsdaoEstq.getInt("cod_prod"); // armazena o código do produto que será vendido
				nomeProdVenda = rsdaoEstq.getString("nome"); // armazena o nome do produto que será vendido
				qntdProdVenda = rsdaoEstq.getInt("quantidade"); // armazena quantidade do produto que será vendido (quantidade disponível)

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
			} else { // caso o código digitado não tenha sido encontrado
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setTitle("CASA LINDA - Código Inválido !");

				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/codigoInvalido.fxml"));
				Parent root = fxmlLoader.load();
				Scene scene = new Scene(root);

				scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
				window.setScene(scene);
				
				window.centerOnScreen();
				window.show();
				return;
			}
		} catch (Exception e) {

		}
    }

	@FXML
    void cancelaBuscaCod(ActionEvent event) {
		txtCod.setText("");
		listarProdutosEstoque();
    }

	@FXML
    void realizaVenda(ActionEvent event) throws IOException {

		if(eNumero(txtDia.getText()) == false){
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow(); 
          	window.setTitle("CASA LINDA - Venda Negada");
  
          	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/vendaNegada.fxml"));
          	Parent root = fxmlLoader.load();
          	Scene scene = new Scene(root);
  
          	scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
          	window.setScene(scene);
          
          	window.centerOnScreen();
          	window.show();
			return;
		} else if(eNumero(txtMes.getText()) == false){
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow(); 
          	window.setTitle("CASA LINDA - Venda Negada");
  
          	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/vendaNegada.fxml"));
          	Parent root = fxmlLoader.load();
          	Scene scene = new Scene(root);
  
          	scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
          	window.setScene(scene);
          
          	window.centerOnScreen();
          	window.show();
			return;
		} else if(eNumero(txtAno.getText()) == false){
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow(); 
          	window.setTitle("CASA LINDA - Venda Negada");
  
          	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/vendaNegada.fxml"));
          	Parent root = fxmlLoader.load();
          	Scene scene = new Scene(root);
  
          	scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
          	window.setScene(scene);
          
          	window.centerOnScreen();
          	window.show();
			return;
		}

		String pagamento = "";

		try {
			int qntdDisponivel = qntdProdVenda; // quantidade disponível do produto
			int qntdPedida = Integer.parseInt(txtQuantidade.getText()); // quantidade digitada pelo usuário
			int qntdAtual = qntdDisponivel - qntdPedida; // quantidade de produtos que restarão em estoque

			if(qntdAtual < 0){
				/* Venda Negada por Quantidade Insuficiente */
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow(); 
          		window.setTitle("CASA LINDA - Quantidade Insuficiente");
  
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/quantidadeInsuficiente.fxml"));
				Parent root = fxmlLoader.load();
				Scene scene = new Scene(root);
	
				scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
				window.setScene(scene);
			
				window.centerOnScreen();
				window.show();
				return;
			} else if(cbPagamento.getValue().isEmpty()){
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow(); 
				window.setTitle("CASA LINDA - Venda Negada");
	
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/vendaNegada.fxml"));
				Parent root = fxmlLoader.load();
				Scene scene = new Scene(root);
	
				scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
				window.setScene(scene);
			
				window.centerOnScreen();
				window.show();
				return;
			} else {
				/* Venda Permitida, quantidade suficiente de produdos */
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow(); 
          		window.setTitle("CASA LINDA - Confirmação de Venda");
  
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/confirmacaoVenda.fxml"));
				Parent root = fxmlLoader.load();
				Scene scene = new Scene(root);
	
				scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
				window.setScene(scene);
			
				window.centerOnScreen();
				window.show();

				// realiza a venda (adiciona produto como 'vendido' em MOVIMENTACAO)
				MovimentacaoDao daoMov = new MovimentacaoDao();
				ProdutoDao prod = new ProdutoDao();
				
				ResultSet rsdaoProd = prod.procuraProduto(nomeProdVenda); // Dados do Produto que será vendido


				if(cbPagamento.getValue() == "cartão de crédito"){
					pagamento = "credito";
				} else if(cbPagamento.getValue() == "cartão de débito"){
					pagamento = "debito";
				} else if(cbPagamento.getValue() == "pix"){
					pagamento = "pix";
				}
				
				if(rsdaoProd.next()){
					daoMov.adicionaMovimentacao(codProdVenda, nomeProdVenda, qntdPedida, "vendido", txtDia.getText(), txtMes.getText(), txtAno.getText(), pagamento,"+"+Float.toString(rsdaoProd.getFloat("preco_venda")));
				}
				

				// código do produto para fazer a busca no estoque adiquirir (nome, preco)
				EstoqueDao daoEstq = new EstoqueDao();
				
				if(qntdAtual == 0){
					// excluir produto em questão do estoque
					daoEstq.excluiProdutoEstoque(codProdVenda);
				} else {
					// atualizar quantidade do produto em questão do estoque
					daoEstq.atualizaQuantidadeEstoque(qntdAtual, codProdVenda);
				}
			}
		} catch (Exception e) {
			/* Venda Negada por falta de dados */
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow(); 
          	window.setTitle("CASA LINDA - Venda Negada");
  
          	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/vendaNegada.fxml"));
          	Parent root = fxmlLoader.load();
          	Scene scene = new Scene(root);
  
          	scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
          	window.setScene(scene);
          
          	window.centerOnScreen();
          	window.show();
			return;
		}
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		listarProdutosEstoque();

		cbPagamento.setItems(MetodoPagamento);;
	}

	boolean eNumero(String texto){

		boolean eNumero = true;
		for (int i = 0; i < texto.length(); i++) { //  percorre todo código digitado verificando se não há letras
		  if (!Character.isDigit(texto.charAt(i))) {
			eNumero = false;
		  }
		}
  
		return eNumero;
	  }
	
}