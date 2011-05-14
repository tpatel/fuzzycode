public abstract class Fruit {
        protected Integer x;
        protected Integer y;
        protected Integer hp;
        protected Integer defence;
        protected Integer speed;
        protected Integer attack;
        protected Integer id;
        
        abstract public Integer getX();
        abstract public Integer setX();
        
        abstract public Integer getY();
        abstract public Integer setY();
        
        abstract public Integer getHp();
        abstract public Integer setHp();
        
        abstract public Integer getDefense();
        abstract public Integer getSpeed();
        abstract public Integer getAttack();
        abstract public Integer getId();
        
        //Range de base ou range max en comptant les objets
        abstract public Integer getRange();
        
}
