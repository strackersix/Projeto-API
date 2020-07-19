package br.com.pages;

public class PropriedadePage {
	
	private String	tipo;
	private Integer dispositivo_id;
	private String calculo;
	private Integer tipo_dispositivo_id;
	private String nome;
	
	
	
	public Integer getTipo_dispositivo_id() {
		return tipo_dispositivo_id;
	}
	public void setTipo_dispositivo_id(Integer tipo_dispositivo_id) {
		this.tipo_dispositivo_id = tipo_dispositivo_id;
	}
	
    public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getDispositivo_id() {
		return dispositivo_id;
	}
	public void setDispositivo_id(Integer dispositivo_id) {
		this.dispositivo_id = dispositivo_id;
	}
	public String getCalculo() {
		return calculo;
	}
	public void setCalculo(String calculo) {
		this.calculo = calculo;
	}
	
	
	
	
	
}
