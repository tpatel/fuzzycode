public interface ProxyInterface {    
    //  DAO
    public Fruit getFruit(Integer id);
    public Equipment getEquipment(Integer id);
    public Building getBuilding(Integer id);
    public Chest getChest(Integer id);
    public SugarDrop getSugarDrop(Integer id);
    
    public  Cell getCell(Integer x, Integer y);
    public Integer getMapWitdh();
    public Integer getMapHeight();
    public void initMap(Integer[][] architecture, Integer[][] fruits, Integer[][] buidlings, Integer limitCherry, Integer limitKiwi, Integer limitNut);
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
