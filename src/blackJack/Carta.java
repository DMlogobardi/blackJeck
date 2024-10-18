/**
 * 
 */
package blackJack;

/**
 * @author david
 *
 */
public class Carta {
	private int valore;
	private String fronte;
	private String retro;

	/**
	 * @param vlore
	 * @param fronte
	 * @param retro
	 */
	public Carta(int valore, String fronte, String retro) {
		this.valore = valore;
		this.fronte = fronte;
		this.retro = retro;
	}

	/**
	 * @return the valore
	 */
	public int getValore() {
		return valore;
	}

	/**
	 * @param vlore the valore to set
	 */
	public void setValore(int valore) {
		this.valore = valore;
	}

	/**
	 * @return the fronte
	 */
	public String getFronte() {
		return fronte;
	}

	/**
	 * @param fronte the fronte to set
	 */
	public void setFronte(String fronte) {
		this.fronte = fronte;
	}

	/**
	 * @return the retro
	 */
	public String getRetro() {
		return retro;
	}

	/**
	 * @param retro the retro to set
	 */
	public void setRetro(String retro) {
		this.retro = retro;
	}

}
