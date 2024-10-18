package multiplay;

public class Giocatori {
	private int punti;
	private Double puntata;
	private int nGiocatore;

	/**
	 * @param punti
	 * @param i
	 * @param nGiocatore
	 */
	public Giocatori(int punti, Double i, int nGiocatore) {
		this.punti = punti;
		this.puntata = i;
		this.nGiocatore = nGiocatore;
	}

	/**
	 * @return the punti
	 */
	public int getPunti() {
		return punti;
	}

	/**
	 * @param punti the punti to set
	 */
	public void setPunti(int punti) {
		this.punti = punti;
	}

	/**
	 * @return the puntata
	 */
	public Double getPuntata() {
		return puntata;
	}

	/**
	 * @param puntata the puntata to set
	 */
	public void setPuntata(Double puntata) {
		this.puntata = puntata;
	}

	/**
	 * @return the nGiocatore
	 */
	public int getnGiocatore() {
		return nGiocatore;
	}

	/**
	 * @param nGiocatore the nGiocatore to set
	 */
	public void setnGiocatore(int nGiocatore) {
		this.nGiocatore = nGiocatore;
	}

}
