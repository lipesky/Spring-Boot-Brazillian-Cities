package com.example.evoluum1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country{
	
    private int id;
    private String nome;
    private Microrregiao microrregiao;
    @JsonProperty("regiao-imediata") 
    private RegiaoImediata regiaoImediata;
    
    public Country() {}
    
	/**
	 * @param id
	 * @param nome
	 * @param microrregiao
	 * @param regiaoImediata
	 */
	public Country(int id, String nome, Microrregiao microrregiao, RegiaoImediata regiaoImediata) {
		this.id = id;
		this.nome = nome;
		this.microrregiao = microrregiao;
		this.regiaoImediata = regiaoImediata;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the microrregiao
	 */
	public Microrregiao getMicrorregiao() {
		return microrregiao;
	}
	/**
	 * @param microrregiao the microrregiao to set
	 */
	public void setMicrorregiao(Microrregiao microrregiao) {
		this.microrregiao = microrregiao;
	}
	/**
	 * @return the regiaoImediata
	 */
	public RegiaoImediata getRegiaoImediata() {
		return regiaoImediata;
	}
	/**
	 * @param regiaoImediata the regiaoImediata to set
	 */
	public void setRegiaoImediata(RegiaoImediata regiaoImediata) {
		this.regiaoImediata = regiaoImediata;
	}
    
    
}