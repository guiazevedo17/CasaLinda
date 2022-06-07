package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import connection.EstoqueDao;
import connection.MovimentacaoDao;
import connection.ProdutoDao;
import entity.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarProdutoController {//implements Initializable {

    @FXML // Botão para retornar a tela de Menu
    private Button btnRetorna, btnCadastrar;

    @FXML // Campos de Inserção de Dados
    private TextField txtDia, txtMes, txtAno, txtNome, txtTipo,txtFornecedor, txtQuantidade, txtPrecoCusto, txtPrecoVenda;

    @FXML // Inserção de Descrição
    private TextArea txtaDescricao;

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
    void cadastraProduto(ActionEvent event) throws IOException, SQLException{
      Produto produto = new Produto();

      if(eNumero(txtDia.getText()) == false){
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("CASA LINDA - Cadastro Negado");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/cadastroNegado.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        window.setScene(scene);
        
        window.centerOnScreen();
        window.show();
        return;
      } else if(eNumero(txtMes.getText()) == false){
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("CASA LINDA - Cadastro Negado");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/cadastroNegado.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        window.setScene(scene);
        
        window.centerOnScreen();
        window.show();
        return;
      } else if(eNumero(txtAno.getText()) == false){
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("CASA LINDA - Cadastro Negado");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/cadastroNegado.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        window.setScene(scene);
        
        window.centerOnScreen();
        window.show();
        return;
      } else if(eNumero(txtQuantidade.getText()) == false){
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("CASA LINDA - Cadastro Negado");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/cadastroNegado.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        window.setScene(scene);
        
        window.centerOnScreen();
        window.show();
        return;
      } else if(eNumero(txtPrecoCusto.getText()) == false){
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("CASA LINDA - Cadastro Negado");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/cadastroNegado.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        window.setScene(scene);
        
        window.centerOnScreen();
        window.show();
        return;
      } else if(eNumero(txtPrecoVenda.getText()) == false){
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("CASA LINDA - Cadastro Negado");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/cadastroNegado.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        window.setScene(scene);
        
        window.centerOnScreen();
        window.show();
        return;
      }


      try {
      produto.setDia(Integer.parseInt(txtDia.getText())); // Dia
      produto.setMes(Integer.parseInt(txtMes.getText())); // Mes
      produto.setAno(Integer.parseInt(txtAno.getText())); // Ano

      produto.setNome(txtNome.getText()); // Nome
      produto.setTipo(txtTipo.getText()); // Tipo
      produto.setFornecedor(txtFornecedor.getText()); // Fornecedor
      produto.setDescricao(txtaDescricao.getText()); // Descricao
      produto.setQuantidade(Integer.parseInt(txtQuantidade.getText())); // Quantidade

      produto.setPreco_custo(Float.parseFloat(txtPrecoCusto.getText())); // Preço Custo
      produto.setPreco_venda(Float.parseFloat(txtPrecoVenda.getText())); // Preço Venda
      
      } catch (Exception e) {
        //TODO: handle exception
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("CASA LINDA - Cadastro Negado");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/cadastroNegado.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        window.setScene(scene);
        
        window.centerOnScreen();
        window.show();

        return;
      }

      ProdutoDao daoProd = new ProdutoDao();
      daoProd.adicionaProduto(produto); // Adiciona a tabela produto
      // Procura na tabela ESTOQUE se o produto já existe
      ResultSet rsdaoProd = daoProd.procuraProdutoEstoque(produto); // Retorna o produto em questão (caso tenha achado)

      try {
        
        if(rsdaoProd.next()){ // verifica se há produto igual ao digitado
          System.out.println("Produto já cadastrado !");

          Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
          window.setTitle("CASA LINDA - Produto Já Cadastrado");

          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/produtoJaCadastrado.fxml"));
          Parent root = fxmlLoader.load();
          Scene scene = new Scene(root);

          scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
          window.setScene(scene);
          
          window.centerOnScreen();
          window.show();
          return;
        } else { // caso não exista confirma o cadastro do produto

        }
        /* Printa tela de confirmação de cadastro */
          Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow(); 
          window.setTitle("CASA LINDA - Confirmação de Cadastro");
  
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/confirmacaoCadastro.fxml"));
          Parent root = fxmlLoader.load();
          Scene scene = new Scene(root);
  
          scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
          window.setScene(scene);
          
          window.centerOnScreen();
          window.show();

      } catch (Exception e) {
        
      }
      /* Produto que irá ser cadastrado em ESTOQUE */
      EstoqueDao daoEstq = new EstoqueDao();
      daoEstq.adicionaProdutoEstoque(produto); // adiciona Produto ao Estoque

      ResultSet rsdaoEstq = daoEstq.procuraProdutoEstoque(produto.getNome()); // Produto do Estoque que acabou de ser adicionado (Estoque)    
      MovimentacaoDao daoMov = new MovimentacaoDao();

      if(rsdaoEstq.next()){
        daoMov.adicionaMovimentacao(rsdaoEstq.getInt("cod_prod"), produto.getNome(), produto.getQuantidade(), "cadastrado", txtDia.getText(), txtMes.getText(), txtAno.getText(), "-", "-"+Float.toString(produto.getPreco_custo()));
      }
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