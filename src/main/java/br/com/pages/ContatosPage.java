package br.com.pages;

public class ContatosPage {
	
	private String email;
	private String telefone;
	private Integer distribuidora_id;
	private String id_externo;

	
	private String nome;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Integer getDistribuidora_id() {
		return distribuidora_id;
	}
	public void setDistribuidora_id(Integer distribuidora_id) {
		this.distribuidora_id = distribuidora_id;
	}
	public String getId_externo() {
		return id_externo;
	}
	public void setId_externo(String id_externo) {
		this.id_externo = id_externo;
	}
	
}
