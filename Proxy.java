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

				default:
					break;
				}
			}
		}

		for (int i = 0; i < fruits.length; ++i) {
			Fruit fruit = new Fruit(fruits[i][Api.OBJECT_TYPE]);
			// TODO Mettre à jour le booléen ami
			this.fruits.put(fruits[i][Api.OBJECT_ID], fruit);
			getCell(fruits[i][Api.OBJECT_X], fruits[i][Api.OBJECT_Y]).setFruit(
					fruit);
		}

		for (int i = 0; i < buildings.length; ++i) {
			building = new Building(buildings[i][Api.OBJECT_ID]);
			this.buildings.put(buildings[i][Api.OBJECT_ID], building);
			getCell(buildings[i][Api.OBJECT_X], buildings[i][Api.OBJECT_Y])
					.setBuilding(building);
		}

		LIMIT_CHERRY = limitCherry;
		LIMIT_KIWI = limitKiwi;
		LIMIT_NUT = limitNut;
	}

	public void updateMap(Integer[][] newObjects, Integer[] deletedObjects,
			Integer[][] movedObjects, Integer[][] modifiedFruits,
			Integer[][] modifiedSugarDrops) {

		Fruit enemy = null;
		Equipment equip = null;
		Chest chest = null;
		SugarDrop sDrop = null;

		// ==================== Nouveaux objets
		for (int i = 0; i < newObjects.length; ++i) {
			switch (newObjects[i][Api.OBJECT_TYPE]) {

			case Api.FRUIT_CHERRY:
			case Api.FRUIT_KIWI:
			case Api.FRUIT_NUT:
				enemy = new Fruit(newObjects[i][Api.OBJECT_TYPE]);
				fruits.put(newObjects[i][Api.OBJECT_ID], enemy);
				// TODO Mettre à jour le booléen
				getCell(newObjects[i][Api.OBJECT_X],
						newObjects[i][Api.OBJECT_Y]).setFruit(enemy);
				break;

			case Api.EQUIPMENT_CUTTER:
			case Api.EQUIPMENT_JUICE_NEEDLE:
			case Api.EQUIPMENT_LEMONER:
			case Api.EQUIPMENT_LIGHTER:
			case Api.EQUIPMENT_PEELER:
			case Api.EQUIPMENT_RELOADER:
			case Api.EQUIPMENT_SALT_SNIPER:
			case Api.EQUIPMENT_TEA_SPOON:
			case Api.EQUIPMENT_TOOTHPICK:
				equip = new Equipment(newObjects[i][Api.OBJECT_TYPE]);
				equipments.put(newObjects[i][Api.OBJECT_ID], equip);
				// TODO Mettre à jour les munitions
				getCell(newObjects[i][Api.OBJECT_X],
						newObjects[i][Api.OBJECT_Y]).addEquipment(equip);
				break;

			case Api.CHEST:
				chest = new Chest();
				chests.put(newObjects[i][Api.OBJECT_ID], chest);
				getCell(newObjects[i][Api.OBJECT_X],
						newObjects[i][Api.OBJECT_Y]).setChest(chest);
				break;

			case Api.SUGAR_DROP:
				sDrop = new SugarDrop();
				sugarDrops.put(newObjects[i][Api.OBJECT_ID], sDrop);
				getCell(newObjects[i][Api.OBJECT_X],
						newObjects[i][Api.OBJECT_Y]).setSugarDrop(sDrop);
				break;
				
			default:
				break;
			}
		}
		
		// =========== objets supprimés
		for (int i = 0; i < deletedObjects.length; ++i) {
			int identifiant = deletedObjects[i];
			fruits.remove(identifiant);
			equipments.remove(identifiant);
			buildings.remove(identifiant);
			chests.remove(identifiant);
			sugarDrops.remove(identifiant);
		}
		
		// ========= objets bougés
		for (int i = 0; i < movedObjects.length; i++) {
			// Les objets qui bougent sont les équipements et les fruits
			int identifiant = movedObjects[i][Api.OBJECT_ID];
			equipments.get(identifiant);
		}

	}

	public void updateMap(Integer[][] newObjects, Integer[][] modifiedSugarDrops) {
	}

	public Integer move(Fruit fruit, Integer x, Integer y) {
		Integer retour = Api.move(fruit.getId(), x, y);
		if (retour == Api.OK) {
			fruit.setX(x);
			fruit.setY(y);
			fruit.setPa(fruit.getPa() - 1);
		}
		return retour;
	}

	public Integer attack(Fruit shooter, Fruit target) {
		Integer retour = Api.attack(shooter.getId(), target.getId());
		if (retour == Api.OK) {
			target
					.setHp(Math.max(0, shooter.getAttack()
							- target.getDefence()));
			shooter.setPa(shooter.getPa() - 1);
		}
		return retour;
	}

	public Integer drinkJuice(Fruit drinker) {
		Integer retour = Api.drinkJuice(drinker.getId());
		if (retour == Api.OK) {
			if (drinker.getMaxHp() - drinker.getHp() > 0) {
				drinker.setHp(drinker.getHp()
						+ Math.min(drinker.getMaxHp() - drinker.getHp(), 5));
			} else if (drinker.getMaxDefence() - drinker.getDefence() > 0) {
				drinker.setDefence(drinker.getDefence()
						+ Math.min(drinker.getMaxDefence()
								- drinker.getDefence(), 1)); // drinker.defence++
			}
			drinker.setPa(drinker.getPa()-1);
		}
		return retour;
	}

	public Integer fructify(Fruit creator, Integer desiredFruitType, Integer x,
			Integer y) {
		Integer retour = Api.fructify(creator.getFruitType(), x, y);
		if(retour == Api.OK) {
			//TODO: action
			creator.setPa(creator.getPa()-1);
			throw new RuntimeException("Pas encore géré !");
		}
		return retour;
	}

	public Integer drawVitamin(Fruit fruit) {
		Integer retour = Api.drawVitamin(fruit.getId());
		if(retour == Api.OK) {
			fruit.setCurVitamins(fruit.getCurVitamins()+1);
			fruit.setPa(fruit.getPa()-1);
		}
		return retour;
	}

	public Integer useEquipment(Fruit fruit, Equipment equipment, Fruit target) {
		Integer retour = Api.useEquipment(fruit.getId(), equipment.getId(), target.getId());
		if(retour == Api.OK) {
			//TODO: action
			fruit.setPa(fruit.getPa()-1);
			throw new RuntimeException("Pas encore géré !");
		}
		return retour;
	}

	public Integer pickUpEquipment(Fruit picker, Equipment equipment) {
		Integer retour = Api.pickUpEquipment(picker.getId(), equipment.getId());
		if(retour == Api.OK) {
			//TODO: action
			picker.setPa(picker.getPa()-1);
			throw new RuntimeException("Pas encore géré !");
		}
		return retour;
	}

	public Integer dropEquipment(Fruit dropper, Equipment equipment, Integer x,
			Integer y) {
		Integer retour = Api.dropEquipment(dropper.getId(), equipment.getId(), x, y);
		if(retour == Api.OK) {
			//TODO: action
			dropper.setPa(dropper.getPa()-1);
			throw new RuntimeException("Pas encore géré !");
		}
		return retour;
	}

	public Integer pickUpSugar(Fruit picker, SugarDrop sugarDrop) {
		Integer retour = Api.pickUpSugar(picker.getId(), sugarDrop.getId());
		if(retour == Api.OK) {
			//TODO: action
			picker.setPa(picker.getPa()-1);
			throw new RuntimeException("Pas encore géré !");
		}
		return retour;
	}

	public Integer dropSugar(Fruit dropper, Integer quantity, Integer x,
			Integer y) {
		Integer retour = Api.dropSugar(dropper.getId(), quantity, x, y);
		if(retour == Api.OK) {
			//TODO: action
			dropper.setPa(dropper.getPa()-1);
			throw new RuntimeException("Pas encore géré !");
		}
		return retour;
	}

	public Integer openChest(Fruit oppener, Chest chest) {
		Integer retour = Api.openChest(oppener.getId(), chest.getId());
		if(retour == Api.OK) {
			//TODO: action
			oppener.setPa(oppener.getPa()-1);
			throw new RuntimeException("Pas encore géré !");
		}
		return retour;
	}

	public Integer stockSugar(Fruit stocker) {
		Integer retour = Api.stockSugar(stocker.getId());
		if(retour == Api.OK) {
			//TODO: action
			stocker.setPa(stocker.getPa()-1);
			throw new RuntimeException("Pas encore géré !");
		}
		return retour;
	}

	public Integer sellEquipment(Fruit seller, Equipment equipment) {
		Integer retour = Api.sellEquipment(seller.getId(), equipment.getId());
		if(retour == Api.OK) {
			//TODO: action
			seller.setPa(seller.getPa()-1);
			throw new RuntimeException("Pas encore géré !");
		}
		return retour;
	}

	public Integer buyEquipment(Fruit buyer, Equipment equipment) {
		Integer retour = Api.buyEquipment(buyer.getId(), equipment.getType());
		if(retour == Api.OK) {
			//TODO: action
			buyer.setPa(buyer.getPa()-1);
			throw new RuntimeException("Pas encore géré !");
		}
		return retour;
	}

}
