package tables;

public class TabelaEstoque {
	private int cod_prod;
	private String nome;
	private String tipo;
	private int quantidade;
	private float preco;

	public TabelaEstoque(int cod_prod, String nome, String tipo, int quantidade, float preco){
		this.cod_prod = cod_prod;
		this.nome = nome;
		this.tipo = tipo;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public int getCod_prod(){
		return cod_prod;
	}

	public String getNome(){
		return nome;
	}

	public String getTipo(){
		return tipo;
	}

	public int getQuantidade(){
		return quantidade;
	}

	public float getPreco(){
		return preco;
	}
}
