package br.com.pages;

public class AlarmesPage {
	
	private String codigo;
	private Integer limite;
	private String initialDate;
	private String endDate;
	private String nome;
	private Integer propriedade_id;
	private Integer operacao_logica_id;
	private String valor_comparacao_1;
	private String valor_comparacao_2;
	private Integer operacao_logica_retorno_id;
	private String valor_retorno_1;
	private String valor_retorno_2;
	private String descricao;
	private int[] dispositivos;
	private int[] dispositivos_tipos;
	private String status;
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPropriedade_id() {
		return propriedade_id;
	}
	public void setPropriedade_id(Integer propriedade_id) {
		this.propriedade_id = propriedade_id;
	}
	public Integer getOperacao_logica_id() {
		return operacao_logica_id;
	}
	public void setOperacao_logica_id(Integer operacao_logica_id) {
		this.operacao_logica_id = operacao_logica_id;
	}
	public String getValor_comparacao_1() {
		return valor_comparacao_1;
	}
	public void setValor_comparacao_1(String valor_comparacao_1) {
		this.valor_comparacao_1 = valor_comparacao_1;
	}
	public String getValor_comparacao_2() {
		return valor_comparacao_2;
	}
	public void setValor_comparacao_2(String valor_comparacao_2) {
		this.valor_comparacao_2 = valor_comparacao_2;
	}
	public Integer getOperacao_logica_retorno_id() {
		return operacao_logica_retorno_id;
	}
	public void setOperacao_logica_retorno_id(Integer operacao_logica_retorno_id) {
		this.operacao_logica_retorno_id = operacao_logica_retorno_id;
	}
	public String getValor_retorno_1() {
		return valor_retorno_1;
	}
	public void setValor_retorno_1(String valor_retorno_1) {
		this.valor_retorno_1 = valor_retorno_1;
	}
	public String getValor_retorno_2() {
		return valor_retorno_2;
	}
	public void setValor_retorno_2(String valor_retorno_2) {
		this.valor_retorno_2 = valor_retorno_2;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int[] getDispositivos() {
		return dispositivos;
	}
	public void setDispositivos(int[] dispositivos) {
		this.dispositivos = dispositivos;
	}
	public int[] getDispositivos_tipos() {
		return dispositivos_tipos;
	}
	public void setDispositivos_tipos(int[] dispositivos_tipos) {
		this.dispositivos_tipos = dispositivos_tipos;
	}
	
	
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Integer getLimite() {
		return limite;
	}
	public void setLimite(Integer limite) {
		this.limite = limite;
	}
	public String getInitialDate() {
		return initialDate;
	}
	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	

}
