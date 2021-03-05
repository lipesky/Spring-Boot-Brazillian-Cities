package com.example.evoluum1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReportRecord {
	
	private int idEstado;
	private String siglaEstado;
	private String regiaoNome;
	private String nomeCidade;
	private String nomeMesorregiao;
	private int idCidade;
	
	/**
	 * @param idEstado
	 * @param siglaEstado
	 * @param regiaoNome
	 * @param nomeCidade
	 * @param nomeMesorregiao
	 * @param idCidade
	 */
	public ReportRecord(int idEstado, String siglaEstado, String regiaoNome, String nomeCidade, String nomeMesorregiao, int idCidade) {
		this.idEstado = idEstado;
		this.siglaEstado = siglaEstado;
		this.regiaoNome = regiaoNome;
		this.nomeCidade = nomeCidade;
		this.nomeMesorregiao = nomeMesorregiao;
		this.idCidade = idCidade;
	}
	
	/**
	 * @param city
	 */
	public ReportRecord(City city) {
		
		final int idEstado = city.getMicrorregiao().getMesorregiao().getUf().getId();
		final String siglaEstado = city.getMicrorregiao().getMesorregiao().getUf().getSigla();
		final String regiaoNome = city.getMicrorregiao().getMesorregiao().getUf().getRegiao().getNome();
		final String nomeCidade = city.getNome();
		final String nomeMesorregiao = city.getMicrorregiao().getMesorregiao().getNome();
		final int idCidade = city.getId();
		
		this.idEstado = idEstado;
		this.siglaEstado = siglaEstado;
		this.regiaoNome = regiaoNome;
		this.nomeCidade = nomeCidade;
		this.nomeMesorregiao = nomeMesorregiao;
		this.idCidade = idCidade;
	}

	/**
	 * @return the idEstado
	 */
	public int getIdEstado() {
		return idEstado;
	}

	/**
	 * @param idEstado the idEstado to set
	 */
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	/**
	 * @return the siglaEstado
	 */
	public String getSiglaEstado() {
		return siglaEstado;
	}

	/**
	 * @param siglaEstado the siglaEstado to set
	 */
	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}

	/**
	 * @return the regiaoNome
	 */
	public String getRegiaoNome() {
		return regiaoNome;
	}

	/**
	 * @param regiaoNome the regiaoNome to set
	 */
	public void setRegiaoNome(String regiaoNome) {
		this.regiaoNome = regiaoNome;
	}

	/**
	 * @return the nomeCidade
	 */
	public String getNomeCidade() {
		return nomeCidade;
	}

	/**
	 * @param nomeCidade the nomeCidade to set
	 */
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	/**
	 * @return the nomeMesorregiao
	 */
	public String getNomeMesorregiao() {
		return nomeMesorregiao;
	}

	/**
	 * @param nomeMesorregiao the nomeMesorregiao to set
	 */
	public void setNomeMesorregiao(String nomeMesorregiao) {
		this.nomeMesorregiao = nomeMesorregiao;
	}
	
	/**
	 * @return the idCidade
	 */
	public int getIdCidade() {
		return idCidade;
	}

	/**
	 * @param idCidade the idCidade to set
	 */
	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}

	/**
	 * @return the nomeFormatado
	 */
	public String getNomeFormatado() {
		return String.format("%s/%s", this.getNomeCidade(), this.getSiglaEstado());
	}


	@Override
	public String toString() {
		
		return String.format(
				"ReportRecordDTO [idEstado=%s, siglaEstado=%s, regiaoNome=%s, nomeCidade=%s, nomeMesorregiao=%s]",
				idEstado, siglaEstado, regiaoNome, nomeCidade, nomeMesorregiao);
	}
	
	public String toCSV() {		
		return String.format(
				"%s,%s,%s,%s,%s,%s\n",
				idEstado, siglaEstado, regiaoNome, nomeCidade, nomeMesorregiao, getNomeFormatado());
	}
	
	@JsonIgnore
	public String getCSVSchema() {		
		return getCSVSchema(true);
	}
	
	public String getCSVSchema(boolean showSeparator) {		
		return String.format(
				(showSeparator? "sep=,\n" : "") +"idEstado,siglaEstado,regiaoNome,nomeCidade,nomeMesorregiao,nomeFormatado\n",
				idEstado, siglaEstado, regiaoNome, nomeCidade, nomeMesorregiao, getNomeFormatado());
	}

}
