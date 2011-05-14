import java.util.*;

public class Cell {
	private Fruit fruit;
	private List<Equipment> equipments;
	private Building building;
	private Chest chest;
	private SugarDrop sugarDrop;
	private Integer controlLevel;
	private Integer dangerLevel;
	private Integer strategicInterest;
	private Boolean wall;
	
	public Boolean isOccupied() {
		return this.isWall() || this.hasEquipment() || this.hasBuilding() || this.hasChest() || this.hasFruit();
	}
	
	public Boolean isWall() {
		return this.wall;
	}
	
	public Boolean hasFruit() {
		return this.getFruit() != null;
	}
	
	public Boolean hasEquipment() {
		return this.getEquipments().size() > 0;
	}
	
	public Boolean hasBuilding() {
		return this.getBuilding() != null;
	}
	
	public Boolean hasChest() {
		return this.getChest() != null;
	}
	
	public Boolean hasSugar() {
		return this.getSugar() != null;
	}
	
	public Fruit getFruit() {
		return this.fruit;
	}
	
	public List<Equipment> getEquipments() {
		return this.equipments;
	}
	
	public Building getBuilding() {
		return this.building;
	}
	
	public Chest getChest() {
		return this.chest;
	}
	
	public SugarDrop getSugar() {
		return this.sugarDrop;
	}
}
