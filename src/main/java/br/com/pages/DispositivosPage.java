package br.com.pages;

public class DispositivosPage {
	private String	codigo;
	
	private Integer tipo_id;
	private String descricao;
	private float constante_medidor;
	private float relacao_potencial;
	private float relacao_corrente;
	private Integer distribuidora_id;
	private Integer empresa_id;
	private Integer alerta_segundos;
	private String url_firmware;
	private Integer equipamento_id;
	
	
	
	public Integer getEquipamento_id() {
		return equipamento_id;
	}
	public void setEquipamento_id(Integer equipamento_id) {
		this.equipamento_id = equipamento_id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Integer getTipo_id() {
		return tipo_id;
	}
	public void setTipo_id(Integer tipo_id) {
		this.tipo_id = tipo_id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getConstante_medidor() {
		return constante_medidor;
	}
	public void setConstante_medidor(float constante_medidor) {
		this.constante_medidor = constante_medidor;
	}
	public float getRelacao_potencial() {
		return relacao_potencial;
	}
	public void setRelacao_potencial(float relacao_potencial) {
		this.relacao_potencial = relacao_potencial;
	}
	public float getRelacao_corrente() {
		return relacao_corrente;
	}
	public void setRelacao_corrente(float relacao_corrente) {
		this.relacao_corrente = relacao_corrente;
	}
	public Integer getDistribuidora_id() {
		return distribuidora_id;
	}
	public void setDistribuidora_id(Integer distribuidora_id) {
		this.distribuidora_id = distribuidora_id;
	}
	public Integer getEmpresa_id() {
		return empresa_id;
	}
	public void setEmpresa_id(Integer empresa_id) {
		this.empresa_id = empresa_id;
	}
	public Integer getAlerta_segundos() {
		return alerta_segundos;
	}
	public void setAlerta_segundos(Integer alerta_segundos) {
		this.alerta_segundos = alerta_segundos;
	}
	public String getUrl_firmware() {
		return url_firmware;
	}
	public void setUrl_firmware(String url_firmware) {
		this.url_firmware = url_firmware;
	}
	

}
