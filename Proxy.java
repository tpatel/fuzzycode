package fuzzycode;

import java.util.*;

public class Proxy {

	private final static int OBJECT_ID = 0;
	private final static int OBJECT_X = 1;
	private final static int OBJECT_Y = 2;
	private final static int OBJECT_TYPE = 3;
	private final static int NOTHING = 10;
	private final static int WALL = 11;
	private final static int BUILDING_VITAMIN_SOURCE = 12;
	private final static int BUILDING_SUGAR_TREE = 13;
	private final static int BUILDING_JUICE_BARREL = 14;
	private final static int BUILDING_SUGAR_BOWL = 15;
	private final static int BUILDING_FRUCTIFICATION_TANK = 16;

	private Map<Integer, Fruit> fruits;
	private Map<Integer, Equipment> equipments;
	private Map<Integer, Building> buildings;
	private Map<Integer, Chest> chests;
	private Map<Integer, SugarDrop> sugarDrops;

	private Cell[][] map;

	public Fruit getFruit(Integer id) {
		return fruits.get(id);
	}

	public Equipment getEquipment(Integer id) {
		return equipments.get(id);
	}

	public Building getBuilding(Integer id) {
		return buildings.get(id);
	}

	public Chest getChest(Integer id) {
		return chests.get(id);
	}

	public SugarDrop getSugarDrop(Integer id) {
		return sugarDrops.get(id);
	}

	public Cell getCell(Integer x, Integer y) {
		return map[x][y];
	}

	public Integer getMapWitdh() {
		return map.length;
	}

	public Integer getMapHeight() {
		if (getMapWitdh() == 0)
			return 0;

		return (map[0]).length;
	}

	public void initMap(Integer[][] architecture, Integer[][] fruits,
			Integer[][] buildings, Integer limitCherry, Integer limitKiwi,
			Integer limitNut) {
		Building building = null;
		for (int i = 0; i < getMapWitdh(); ++i) {
			for (int j = 0; j < getMapHeight(); ++j) {
				switch (architecture[i][j]) {
				case NOTHING:
					break;

				case WALL:
					getCell(i, j).setWall(true);
					break;

				case BUILDING_VITAMIN_SOURCE:
					building = new Building(Building.VITAMIN_SOURCE);
					getCell(i, j).setBuilding(building);
					break;

				case BUILDING_FRUCTIFICATION_TANK:
					building = new Building(Building.FRUCTIFICATION_TANK);
					getCell(i, j).setBuilding(building);
					break;

				case BUILDING_JUICE_BARREL:
					building = new Building(Building.JUICE_BARREL);
					getCell(i, j).setBuilding(building);
					break;

				case BUILDING_SUGAR_BOWL:
					building = new Building(Building.SUGAR_BOWL);
					getCell(i, j).setBuilding(building);
					break;

				case BUILDING_SUGAR_TREE:
					building = new Building(Building.SUGAR_TREE);
					getCell(i, j).setBuilding(building);
					break;

				}
			}
		}

		for (int i = 0; i< fruits.length; ++i) {

		}
	}

	public void updateMap(Integer[][] newObjects, Integer[] deletedObjects,
			Integer[][] movedFruits, Integer[][] modifiedFruits,
			Integer[][] modifiedSugarDrops) {
	}

	public void updateMap(Integer[][] newObjects, Integer[][] modifiedSugarDrops) {
	}

	public Integer move(Fruit fruit, Integer x, Integer y) {
		return 0;
	}

	public Integer attack(Fruit shooter, Fruit target) {
		return 0;
	}

	public Integer drinkJuice(Fruit drinker) {
		return 0;
	}

	public Integer fructify(Fruit creator, Integer desiredFruitType, Integer x,
			Integer y) {
		return 0;
	}

	public Integer drawVitamin(Fruit fruit) {
		return 0;
	}

	public Integer useEquipment(Fruit fruit, Equipment equipment, Fruit target) {
		return 0;
	}

	public Integer pickUpEquipment(Fruit picker, Equipment equipment) {
		return 0;
	}

	public Integer dropEquipment(Fruit dropper, Equipment equipment, Integer x,
			Integer y) {
		return 0;
	}

	public Integer pickUpSugar(Fruit picker, SugarDrop sugarDrop) {
		return 0;
	}

	public Integer dropSugar(Fruit dropper, Integer quantity, Integer x,
			Integer y) {
		return 0;
	}

	public Integer openChest(Fruit oppener, Chest chest) {
		return 0;
	}

	public Integer stockSugar(Fruit stocker) {
		return 0;
	}

	public Integer sellEquipment(Fruit seller, Equipment equipment) {
		return 0;
	}

	public Integer buyEquipment(Fruit buyer, Equipment equipment) {
		return 0;
	}

}
