package tables;

public class TabelaRelatorio {
	private int cod_prod;
	private String nome;
	private int quantidade;
	private String status;
	private int dia;
	private int mes;
	private int ano;
	private String forma_pagamento;
	private float valor;

	public TabelaRelatorio(int cod_prod, String nome, int quantidade, String status, int dia, int mes, int ano, String forma_pagamento, float valor){
		this.cod_prod = cod_prod;
		this.nome = nome;
		this.quantidade = quantidade;
		this.status = status;
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.forma_pagamento = forma_pagamento;
		this.valor = valor;
	}

	// codigo
	public int getCod_prod() {
		return cod_prod;
	}
	public void setCod_prod(int cod_prod) {
		this.cod_prod = cod_prod;
	}
	
	// nome
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	// quantidade
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	// status
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	// forma_pagamento
	public String getForma_pagamento() {
		return forma_pagamento;
	}
	public void setForma_pagamento(String forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}
	
	// valor
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
}
