package fuzzycode;

public class Fruit {
	protected Integer x;
	protected Integer y;
	protected Integer hp;
	protected Integer maxHp; //Points de vie maximum
	protected Integer defence;
	protected Integer maxDefence; //Points de défence max
	protected Integer speed;
	protected Integer attack;
	protected Integer id;
	protected Integer pa; //Points d'actions
	protected Integer fruitType;
	protected Integer curVitamins;

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


	public Fruit(Integer type) {
		// TODO Auto-generated constructor stub
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
		return this.speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
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
	public Integer getRange(){
		return 0;
	}

}
