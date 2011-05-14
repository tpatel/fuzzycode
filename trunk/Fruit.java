package fuzzycode;

public abstract class Fruit {
	protected Integer x;
	protected Integer y;
	protected Integer hp;
	protected Integer defence;
	protected Integer speed;
	protected Integer attack;
	protected Integer id;

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

	public abstract Integer getSpeed();

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
	abstract public Integer getRange();

}
