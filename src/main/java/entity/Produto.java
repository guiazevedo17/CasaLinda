package entity;

public class Produto {
	private int cod_prod;
	private int dia;
	private int mes;
	private int ano;
	private String nome;
	private String tipo;
	private String fornecedor;
	private String descricao;
	private int quantidade;
	private float preco_custo;
	private float preco_venda;

	public Produto(){

	}

	public Produto(int cod_prod, int dia, int mes, int ano, String nome, String tipo, String fornecedor, String descricao, int quantidade, float preco_custo, float preco_venda){
		super();

		this.cod_prod = cod_prod;
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.nome = nome;
		this.tipo = tipo;
		this.fornecedor = fornecedor;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco_custo = preco_custo;
		this.preco_venda = preco_venda;
	}

	// cod_prod
	public int getCod_prod() {
		return cod_prod;
	}

	public void setCod_prod(int cod_prod) {
		this.cod_prod = cod_prod;
	}

	// dia
	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	// mes
	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	// ano
	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	// nome
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	// tipo
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	// fornecedor
	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	// descricao
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	// quantidade
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	// preco_custo
	public float getPreco_custo() {
		return preco_custo;
	}

	public void setPreco_custo(float preco_custo) {
		this.preco_custo = preco_custo;
	}

	// preco_venda
	public float getPreco_venda() {
		return preco_venda;
	}

	public void setPreco_venda(float preco_venda) {
		this.preco_venda = preco_venda;
	}

	
}


