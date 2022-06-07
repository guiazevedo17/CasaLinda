package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RetornaRealizarVenda {

    @FXML
    private Button btnOk;

    @FXML
    void retornaRealizarVenda(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setTitle("CASA LINDA - Realizar Venda");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/realizarVenda.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
		window.setScene(scene);
		
		//window.setMaximized(false);
		//window.setResizable(false);
		window.centerOnScreen();
		window.show();

    }

}