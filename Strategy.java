package fuzzycode;

/**
 * Interface des stagtegies.
 */
public abstract class Strategy {
	/**
	 * Appelé a l'activation de la strategie.
	 */
	public abstract void start();
	/**
	 * Appelé a la desactivation de la strategie.
	 */
	public abstract void stop();
	/**
	 * Appelé a chaque frame si la strategie est active.
	 */
    public abstract void run();
    /**
     * Pertinence de la strategie.
     */
    public abstract Double adequacy();    
}
