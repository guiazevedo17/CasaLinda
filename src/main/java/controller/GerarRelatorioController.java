package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import connection.MovimentacaoDao;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tables.TabelaRelatorio;

public class GerarRelatorioController implements Initializable {
	
	@FXML // botão de retornar para o menu
    private Button btnRetorna;

	@FXML // Tipo de Relatorio (Mensal, Anual) / e Opções (Meses cadastrados, Anos cadastrados)
    private ComboBox<String> cbTipo, cbOpcoes;

	private ObservableList<String> tipoRelatorio = FXCollections.observableArrayList("Mensal","Anual");

	@FXML // tabela do relatório (movimentacao)
    private TableView<TabelaRelatorio> tbRelatorio;

    @FXML // colunas da tabela do relatorio (movimentacao)
    private TableColumn<TabelaRelatorio, Integer> tbclmCod, tbclmQuantia, tbclmDia, tbclmMes, tbclmAno;
	 
	@FXML // colunas da tabela do relatorio (movimentacao)
    private TableColumn<TabelaRelatorio, String> tbclmNome, tbclmStatus, tbclmPagamento;
	
	@FXML // colunas da tabela do relatorio (movimentacao)
    private TableColumn<TabelaRelatorio, Float> tbclmValor;

	public void listarMovimentacoes() {
		tbRelatorio.getItems().clear();

		tbclmCod.setCellValueFactory(new PropertyValueFactory("cod_prod"));
		tbclmNome.setCellValueFactory(new PropertyValueFactory("nome"));
		tbclmQuantia.setCellValueFactory(new PropertyValueFactory("quantidade"));
		tbclmStatus.setCellValueFactory(new PropertyValueFactory("status"));
		tbclmDia.setCellValueFactory(new PropertyValueFactory("dia"));
		tbclmMes.setCellValueFactory(new PropertyValueFactory("mes"));
		tbclmAno.setCellValueFactory(new PropertyValueFactory("ano"));
		tbclmPagamento.setCellValueFactory(new PropertyValueFactory("forma_pagamento"));
		tbclmValor.setCellValueFactory(new PropertyValueFactory("valor"));

		MovimentacaoDao daoMov = new MovimentacaoDao();
		ResultSet rsdaoMov = daoMov.listMovimentacao();
		ObservableList<TabelaRelatorio> listTabelaRelatorio = FXCollections.observableArrayList();

		try {
			while(rsdaoMov.next()){
				TabelaRelatorio t = new TabelaRelatorio(rsdaoMov.getInt("cod_prod"), rsdaoMov.getString("nome"), rsdaoMov.getInt("quantidade"), rsdaoMov.getString("status"), rsdaoMov.getInt("dia"), rsdaoMov.getInt("mes"), rsdaoMov.getInt("ano"), rsdaoMov.getString("forma_pagamento"), rsdaoMov.getFloat("valor"));
				listTabelaRelatorio.add(t);
				tbRelatorio.setItems(listTabelaRelatorio);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listarMovimentacoes();
		
		cbTipo.setItems(tipoRelatorio);
	}

	@FXML
	public void selecionaTipoRelatorio(ActionEvent event){
		MovimentacaoDao daoMov = new MovimentacaoDao();
		ResultSet rsdaoMov = daoMov.listMovimentacao();

		try {

			if(cbTipo.getValue() == "Mensal"){
				cbOpcoes.getItems().clear();
				listarMovimentacoes();	
				ObservableList<String> jaListado = FXCollections.observableArrayList("");
				
				while(rsdaoMov.next()){				
					boolean listado = false;

					for(int i = 0; i < jaListado.size() ;i++){
						
						if(Integer.toString(rsdaoMov.getInt("mes")).equals(jaListado.get(i))){
							listado = true;
						} 
					}

					if(listado == false){
						cbOpcoes.getItems().add(Integer.toString(rsdaoMov.getInt("mes")));
						jaListado.add(Integer.toString(rsdaoMov.getInt("mes")));
					}
				}


			} else if(cbTipo.getValue() == "Anual") {
				cbOpcoes.getItems().clear();
				listarMovimentacoes();
				ObservableList<String> jaListado = FXCollections.observableArrayList("");

				while(rsdaoMov.next()){					
					boolean listado = false;

					for(int i = 0; i < jaListado.size() ;i++){
						
						if(Integer.toString(rsdaoMov.getInt("ano")).equals(jaListado.get(i))){
							listado = true;
						} 
					}

					if(listado == false){
						cbOpcoes.getItems().add(Integer.toString(rsdaoMov.getInt("ano")));
						jaListado.add(Integer.toString(rsdaoMov.getInt("ano")));
					}
					
				}
				

			}
		} catch (Exception e) {
			//TODO: handle exception
		}
	}

	@FXML
    void geraRelatorio(ActionEvent event) {
		MovimentacaoDao daoMov = new MovimentacaoDao();
		ObservableList<TabelaRelatorio> listTabelaRelatorio = FXCollections.observableArrayList();

		tbRelatorio.getItems().clear();

		tbclmCod.setCellValueFactory(new PropertyValueFactory("cod_prod"));
		tbclmNome.setCellValueFactory(new PropertyValueFactory("nome"));
		tbclmQuantia.setCellValueFactory(new PropertyValueFactory("quantidade"));
		tbclmStatus.setCellValueFactory(new PropertyValueFactory("status"));
		tbclmDia.setCellValueFactory(new PropertyValueFactory("dia"));
		tbclmMes.setCellValueFactory(new PropertyValueFactory("mes"));
		tbclmAno.setCellValueFactory(new PropertyValueFactory("ano"));
		tbclmPagamento.setCellValueFactory(new PropertyValueFactory("forma_pagamento"));
		tbclmValor.setCellValueFactory(new PropertyValueFactory("valor"));

		if(cbTipo.getValue() == "Mensal"){

			ResultSet rsdaoMov = daoMov.listMovimentacaoMes(cbOpcoes.getValue());

			try {
				while(rsdaoMov.next()){
					TabelaRelatorio t = new TabelaRelatorio(rsdaoMov.getInt("cod_prod"), rsdaoMov.getString("nome"), rsdaoMov.getInt("quantidade"), rsdaoMov.getString("status"), rsdaoMov.getInt("dia"), rsdaoMov.getInt("mes"), rsdaoMov.getInt("ano"), rsdaoMov.getString("forma_pagamento"), rsdaoMov.getFloat("valor"));
					listTabelaRelatorio.add(t);
					tbRelatorio.setItems(listTabelaRelatorio);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			

		} else if(cbTipo.getValue() == "Anual"){

			ResultSet rsdaoMov = daoMov.listMovimentacaoAno(cbOpcoes.getValue());
			
			try {
				while(rsdaoMov.next()){
					TabelaRelatorio t = new TabelaRelatorio(rsdaoMov.getInt("cod_prod"), rsdaoMov.getString("nome"), rsdaoMov.getInt("quantidade"), rsdaoMov.getString("status"), rsdaoMov.getInt("dia"), rsdaoMov.getInt("mes"), rsdaoMov.getInt("ano"), rsdaoMov.getString("forma_pagamento"), rsdaoMov.getFloat("valor"));
					listTabelaRelatorio.add(t);
					tbRelatorio.setItems(listTabelaRelatorio);
				}
			} catch (Exception e) {
				System.out.println(e);
			}

		}
    }

}