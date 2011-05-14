package fuzzycode;

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
	
	public void setFruit(Fruit fruit) {
		this.fruit = fruit;
	}
	
	public void addEquipment(Equipment equip) {
		this.equipments.add(equip);
	}
	
	public void removeEquipment(Equipment equip) {
		this.equipments.remove(equip);
	}
	
	public void setBuilding(Building building) {
		this.building = building;
	}
	
	public void setChest(Chest chest) {
		this.chest = chest;
	}
	
	public void setSugarDrop(SugarDrop sugar) {
		this.sugarDrop = sugar;
	}
	
	public void setControlLevel(Integer level) {
		this.controlLevel = level;
	}
	
	public void setDangerLevel(Integer level) {
		this.dangerLevel = level;
	}
	
	public void setStrategicInterest(Integer interest) {
		this.strategicInterest = interest;
	}
	
	public void setWall(Boolean wall) {
		this.wall = wall;
	}
	
	public Integer getControlLevel() {
		return this.controlLevel;
	}
	
	public Integer getDangerLevel() {
		return this.dangerLevel;
	}
	
	public Integer getStrategicInterest() {
		return this.strategicInterest;
	}
	
	public Boolean isOccupied() {
		return this.isWall() || this.hasEquipments() || this.hasBuilding() || this.hasChest() || this.hasFruit();
	}
	
	public Boolean isWall() {
		return this.wall;
	}
	
	public Boolean hasFruit() {
		return this.getFruit() != null;
	}
	
	public Boolean hasEquipments() {
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
