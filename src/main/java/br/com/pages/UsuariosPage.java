package br.com.pages;

public class UsuariosPage {
	
	private String name;
	private String email;
	private String password;
	private String senha_atual;
	private String nova_senha;
	
	
	public String getSenha_atual() {
		return senha_atual;
	}
	public void setSenha_atual(String senha_atual) {
		this.senha_atual = senha_atual;
	}
	public String getNova_senha() {
		return nova_senha;
	}
	public void setNova_senha(String nova_senha) {
		this.nova_senha = nova_senha;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
