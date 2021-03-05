package com.example.evoluum1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegiaoImediata{	
	
    private int id;
    private String nome;
    @JsonProperty("regiao-intermediaria") 
    private RegiaoIntermediaria regiaoIntermediaria;
    
    
    public RegiaoImediata() {}
	/**
	 * @param id
	 * @param nome
	 * @param regiaoIntermediaria
	 */
	public RegiaoImediata(int id, String nome, RegiaoIntermediaria regiaoIntermediaria) {
		super();
		this.id = id;
		this.nome = nome;
		this.regiaoIntermediaria = regiaoIntermediaria;
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
	 * @return the regiaoIntermediaria
	 */
	public RegiaoIntermediaria getRegiaoIntermediaria() {
		return regiaoIntermediaria;
	}
	/**
	 * @param regiaoIntermediaria the regiaoIntermediaria to set
	 */
	public void setRegiaoIntermediaria(RegiaoIntermediaria regiaoIntermediaria) {
		this.regiaoIntermediaria = regiaoIntermediaria;
	}
    
    
}