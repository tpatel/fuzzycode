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
    
    public Boolean isOccupied();
    public Boolean isWall();
    
    public Boolean hasFruit();
    public Boolean hasEquipment();
    public Boolean hasBuilding();
    public Boolean hasChest();
    public Boolean hasSugar();
    
    public Fruit getFruit();
    public List<Equipement> getEquipments();
    public Building getBuilding();
    public Chest getChest();
    public SugarDrop getSugar();
}
