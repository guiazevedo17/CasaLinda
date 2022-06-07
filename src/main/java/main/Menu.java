package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Menu extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("CASA LINDA - Controle de Estoque");
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/menu.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
		stage.setScene(scene);
		stage.setMaximized(false);
		stage.setResizable(false);

		stage.centerOnScreen();
		stage.show();
	}

	public static void main(String[] args){
		launch(args);
	}
}