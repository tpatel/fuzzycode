public class Cell {
	private Fruit fruit;
	private List<Equipement> equipements;
	private Building building;
	private Chest chest;
	private SugarDrop sugarDrop;
	private Integer controlLevel;
	private Integer dangerLevel;
	private Integer strategicInterest;
	private Boolean wall;
	
	public Boolean isOccupied() {
		return this.isWall() || this.hasEquipement() || this.hasBuilding() || this.hasChest() || this.hasFruit();
	}
	
	public Boolean isWall() {
		return this.wall;
	}
	
	public Boolean hasFruit() {
		return this.getFruit() != null;
	}
	
	public Boolean hasEquipment() {
		return this.getEquipements().size() > 0;
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
	
	public List<Equipement> getEquipments() {
		return this.equipment;
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
