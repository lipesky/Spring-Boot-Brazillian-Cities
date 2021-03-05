package com.example.evoluum1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Microrregiao{
    private int id;
    private String nome;
    private Mesorregiao mesorregiao;
    
    
    public Microrregiao() {}
	/**
	 * @param id
	 * @param nome
	 * @param mesorregiao
	 */
	public Microrregiao(int id, String nome, Mesorregiao mesorregiao) {
		super();
		this.id = id;
		this.nome = nome;
		this.mesorregiao = mesorregiao;
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
	 * @return the mesorregiao
	 */
	public Mesorregiao getMesorregiao() {
		return mesorregiao;
	}

	/**
	 * @param mesorregiao the mesorregiao to set
	 */
	public void setMesorregiao(Mesorregiao mesorregiao) {
		this.mesorregiao = mesorregiao;
	}
    
    
}