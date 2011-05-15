package fuzzycode;

public class SugarDrop {
	private Integer nbrElement;
	private Integer id;
	
	public void setNbrElement(Integer nbrElement) {
		this.nbrElement = Math.max(0, nbrElement);
	}

	public Integer getNbrElement() {
		return this.nbrElement;
	}
	
	public Integer getId() {
		return this.id;
	}

	public SugarDrop() {
		this.nbrElement = 0;
	}
	
	
}

