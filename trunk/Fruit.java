package fuzzycode;

import java.util.List;
import game.Api;

public class Fruit {
	protected Integer x;
	protected Integer y;
	protected Integer hp;
	protected Integer maxHp; //Points de vie maximum
	protected Integer defence;
	protected Integer maxDefence; //Points de défence max
	protected Integer speed;
	protected Integer attack;
	protected Integer range;
	protected Integer id;
	protected Integer type;
	private List<Equipment> equipments;
	protected Integer pa; //Points d'actions
	protected Integer fruitType;
	protected Integer curVitamins;

	public Fruit(Integer integer) {
		this.setType(type);
	}
	
	public Integer getCurVitamins() {
		return curVitamins;
	}

	public void setCurVitamins(Integer curVitamins) {
		this.curVitamins = curVitamins;
	}

	public Integer getFruitType() {
		return fruitType;
	}

	public void setFruitType(Integer fruitType) {
		this.fruitType = fruitType;
	}

	public Integer getMaxDefence() {
		return maxDefence;
	}

	public void setMaxDefence(Integer maxDefence) {
		this.maxDefence = maxDefence;
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
		this.hp = hp;
	}

	public Integer getDefence() {
		return defence;
	}

	public void setDefence(Integer defence) {
		this.defence = defence;
	}

	public Integer getSpeed() {
		if(this.type == Api.FRUIT_CHERRY) {
			if(this.getWeight() >= 0 && this.getWeight() <= 20)		return 6;
			if(this.getWeight() >= 21 && this.getWeight() <= 30)	return 5;
			if(this.getWeight() >= 31 && this.getWeight() <= 35)	return 4;
			if(this.getWeight() >= 36 && this.getWeight() <= 36)	return 3;
			if(this.getWeight() >= 40 && this.getWeight() <= 42)	return 2;
			if(this.getWeight() >= 43 && this.getWeight() <= 45)	return 1;
		}
		if(this.type == Api.FRUIT_KIWI) {
			if(this.getWeight() >= 0 && this.getWeight() <= 35)		return 4;
			if(this.getWeight() >= 36 && this.getWeight() <= 45)	return 3;
			if(this.getWeight() >= 46 && this.getWeight() <= 51)	return 2;
			if(this.getWeight() >= 52 && this.getWeight() <= 55)	return 1;
		}
		if(this.type == Api.FRUIT_NUT) {
			if(this.getWeight() >= 0 && this.getWeight() <= 42)		return 2;
			if(this.getWeight() >= 43 && this.getWeight() <= 65)	return 1;
		}
		return 0;
	}
	
	public Double getWeight() {
		Double w = 0.0;
		for(Equipment e : this.getEquipments())
			w += e.getWeight();
		return w;
	}
	
	public Integer getType() {
		return this.type;
	}
	
	public void setType(Integer type) {
		this.type = type;
		
		this.maxDefence = this.type == Api.FRUIT_CHERRY ? 0 : (this.type == Api.FRUIT_KIWI ? 1 : 2);
		this.maxHp = this.type == Api.FRUIT_CHERRY ? 15 : (this.type == Api.FRUIT_KIWI ? 20 : 25);
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
		for(Equipment e : this.getEquipments())
			if(e.getRange() > r)	r = e.getRange();
		return r;
	}
	
	public Integer getRangeBase() {
		return this.range;
	}
	
	public List<Equipment> getEquipments() {
    	return this.equipments;
    }
	
	public void addEquipment(Equipment equip) {
		this.equipments.add(equip);
	}
	
	public void rmEquipment(Equipment equip) {
		this.equipments.remove(equip);
	}
}
