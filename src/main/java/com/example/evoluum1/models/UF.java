package com.example.evoluum1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UF{
	
	private int id;
    private String sigla;
    private String nome;
    private Country regiao;
    
    
    public UF() {}
    
	/**
	 * @param id
	 * @param sigla
	 * @param nome
	 * @param regiao
	 */
	public UF(int id, String sigla, String nome, Country regiao) {
		this.id = id;
		this.sigla = sigla;
		this.nome = nome;
		this.regiao = regiao;
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
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}


	/**
	 * @param sigla the sigla to set
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
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
	 * @return the regiao
	 */
	public Country getRegiao() {
		return regiao;
	}


	/**
	 * @param regiao the regiao to set
	 */
	public void setRegiao(Country regiao) {
		this.regiao = regiao;
	}
	
	@Override
	public String toString() {
		return String.format("{ \"id\"=%s, \"sigla\"=\"%s\", \"nome\"=\"%s\", \"regiao\"=\"%s\" }", id, sigla, nome, regiao);
	}
		
}
