import java.util.*;

public class Proxy {
	
	private Map<Integer, Fruit> fruits;    
    private Map<Integer, Equipment> equipments;
    private Map<Integer, Building> buildings;
    private Map<Integer, Chest> chests;
    private Map<Integer, SugarDrop> sugarDrops;
    
	private Cell[][] map;
           
    public Fruit getFruit(Integer id) {
        return fruits.get(id);
    }
    
    public Equipment getEquipment(Integer id){
        return equipments.get(id);
    }
    
    public Building getBuilding(Integer id){
        return buildings.get(id);
    }
    
    public Chest getChest(Integer id){
        return chests.get(id);
    }
    
    public SugarDrop getSugarDrop(Integer id){
        return sugarDrops.get(id);
    }
    
    public  Cell getCell(Integer x, Integer y){
         return map[x][y];   
    }
    
    public Integer getMapWitdh() {
        return map.size();
    }
    
    public Integer getMapHeight(){
        return map[].size();
    }
    
    public void initMap(Integer[][] architecture, Integer[][] fruits, Integer[][] buidlings, Integer limitCherry, Integer limitKiwi, Integer limitNut) {
         
        for (Integer[] colonne : architecture) {
            for (Integer ligne : colonne) {
                switch(ligne.intValue()) {
                    case 
                }
            }
        }
         
         
    }
    
    
    public void updateMap(Integer[][] newObjects, Integer[] deletedObjects, Integer[][] movedFruits, Integer[][] modifiedFruits, Integer[][] modifiedSugerDrops);
    public void updateMap(Integer[][] newObjects, Integer[][] modifiedSugarDrops);
    
    public Integer move(Fruit fruit, Integer x, Integer y);
    public Integer move(Fruit fruit, Cell cell);
    
    public Integer attack(Fruit shooter, Fruit target);
    
    public Integer drinkJuice(Fruit drinker);
    
    public Integer fructify(Fruit creator, Integer desiredFruitType, Integer x, Integer y);
    public Integer fructify(Fruit creator, Integer desiredFruitType, Cell spwanCell);
    
    public Integer drawVitamin(Fruit fruit);
    
    public Integer useEquipment(Fruit fruit, Equipment equipment, Fruit target);
    
    public Integer pickUpEquipment(Fruit picker, Equipment equipment);
    
    public Integer dropEquipment(Fruit dropper, Equipment equipment, Integer x, Integer y);
     public Integer dropEquipment(Fruit dropper, Equipment equipment, Cell targetCell);
     
     public Integer pickUpSugar(Fruit picker, SugarDrop sugarDrop);
     
     public Integer dropSugar(Fruit dropper, Integer quantity, Integer x, Integer y);
     public Integer dropSugar(Fruit dropper, Integer quantity, Cell targetCell);
     
     public Integer openChest(Fruit oppener, Chest chest);
     
     public Integer stockSugar(Fruit stocker);
     
     public Integer sellEquipment(Fruit seller, Equipment equipment);
     
     public Integer buyEquipment(Fruit buyer, Equipment equipment);
    
}
