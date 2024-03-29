package fuzzycode;

import game.Api;

import java.awt.Point;
import java.util.*;

public class Proxy {

	private Map<Integer, Fruit> fruits;
	private Map<Integer, Equipment> equipments;
	private Map<Integer, Building> buildings;
	private Map<Integer, Chest> chests;
	private Map<Integer, SugarDrop> sugarDrops;

	private Cell[][] map;

	private Integer limitCherry = 0;
	private Integer limitKiwi = 0;
	private Integer limitNut = 0;
	private Integer vitaminGoal = 0;
	private Integer maxNbTurns = 0;
	private Integer currentTurn = 0;
	public Integer getCurrentTurn() {
		return currentTurn;
	}

	private Double totalSugar = 0.0;
	private Integer totalVitamins = 0;

	private static Proxy instance = null;

	public static Proxy getProxy() {
		if (instance == null) {
			instance = new Proxy();
		}
		return instance;
	}

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

	public Integer getMapWidth() {
		return map.length;
	}

	public Integer getMapHeight() {
		if (getMapWidth() == 0)
			return 0;

		return (map[0]).length;
	}

	public Cell[][] getMap() {
		return map;
	}

	public Integer getLimitCherry() {
		return limitCherry;
	}

	public Integer getLimitKiwi() {
		return limitKiwi;
	}

	public Integer getLimitNut() {
		return limitNut;
	}

	public Integer getVitaminGoal() {
		return vitaminGoal;
	}

	public Integer getMaxNbTurns() {
		return maxNbTurns;
	}

	
	public Double getTotalSugar() {
		return totalSugar;
	}


	public Integer getTotalVitamins() {
		return totalVitamins;
	}


	public Proxy() {
		this.fruits = new HashMap<Integer, Fruit>();
		this.equipments = new HashMap<Integer, Equipment>();
		this.buildings = new HashMap<Integer, Building>();
		this.chests = new HashMap<Integer, Chest>();
		this.sugarDrops = new HashMap<Integer, SugarDrop>();

		this.map = new Cell[0][0];
	}

	public void initMap(Integer[][] architecture, Integer[][] fruits,
			Integer[][] buildings, Integer limitCherry, Integer limitKiwi,
			Integer limitNut, Integer vitaminGoal, Integer maxNbTurns) {
		creerMap(architecture.length, architecture[0].length);

		Building building = null;
		for (int i = 0; i < getMapWidth(); ++i) {
			for (int j = getMapHeight() - 1; j >= 0; --j) {
				switch (architecture[i][j]) {
				case Api.NOTHING:
					break;

				case Api.WALL:
					getCell(i, j).setWall(true);
					break;

				case Api.BUILDING_VITAMIN_SOURCE:
				case Api.BUILDING_FRUCTIFICATION_TANK:
				case Api.BUILDING_JUICE_BARREL:
				case Api.BUILDING_SUGAR_BOWL:
				case Api.BUILDING_SUGAR_TREE:
					building = new Building(architecture[i][j]);
					building.setFriend(false);
					getCell(i, j).setBuilding(building);
					break;

				default:
					break;
				}
			}
		}

		for (int i = 0; i < fruits.length; ++i) {
			creerFruit(fruits[i][Api.OBJECT_ID], fruits[i][Api.OBJECT_X],
					fruits[i][Api.OBJECT_Y], fruits[i][Api.OBJECT_TYPE], new Boolean(true));
		}

		for (int i = 0; i < buildings.length; ++i) {
			building = new Building(buildings[i][Api.OBJECT_TYPE]);
			building.setFriend(true);
			this.buildings.put(buildings[i][Api.OBJECT_ID], building);
			getCell(buildings[i][Api.OBJECT_X], buildings[i][Api.OBJECT_Y])
					.setBuilding(building);
		}

		this.limitCherry = limitCherry;
		this.limitKiwi = limitKiwi;
		this.limitNut = limitNut;
		this.vitaminGoal = vitaminGoal;
		this.maxNbTurns = maxNbTurns;
	}

	private void creerMap(int width, int length) {
		this.map = new Cell[width][length];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < length; j++) {
				map[i][j] = new Cell();
			}
		}
	}

	public void updateMap(Integer[][] newObjects, Integer[] deletedObjects,
			Integer[][] movedObjects, Integer[][] modifiedFruits,
			Integer[][] modifiedSugarDrops) {

		Equipment equip = null;
		Chest chest = null;
		SugarDrop sDrop = null;
		currentTurn++;
		
		// ==================== Nouveaux objets
		for (int i = 0; i < newObjects.length; ++i) {
			switch (newObjects[i][Api.OBJECT_TYPE]) {

			case Api.FRUIT_CHERRY:
			case Api.FRUIT_KIWI:
			case Api.FRUIT_NUT:
				creerFruit(newObjects[i][Api.OBJECT_ID],
						newObjects[i][Api.OBJECT_X],
						newObjects[i][Api.OBJECT_Y],
						newObjects[i][Api.OBJECT_TYPE], false);
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
				equip.setAmmo(newObjects[i][Api.OBJECT_INFO]);
				equipments.put(newObjects[i][Api.OBJECT_ID], equip);
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
				sDrop.setNbrElement(newObjects[i][Api.OBJECT_INFO]);
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
			Integer identifiant = deletedObjects[i];
			fruits.remove(identifiant);
			equipments.remove(identifiant);
			buildings.remove(identifiant);
			chests.remove(identifiant);
			sugarDrops.remove(identifiant);
		}

		// ========= objets bougés
		for (int i = 0; i < movedObjects.length; i++) {
			// Les objets qui bougent sont les équipements et les fruits
			Integer identifiant = movedObjects[i][Api.OBJECT_ID];
			Equipment equipementBouge = equipments.get(identifiant);
			if (equipementBouge != null) {
				getCell(movedObjects[i][Api.OBJECT_X],
						movedObjects[i][Api.OBJECT_Y]).removeEquipment(
						equipementBouge);
				getCell(movedObjects[i][Api.OBJECT_NEW_X],
						movedObjects[i][Api.OBJECT_NEW_Y]).addEquipment(
						equipementBouge);
			} else {
				Fruit fruitBouge = fruits.get(identifiant);
				if (fruitBouge != null) {
					moveFruit(fruitBouge, movedObjects[i][Api.OBJECT_X],
							movedObjects[i][Api.OBJECT_Y],
							movedObjects[i][Api.OBJECT_NEW_X],
							movedObjects[i][Api.OBJECT_NEW_Y]);
				}
			}
		}

		// ========= fruits modifiés
		for (int i = 0; i < modifiedFruits.length; i++) {
			// Les objets qui bougent sont les équipements et les fruits
			Integer identifiant = modifiedFruits[i][Api.OBJECT_ID];

			Fruit fruitModifie = fruits.get(identifiant);
			if (fruitModifie != null) {
				fruitModifie.setDefense(modifiedFruits[i][Api.OBJECT_LIFE]);
				fruitModifie.setHp(modifiedFruits[i][Api.OBJECT_LIFE]);
			}
		}

		// ========= sugarDrops modifiés
		for (int i = 0; i < modifiedSugarDrops.length; i++) {
			// Les objets qui bougent sont les équipements et les fruits
			Integer identifiant = modifiedSugarDrops[i][Api.OBJECT_ID];

			SugarDrop drop = sugarDrops.get(identifiant);
			if (drop != null) {
				drop.setNbrElement(modifiedSugarDrops[i][Api.OBJECT_SUGAR]);
			}
		}

	}

	public void updateMap(Integer[][] newObjects, Integer[][] modifiedSugarDrops) {
		Equipment equip = null;
		Chest chest = null;
		SugarDrop sDrop = null;

		// ==================== Nouveaux objets
		for (int i = 0; i < newObjects.length; ++i) {
			switch (newObjects[i][Api.OBJECT_TYPE]) {

			case Api.FRUIT_CHERRY:
			case Api.FRUIT_KIWI:
			case Api.FRUIT_NUT:
				creerFruit(newObjects[i][Api.OBJECT_ID],
						newObjects[i][Api.OBJECT_X],
						newObjects[i][Api.OBJECT_Y],
						newObjects[i][Api.OBJECT_TYPE], false);
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
				equip.setAmmo(newObjects[i][Api.OBJECT_INFO]);
				equipments.put(newObjects[i][Api.OBJECT_ID], equip);
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

		// ========= sugarDrops modifiés
		for (int i = 0; i < modifiedSugarDrops.length; i++) {
			// Les objets qui bougent sont les équipements et les fruits
			Integer identifiant = modifiedSugarDrops[i][Api.OBJECT_ID];

			SugarDrop drop = sugarDrops.get(identifiant);
			if (drop != null) {
				drop.setNbrElement(modifiedSugarDrops[i][Api.OBJECT_SUGAR]);
			}
		}
	}

	public List<Fruit> getFruits(boolean ami) {
		List<Fruit> fruitsRetour = new ArrayList<Fruit>();
		for (Integer identifier : fruits.keySet()) {
			if (fruits.get(identifier).isFriend() == ami) {
				fruitsRetour.add(fruits.get(identifier));
			}
		}
		return fruitsRetour;
	}

	public Integer move(Fruit fruit, Integer x, Integer y) {
		Integer retour = Api.move(fruit.getId(), x, y);
		System.out.println("Déplacement du fruit vers " + x +"/" + y);
		if (retour == Api.OK) {
			moveFruit(fruit, fruit.getX(), fruit.getY(), x, y);
			fruit.setPa(fruit.getPa() - 1);
		}
		else System.out.println(Api.decode(retour));
		return retour;
	}

	public Integer attack(Fruit shooter, Fruit target) {
		Integer retour = Api.attack(shooter.getId().intValue(), target.getId().intValue());
		if (retour == Api.HIT) {
			target.setHp(Math.max(0, shooter.getAttack()
							- target.getDefense()));
			shooter.setPa(shooter.getPa() - 1);
		} else if (retour >= 0) {
			target.setHp(0);
			removeFruit(target);
			shooter.setCurVitamins(shooter.getCurVitamins() + retour);
			shooter.setPa(shooter.getPa() - 1);
		}
		return retour;
	}

	public Integer drinkJuice(Fruit drinker) {
		Integer retour = Api.drinkJuice(drinker.getId());
		if (retour == Api.LIFE_GAINED) {
			drinker.setHp(drinker.getHp()
					+ Math.min(drinker.getMaxHp() - drinker.getHp(), 5));
			drinker.setPa(drinker.getPa() - 1);
		} else if (retour == Api.DEFENSE_GAINED) {
			drinker.setDefense(drinker.getDefense()
					+ Math.min(drinker.getMaxDefense() - drinker.getDefense(),
							1)); // drinker.defence++
			drinker.setPa(drinker.getPa() - 1);
		}
		return retour;
	}

	public Integer fructify(Integer desiredFruitType, Integer x, Integer y) {
		Integer retour = Api.fructify(desiredFruitType, x, y);
		if (retour > 0) {
			creerFruit(retour, x, y, desiredFruitType, true);
		}
		return retour;
	}

	public Integer drawVitamin(Fruit fruit) {
		Integer retour = Api.drawVitamin(fruit.getId());
		if (retour == Api.OK) {
			fruit.setCurVitamins(fruit.getCurVitamins() + 1);
			fruit.setPa(fruit.getPa() - 1);
		}
		return retour;
	}

	public Integer useEquipment(Fruit fruit, Equipment equipment, Fruit target) {
		Integer retour = Api.useEquipment(fruit.getId(), equipment.getId(),
				target.getId());
		if (retour == Api.HIT) {
			if (equipment.getType() == Api.EQUIPMENT_PEELER){
				target.setDefense(target.getDefense() - 1);
			} else {
			target.setHp(Math.max(0, equipment.getAttack()
					- target.getDefense()));
			}
			fruit.setPa(fruit.getPa() - 1);
		} else if (retour == Api.RELOADED && equipment.getType() == Api.EQUIPMENT_JUICE_NEEDLE) {
			fruit.setHp(fruit.getHp() + 4);
			fruit.setPa(fruit.getPa() - 1);
		} else if (retour >= 0) {
			target.setHp(0);
			removeFruit(target);
			fruit.setCurVitamins(fruit.getCurVitamins() + retour);
			fruit.setPa(fruit.getPa() - 1);
		} 
		return retour;
	}

	public Integer useEquipment(Fruit fruit, Equipment equipment,
			Equipment target) {
		Integer retour = Api.useEquipment(fruit.getId(), equipment.getId(),
				target.getId());
		if (retour == Api.RELOADED && equipment.getType() == Api.EQUIPMENT_RELOADER) {
			target.setAmmo(target.getAmmo() + 100);
			fruit.setPa(fruit.getPa() - 1);
		}
		return retour;
	}

	public Integer pickUpEquipment(Fruit picker, Equipment equipment, Integer positionXEquipment, Integer positionYEquipment) {
		Integer retour = Api.pickUpEquipment(picker.getId(), equipment.getId());
		if (retour == Api.OK) {
			picker.addEquipment(equipment);
			getCell(positionXEquipment, positionYEquipment).removeEquipment(equipment);
			picker.setPa(picker.getPa() - 1);
		}
		return retour;
	}

	public Integer dropEquipment(Fruit dropper, Equipment equipment, Integer x,
			Integer y) {
		Integer retour = Api.dropEquipment(dropper.getId(), equipment.getId(),
				x, y);
		if (retour == Api.OK) {
			dropper.removeEquipment(equipment);
			getCell(x, y).addEquipment(equipment);
			dropper.setPa(dropper.getPa() - 1);
		}
		return retour;
	}

	public Integer pickUpSugar(Fruit picker, SugarDrop sugarDrop, Integer positionXSugar, Integer positionYSugar) {
		Integer retour = Api.pickUpSugar(picker.getId(), sugarDrop.getId());
		if (retour == Api.OK) {
			if (retour == Api.SOME_SUGAR_TAKEN) {
				sugarDrop.setNbrElement(sugarDrop.getNbrElement() - 10);
			} else if (retour == Api.ALL_SUGAR_TAKEN) {
				getCell(positionXSugar, positionYSugar).setSugarDrop(null);
				sugarDrops.remove(sugarDrop.getId());
			}
			picker.setSugar(picker.getSugar() + Math.min(10, sugarDrop.getNbrElement()));
			picker.setPa(picker.getPa() - 1);
		}
		return retour;
	}

	public Integer dropSugar(Fruit dropper, Integer quantity, Integer x,
			Integer y) {
		Integer retour = Api.dropSugar(dropper.getId(), quantity, x, y);
		if (retour == Api.OK){
			int realQuantityDropped = Math.min(quantity, dropper.getSugar());
			dropper.setSugar(dropper.getSugar() - realQuantityDropped);
			SugarDrop sDrop = new SugarDrop();
			sDrop.setNbrElement(realQuantityDropped);
			getCell(x, y).setSugarDrop(sDrop);
		}
		dropper.setPa(dropper.getPa() - 1);
		return retour;
	}

	public Integer openChest(Fruit oppener, Chest chest, Integer positionX, Integer positionY) {
		Integer retour = Api.openChest(oppener.getId(), chest.getId());
		if (retour == Api.OK) {
			getCell(positionX, positionY).setChest(null);
			chests.remove(chest.getId());
			oppener.setPa(oppener.getPa() - 1);
		}
		return retour;
	}

	public Integer stockSugar(Fruit stocker) {
		Integer retour = Api.stockSugar(stocker.getId());
		System.out.println("Stockage du sucre depuis " + stocker.getX() + "/"+ stocker.getY());
		if (retour == Api.OK) {
			this.totalSugar += stocker.getSugar();
			stocker.setSugar(0);
			this.totalVitamins += stocker.getCurVitamins();
			stocker.setCurVitamins(0);
			stocker.setPa(stocker.getPa() - 1);
		}
		else System.out.println(Api.decode(retour));
		return retour;
	}

	public Integer sellEquipment(Fruit seller, Equipment equipment) {
		Integer retour = Api.sellEquipment(seller.getId(), equipment.getId());
		if (retour == Api.OK) {
			this.totalSugar += equipment.getSellValue();
			seller.removeEquipment(equipment);
			seller.setPa(seller.getPa() - 1);
		}
		return retour;
	}

	public Integer buyEquipment(Fruit buyer, Integer equipmentType) {
		Integer retour = Api.buyEquipment(buyer.getId(), equipmentType);
		if (retour > 0) {			
			Equipment equipment = new Equipment(equipmentType);
			equipment.setId(retour);
			this.totalSugar -= equipment.getBuyValue();
			buyer.addEquipment(equipment);
			equipments.put(equipment.getId(), equipment);
			buyer.setPa(buyer.getPa() - 1);
		}
		return retour;
	}

	
	// ========== Private functions 
	private void moveFruit(Fruit fruit, Integer oldX, Integer oldY,
			Integer newX, Integer newY) {
		if (oldX > 0 && oldY > 0) {
			removeFruitFromMap(fruit);
		}
		fruit.setX(newX);
		fruit.setY(newY);
		getCell(newX, newY).setFruit(fruit);

	}

	private void removeFruitFromMap(Fruit fruit) {
		getCell(fruit.getX(), fruit.getY()).setFruit(null);

	}

	private void removeFruit(Fruit fruit) {
		removeFruitFromMap(fruit);
		fruits.put(fruit.getId(), null);
		fruits.remove(fruit.getId());
	}

	private void creerFruit(Integer identifiant, Integer x, Integer y,
			Integer type, Boolean isAmi) {
		Fruit fruit = new Fruit(type);
		fruit.setId(identifiant);
		fruit.setFriend(isAmi);
		fruit.setX(x);
		fruit.setY(y);
		
		System.out.println("Creation" + isAmi + " " + x + " " + y);
		this.getCell(x, y).setFruit(fruit);
		fruits.put(identifiant, fruit);
		getCell(x, y).setFruit(fruit);
	}
	
	public Point getBuildingFriend(Integer typeBuilding) {
		for (int i = 0; i < Proxy.getProxy().getMapWidth(); i++) {
			for (int j = 0; j < Proxy.getProxy().getMapHeight(); j++) {
				Building b = Proxy.getProxy().getCell(i, j).getBuilding();
				if (b != null && b.getType() == typeBuilding && b.isFriend()) {
					return new Point(i, j);
				}
			}
		}
		return null;
	}
	
	public Point getBuildingNeutral(Integer typeBuilding) {
		for (int i = 0; i < Proxy.getProxy().getMapWidth(); i++) {
			for (int j = 0; j < Proxy.getProxy().getMapHeight(); j++) {
				Building b = Proxy.getProxy().getCell(i, j).getBuilding();
				if (b != null && b.getType() == typeBuilding) {
					return new Point(i, j);
				}
			}
		}
		return null;
	} 

}
