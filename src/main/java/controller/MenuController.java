package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MenuController {

    @FXML // Botões do Menu
    private Button btnCadastrarProduto, btnChecarEstoque, btnGerarRelatorio, btnRealizarVenda;

	@FXML // Título
    private Text lblTitulo;

    @FXML // Muda para tela de Cadastrar Produto
    void cadastrarProdutoTela(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setTitle("CASA LINDA - Cadastrar Produto");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/cadastrarProduto.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
		window.setScene(scene);
		
		window.centerOnScreen();
		window.show();
    }

    @FXML // Muda para tela de Checar Estoque
    void checarEstoqueTela(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setTitle("CASA LINDA - Checar Estoque");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/checarEstoque.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
		window.setScene(scene);
		
		window.centerOnScreen();
		window.show();
    }

    @FXML // Muda para tela de Gerar Relatório
    void gerarRelatorioTela(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setTitle("CASA LINDA - Gerar Relatório");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/gerarRelatorio.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
		window.setScene(scene);
		
		window.centerOnScreen();
		window.show();
    }

    @FXML // Muda para tela de Realizar Venda
    void realizarVendaTela(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setTitle("CASA LINDA - Realizar Venda");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/realizarVenda.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
		window.setScene(scene);
		
		window.centerOnScreen();
		window.show();
    }

}
