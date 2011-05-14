package fuzzycode;

import game.Api;

public class Equipment {
	protected Integer range;
	protected Integer ammo;
	protected Double weightBase;
	protected Double weightAmmo;
	protected Integer id;
	protected Integer type;
	protected Integer ammoMax;
	
	public Integer getAmmoMax() {
		return ammoMax;
	}

	public void setAmmoMax(Integer ammoMax) {
		this.ammoMax = ammoMax;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
		
		if(this.type == Api.EQUIPMENT_CUTTER) {
			this.range = 2;
			this.ammoMax = Integer.MAX_VALUE;
			this.weightBase = 15.0;
			this.weightAmmo = 0.0;
		}
		
		if(this.type == Api.EQUIPMENT_JUICE_NEEDLE) {
			this.range = 1;
			this.ammoMax = 10;
			this.weightBase = 2.0;
			this.weightAmmo = 1.0;
		}
		
		if(this.type == Api.EQUIPMENT_LEMONER) {
			this.range = 6;
			this.ammoMax = 25;
			this.weightBase = 10.0;
			this.weightAmmo = 0.4;
		}
		
		if(this.type == Api.EQUIPMENT_LIGHTER) {
			this.range = 5;
			this.ammoMax = 100;
			this.weightBase = 11.0;
			this.weightAmmo = 0.01;
		}
		
		if(this.type == Api.EQUIPMENT_PEELER) {
			this.range = 2;
			this.ammoMax = Integer.MAX_VALUE;
			this.weightBase = 5.0;
			this.weightAmmo = 0.0;
		}
		
		if(this.type == Api.EQUIPMENT_RELOADER) {
			this.range = 1;
			this.ammoMax = 1;
			this.weightBase = 2.0;
			this.weightAmmo = 10.0;
		}
		
		if(this.type == Api.EQUIPMENT_SALT_SNIPER) {
			this.range = 10;
			this.ammoMax = 10;
			this.weightBase = 20.0;
			this.weightAmmo = 1.0;
		}
		
		if(this.type == Api.EQUIPMENT_TEA_SPOON) {
			this.range = 1;
			this.ammoMax = Integer.MAX_VALUE;
			this.weightBase = 5.0;
			this.weightAmmo = 0.0;
		}
		
		if(this.type == Api.EQUIPMENT_TOOTHPICK) {
			this.range = 1;
			this.ammoMax = Integer.MAX_VALUE;
			this.weightBase = 10.0;
			this.weightAmmo = 0.0;
		}
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
	
	public Integer getAmmo() {
		return this.ammo;
	}
	
	public void setAmmo(Integer ammo) {
		this.ammo = ammo;
	}
	
	public Double getWeight() {
		return this.weightBase + this.weightAmmo * this.ammo;
	}
}
