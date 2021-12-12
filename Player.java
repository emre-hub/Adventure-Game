import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int orjinalHealth;
    private int money;
    private String name;
    private String charName;
    private Inventory inventory;
    private Scanner input = new Scanner(System.in);

    public Player(String name){
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar(){
        GameChar[] charList = {new Samurai(), new Archer() , new Knight()};
        System.out.println("Karakterler : ");
        System.out.println("--------------------------------------");
        for(GameChar gameChar : charList){
            System.out.print("ID: [" + gameChar.getId() + "]\t");
            System.out.println("Karakter : " + gameChar.getName() +
                    " \tHasar : " + gameChar.getDamage() +
                    " \tSağlık : " + gameChar.getHealth() +
                    " \tPara : " + gameChar.getMoney());
        }
        System.out.println("--------------------------------------");
        System.out.println("Seçiminiz (ID) :  ");
        int selectChar = input.nextInt();
        switch (selectChar){
            case 1 : initPlayer(new Samurai()); break;
            case 2 : initPlayer(new Archer()); break;
            case 3 : initPlayer(new Knight()); break;
            default: initPlayer(new Samurai()); break;
        }

    }

    public void printInfo(){
        System.out.println("Silah  : " + this.getInventory().getWeapon().getName()  +
                ", Zırh : " + this.getInventory().getArmor().getName() +
                ", Defans : " + this.getInventory().getArmor().getBlock() +
                ", Hasar : " + this.getTotalDamage() +
                ", Sağlık : " + this.getHealth() +
                ", Para : " + this.getMoney());
    }

    public void initPlayer(GameChar gameChar) {
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setOrjinalHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }

    public int getTotalDamage(){
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Weapon getWeapon(){
        return this.getInventory().getWeapon();
    }

    public Armor getArmor(){
        return this.getInventory().getArmor();
    }

    public int getOrjinalHealth() {
        return orjinalHealth;
    }

    public void setOrjinalHealth(int orjinalHealth) {
        this.orjinalHealth = orjinalHealth;
    }

}


