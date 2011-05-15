package fuzzycode;

import game.Api;

public class Equipment {
	protected Integer attack;
	protected Integer range;
	protected Integer ammo;
	protected Double weightBase;
	protected Double weightAmmo;
	protected Integer id;
	protected Integer type;
	protected Integer ammoMax;

	public Integer getAttack() {
		return attack;
	}

	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	public Integer getAmmoMax() {
		return this.ammoMax == -1 ? Integer.MAX_VALUE : this.ammoMax;
	}

	public void setAmmoMax(Integer ammoMax) {
		this.ammoMax = ammoMax;
	}

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

	public Integer getRange() {
		return this.range;
	}

	public Integer getAmmo() {
		return this.ammoMax == -1 ? Integer.MAX_VALUE : this.ammo;
	}

	public void setAmmo(Integer ammo) {
		if (ammoMax > 0)
			this.ammo = Math.max(0, Math.min(ammo, this.ammoMax));
	}

	public Double getWeight() {
		return this.weightBase + this.weightAmmo * this.ammo;
	}

	public Equipment(Integer type) {

		if (this.type == Api.EQUIPMENT_CUTTER) {
			this.attack = 8;
			this.range = 2;
			this.ammoMax = -1;
			this.weightBase = 15.0;
			this.weightAmmo = 0.0;
		}

		if (this.type == Api.EQUIPMENT_JUICE_NEEDLE) {
			this.attack = 4;
			this.range = 1;
			this.ammoMax = 10;
			this.weightBase = 2.0;
			this.weightAmmo = 1.0;
		}

		if (this.type == Api.EQUIPMENT_LEMONER) {
			this.attack = 4;
			this.range = 6;
			this.ammoMax = 25;
			this.weightBase = 10.0;
			this.weightAmmo = 0.4;
		}

		if (this.type == Api.EQUIPMENT_LIGHTER) {
			this.attack = 2;
			this.range = 5;
			this.ammoMax = 100;
			this.weightBase = 11.0;
			this.weightAmmo = 0.01;
		}

		if (this.type == Api.EQUIPMENT_PEELER) {
			this.attack = -1;
			this.range = 2;
			this.ammoMax = -1;
			this.weightBase = 5.0;
			this.weightAmmo = 0.0;
		}

		if (this.type == Api.EQUIPMENT_RELOADER) {
			this.attack = 100;
			this.range = 1;
			this.ammoMax = 1;
			this.weightBase = 2.0;
			this.weightAmmo = 10.0;
		}

		if (this.type == Api.EQUIPMENT_SALT_SNIPER) {
			this.attack = 6;
			this.range = 10;
			this.ammoMax = 10;
			this.weightBase = 20.0;
			this.weightAmmo = 1.0;
		}

		if (this.type == Api.EQUIPMENT_TEA_SPOON) {
			this.attack = 4;
			this.range = 1;
			this.ammoMax = -1;
			this.weightBase = 5.0;
			this.weightAmmo = 0.0;
		}

		if (this.type == Api.EQUIPMENT_TOOTHPICK) {
			this.attack = 6;
			this.range = 1;
			this.ammoMax = -1;
			this.weightBase = 10.0;
			this.weightAmmo = 0.0;
		}

		this.ammo = ammoMax;
		this.type = type;
	}
}
