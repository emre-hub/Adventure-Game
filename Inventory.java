public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private boolean caveAward;
    private boolean forestAward;
    private boolean riverAward;
    private boolean quarryAward;

    public Inventory() {
        this.weapon = new Weapon("Yumruk", -1, 0,0);
        this.armor = new Armor(-1, "Gömlek" ,0,0);
        this.caveAward = false;
        this.forestAward = false;
        this.riverAward = false;
        this.quarryAward = false;
    }

    public Weapon getWeapon() {
        return weapon;
    }
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    public Armor getArmor(){
        return armor;
    }
    public void setArmor(Armor armor){
        this.armor = armor;
    }

    public boolean isCaveAward() {
        return caveAward;
    }

    public void setCaveAward(boolean caveAward) {
        this.caveAward = caveAward;
    }

    public boolean isForestAward() {
        return forestAward;
    }

    public void setForestAward(boolean forestAward) {
        this.forestAward = forestAward;
    }

    public boolean isRiverAward() {
        return riverAward;
    }

    public void setRiverAward(boolean riverAward) {
        this.riverAward = riverAward;
    }

    public boolean isQuarryAward() {
        return quarryAward;
    }

    public void setQuarryAward(boolean quarryAward) {
        this.quarryAward = quarryAward;
    }
}
