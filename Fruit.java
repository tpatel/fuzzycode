package fuzzycode;

import java.util.List;
import game.Api;

public class Fruit {
	protected Integer x;
	protected Integer y;
	protected Integer hp;
	protected Integer maxHp; // Points de vie maximum
	protected Integer defense;
	protected Integer maxDefense; // Points de défense max
	protected Integer speedMax;
	protected Integer attack;
	protected Integer range;
	protected Integer id;
	protected Integer type;
	private List<Equipment> equipments;
	protected Integer pa; // Points d'actions
	protected Integer fruitType;
	protected Integer curVitamins;
	protected Integer maxCurVitamins;
	protected Integer sugar;
	protected Integer maxWeight;
	protected Integer maxSugar;
	protected Boolean friend;

	public Integer getMaxSugar() {
		return maxSugar;
	}

	public void setMaxSugar(Integer maxSugar) {
		this.maxSugar = maxSugar;
	}

	public Integer getSugar() {
		return sugar;
	}

	public void setSugar(Integer sugar) {
		this.sugar = Math.max(0, Math.min(sugar, this.maxSugar));
	}

	public Boolean isFriend() {
		return friend;
	}

	public void setFriend(Boolean isAmi) {
		this.friend = isAmi;
	}

	public Fruit(Integer type) {
		if (type == Api.FRUIT_CHERRY) {
			this.attack = 4;
			this.defense = 0;
			this.hp = 15;
			this.maxDefense = 0;
			this.maxHp = 15;
			this.maxSugar = 90;
			this.range = 2;
			this.speedMax = 6;
			this.maxWeight = 45;
			this.maxCurVitamins = 10;
		} else {
			if (type == Api.FRUIT_KIWI) {
				this.attack = 2;
				this.defense = 1;
				this.hp = 20;
				this.maxDefense = 1;
				this.maxHp = 20;
				this.maxSugar = 120;
				this.range = 5;
				this.speedMax = 4;
				this.maxWeight = 55;
				this.maxCurVitamins = 15;
			} else {
				if (type == Api.FRUIT_NUT) {
					this.attack = 3;
					this.defense = 2;
					this.hp = 25;
					this.maxDefense = 2;
					this.maxHp = 25;
					this.maxSugar = 60;
					this.range = 1;
					this.speedMax = 2;
					this.maxWeight = 65;
					this.maxCurVitamins = 20;
				}
			}
		}

		this.x = -1;
		this.y = -1;
		this.friend = false;
		this.pa = 2;
		this.sugar = 0;
		this.curVitamins = 0;
		this.setType(type);
	}

	public Integer getCurVitamins() {
		return curVitamins;
	}

	public void setCurVitamins(Integer curVitamins) {
		this.curVitamins = Math.max(0, Math.min(curVitamins, this.maxCurVitamins));
	}

	public Integer getFruitType() {
		return fruitType;
	}

	public void setFruitType(Integer fruitType) {
		this.fruitType = fruitType;
	}

	public Integer getMaxDefense() {
		return maxDefense;
	}

	public void setMaxDefence(Integer maxDefence) {
		this.maxDefense = maxDefence;
	}

	public Integer getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(Integer maxHp) {
		this.maxHp = maxHp;
	}

	public Integer getPa() {
		return pa;
	}

	public void setPa(Integer pa) {
		this.pa = pa;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = Math.min(hp, this.maxHp);
	}

	public Integer getDefense() {
		return defense;
	}

	public void setDefense(Integer defense) {
		this.defense = Math.max(0, Math.min(defense, this.maxDefense));
	}

	public Integer getSpeed() {
		if (this.type == Api.FRUIT_CHERRY) {
			if (this.getWeight() >= 0 && this.getWeight() <= 20)
				return 6;
			if (this.getWeight() >= 21 && this.getWeight() <= 30)
				return 5;
			if (this.getWeight() >= 31 && this.getWeight() <= 35)
				return 4;
			if (this.getWeight() >= 36 && this.getWeight() <= 36)
				return 3;
			if (this.getWeight() >= 40 && this.getWeight() <= 42)
				return 2;
			if (this.getWeight() >= 43 && this.getWeight() <= 45)
				return 1;
		}
		if (this.type == Api.FRUIT_KIWI) {
			if (this.getWeight() >= 0 && this.getWeight() <= 35)
				return 4;
			if (this.getWeight() >= 36 && this.getWeight() <= 45)
				return 3;
			if (this.getWeight() >= 46 && this.getWeight() <= 51)
				return 2;
			if (this.getWeight() >= 52 && this.getWeight() <= 55)
				return 1;
		}
		if (this.type == Api.FRUIT_NUT) {
			if (this.getWeight() >= 0 && this.getWeight() <= 42)
				return 2;
			if (this.getWeight() >= 43 && this.getWeight() <= 65)
				return 1;
		}
		return 0;
	}

	public Double getWeight() {
		Double w = 0.0;
		for (Equipment e : this.getEquipments())
			w += e.getWeight();
		return w;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;

		this.maxDefense = (this.type == Api.FRUIT_CHERRY ? 0
				: (this.type == Api.FRUIT_KIWI ? 1 : 2));
		this.maxHp = (this.type == Api.FRUIT_CHERRY ? 15
				: (this.type == Api.FRUIT_KIWI ? 20 : 25));
	}

	public Integer getAttack() {
		return attack;
	}

	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// Range de base ou range max en comptant les objets
	public Integer getRange() {
		Integer r = this.getRangeBase();
		for (Equipment e : this.getEquipments())
			if (e.getRange() > r)
				r = e.getRange();
		return r;
	}

	public Integer getRangeBase() {
		return this.range;
	}

	public List<Equipment> getEquipments() {
		return this.equipments;
	}

	public Integer getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(Integer maxWeight) {
		this.maxWeight = maxWeight;
	}

	public Boolean addEquipment(Equipment equip) {
		if (equip.getWeight() + getWeight() > getMaxWeight())
			return false;

		this.equipments.add(equip);
		return true;
	}

	public void removeEquipment(Equipment equip) {
		this.equipments.remove(equip);
	}
}
