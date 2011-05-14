package fuzzycode;

public class Equipment {
	protected Integer range;
	protected Integer ammo;
	protected Double weightBase;
	protected Double weightAmmo;
	protected Integer id;
	protected Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Equipment(int equipmentTeaSpoon) {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getRange() {
		return this.range;
	}
	
	public void setRange(Integer range) {
		this.range = range;
	}
	
	public Integer getAmmo() {
		return this.ammo;
	}
	
	public void setAmmo(Integer ammo) {
		this.ammo = ammo;
	}
	
	public Double getWeight() {
		return this.weightBase + this.weightAmmo * this.ammo;
	}
	
	public void setWeightBase(Double weight) {
		this.weightBase = weight;
	}
	
	public void setWeightAmmo(Double weight) {
		this.weightAmmo = weight;
	}
}
