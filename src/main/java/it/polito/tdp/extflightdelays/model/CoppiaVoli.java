package it.polito.tdp.extflightdelays.model;

/**
 * Classe utilizzata nel DAO per costruire nel modo corretto il grafo (intendevo coppia di aeroporti, non voli)
 * @author eugenioprincipi
 *
 */
public class CoppiaVoli {

	private Integer volo1;
	private Integer volo2;
	private Integer peso;
	
	/**
	 * @param volo1
	 * @param volo2
	 * @param peso
	 */
	public CoppiaVoli(Integer volo1, Integer volo2, Integer peso) {
		super();
		this.volo1 = volo1;
		this.volo2 = volo2;
		this.peso = peso;
	}

	public Integer getVolo1() {
		return volo1;
	}

	public void setVolo1(Integer volo1) {
		this.volo1 = volo1;
	}

	public Integer getVolo2() {
		return volo2;
	}

	public void setVolo2(Integer volo2) {
		this.volo2 = volo2;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
}
