package com.example.evoluum1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Mesorregiao{
	
    private int id;
    private String nome;
    @JsonProperty("UF") 
    private UF uf;
    
    
    public Mesorregiao() {}
	/**
	 * @param id
	 * @param nome
	 * @param uf
	 */
	public Mesorregiao(int id, String nome, UF uf) {
		this.id = id;
		this.nome = nome;
		this.uf = uf;
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
	 * @return the uf
	 */
	public UF getUf() {
		return uf;
	}

	/**
	 * @param uf the uf to set
	 */
	public void setUf(UF uf) {
		this.uf = uf;
	}
    
	
    
}