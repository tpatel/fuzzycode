package fuzzycode;

import game.Api;

import java.util.*;

public class Proxy {

	private Map<Integer, Fruit> fruits;
	private Map<Integer, Equipment> equipments;
	private Map<Integer, Building> buildings;
	private Map<Integer, Chest> chests;
	private Map<Integer, SugarDrop> sugarDrops;

	private Cell[][] map;
	
	private int LIMIT_CHERRY = 0;
	private int LIMIT_KIWI = 0;
	private int LIMIT_NUT = 0;

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
				case Api.NOTHING:
					break;

				case Api.WALL:
					getCell(i, j).setWall(true);
					break;

				case Api.BUILDING_VITAMIN_SOURCE:
					building = new Building(Api.BUILDING_VITAMIN_SOURCE);
					getCell(i, j).setBuilding(building);
					break;

				case Api.BUILDING_FRUCTIFICATION_TANK:
					building = new Building(Api.BUILDING_FRUCTIFICATION_TANK);
					getCell(i, j).setBuilding(building);
					break;

				case Api.BUILDING_JUICE_BARREL:
					building = new Building(Api.BUILDING_JUICE_BARREL);
					getCell(i, j).setBuilding(building);
					break;

				case Api.BUILDING_SUGAR_BOWL:
					building = new Building(Api.BUILDING_SUGAR_BOWL);
					getCell(i, j).setBuilding(building);
					break;

				case Api.BUILDING_SUGAR_TREE:
					building = new Building(Api.BUILDING_SUGAR_TREE);
					getCell(i, j).setBuilding(building);
					break;

				}
			}
		}

		for (int i = 0; i< fruits.length; ++i) {
			Ally fruit = new Ally(fruits[i][Api.OBJECT_TYPE]);
			this.fruits.put(fruits[i][Api.OBJECT_ID], fruit);
			getCell(fruits[i][Api.OBJECT_X], fruits[i][Api.OBJECT_Y]).setFruit(fruit);
		}
		
		for (int i = 0; i< buildings.length; ++i) {
			building = new Building(buildings[i][Api.OBJECT_ID]);
			this.buildings.put(buildings[i][Api.OBJECT_ID], building);
			getCell(buildings[i][Api.OBJECT_X], buildings[i][Api.OBJECT_Y]).setBuilding(building);
		}
		
		LIMIT_CHERRY = limitCherry;
		LIMIT_KIWI = limitKiwi;
		LIMIT_NUT = limitNut;
	}

	public void updateMap(Integer[][] newObjects, Integer[] deletedObjects,
			Integer[][] movedFruits, Integer[][] modifiedFruits,
			Integer[][] modifiedSugarDrops) {
		
		for (int i=0; i<newObjects.length; ++i){
			switch (newObjects[i][Api.OBJECT_TYPE]) {
			case Api.FRUIT_CHERRY:
				
				break;

			default:
				break;
			}
		}
		
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
