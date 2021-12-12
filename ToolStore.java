public class ToolStore extends NormalLoc{
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println("-------- Mağazaya Hoşgeldiniz ! --------");
        boolean showMenu = true;
        while(showMenu){
            System.out.println("1 - Silahlar");
            System.out.println("2 - Zırhlar");
            System.out.println("3 - Çıkış");
            System.out.print("Seçiminiz  :");

            int selectCase = input.nextInt();

            while(selectCase < 1 || selectCase > 3){
                System.out.println("Geçersiz seçim, tekrar seçiniz : ");
                selectCase = input.nextInt();
            }
            switch (selectCase){
                case 1 : printWeapon(); buyWeapon(); break;
                case 2 : printArmor(); buyArmor(); break;
                case 3 :
                    System.out.println("Bir daha bekleriz...");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printWeapon(){
        System.out.println("---- Silahlar ----");
        for (Weapon w : Weapon.weapons()){
            /*weapons() metodu static tanımladığım için,
            Weapon sınıfından nesne üretmeden bu metodu kullanabilirim.
            Metoddan dönen array uzerinde foreach dongusu kurdum.*/
            System.out.println("[" + w.getId() + "]- " + w.getName() + " <Para : " + w.getPrice() + ", Hasar : " + w.getDamage() + ">");
        }
        System.out.println("0- Çıkış Yap");
    }

    public void buyWeapon(){
        System.out.println("Bir silah seçiniz : ");
        int selectWeaponID = input.nextInt();

        while(selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length){
            System.out.println("Geçersiz seçim, tekrar seçiniz : ");
            selectWeaponID = input.nextInt();
        }
        if(selectWeaponID != 0){
            Weapon selectedWeapon = Weapon.getWeaponObjById(selectWeaponID);
            if(selectedWeapon != null){
                if(selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yeterli paranız bulunmamaktadır!");
                }else{
                    //Satın almanın gerçekleştiği alan :
                    System.out.println(selectedWeapon.getName() + " satın aldınız !");
                    this.getPlayer().getInventory().setWeapon(selectedWeapon); //Silah envantere eklendi
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();//Para ödeniyor..
                    this.getPlayer().setMoney(balance);
                    //System.out.println("Kalan paranız : " + this.getPlayer().getMoney());
                }
            }
        }
    }

    public void printArmor() {
        System.out.println("---- Zırhlar ----");
        for (Armor a : Armor.armors())
            System.out.println("[" + a.getId() + "]- " + a.getName() + " <Para : " + a.getPrice() + ", Defans : " + a.getBlock() + ">");
        System.out.println("0- Çıkış Yap");
    }

    public void buyArmor(){
        System.out.println("Bir zırh seçiniz : ");
        int selectArmorID = input.nextInt();

        while(selectArmorID < 0 || selectArmorID > Armor.armors().length){
            System.out.println("Geçersiz seçim, tekrar seçiniz : ");
            selectArmorID = input.nextInt();
        }

        if(selectArmorID != 0){
            Armor selectedArmor = Armor.getArmorObjById(selectArmorID);
            if(selectedArmor != null){
                if(selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yeterli paranız bulunmamaktadır!");
                }else{
                    //Satın almanın gerçekleştiği alan :
                    System.out.println(selectedArmor.getName() + " zırh satın aldınız !");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedArmor.getPrice());
                    this.getPlayer().getInventory().setArmor(selectedArmor); //Zırh envantere eklendi
                }
            }
        }
    }
}
