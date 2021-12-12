import java.util.Locale;
import java.util.Random;

public class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber(); //bulundugumuz mekandaki canavar sayısı rastgele oluşturulur.
        System.out.println("Şu an buradasınız : " + this.getName());
        System.out.println("Dikkatli Ol! Burada " + obsNumber + " tane " + this.getObstacle().getName() + " yaşıyor !");
        System.out.println("<S>avaş veya <K>aç! : ");
        String selectCase = input.nextLine().toUpperCase();
        if(selectCase.equals("S") && combat(obsNumber)){ //combat metoduna gidilir, orada düşmanlar yenilip true deger döndürülürse bu blok çalışır
            System.out.println(this.getName() + " tüm düşmanları yendiniz ! ");
            return true;
        }
        if(this.getPlayer().getHealth() <= 0){ //combattan false değer dönmüştür, oyuncunun canı 0'a inmiştir
            System.out.println("Öldünüz...");
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber){ //düşman sayısı olan obsNumber, onLocation'da belirlendi.
        Random rand = new Random(); //rand nesnesini daha sonra rastgele sayı uretmek için kullanacağım
        for(int i = 1; i <= obsNumber; i++){
            this.getObstacle().setHealth(this.getObstacle().getOrjinalHealth()); //Her for döngüsünde, canavarın canını orijinal can degeri ile degistiriyorum.
            // Böylelikle while döngüsünde canavar ölmüşse ve mekanda hala canavar varsa, yeni canavarla savaş devam ediyor.
            playerStats();
            obstacleStats(i);
            while(this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                System.out.println("<V>ur veya <K>aç : ");
                String selectCombat = input.nextLine().toUpperCase();
                if(selectCombat.equals("V")){
                    int rand_int1 = rand.nextInt(10) + 1; //Saldırıya Canavar ya da oyuncunun başlama ihtimali : %50
                    if(rand_int1 >= 5){ //Sıra şansı oyuncunun ise :
                        System.out.println("Oyuncu vurdu !!");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if(this.getObstacle().getHealth() > 0){ //Canavarın vuruş yaptığı kısım burada başlıyor
                            System.out.println();
                            System.out.println(this.getObstacle().getName() + " vurdu !!");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock(); //Zırh, canavarın hasarını emer.
                            if(obstacleDamage < 0){//obstacleDamage negatif olursa, canavarın hasarı oyuncuya can olarak eklenir. Bu bugı engelledim.
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage); //canavarın vurduğu toplam hasar
                            afterHit();
                        }
                    }else{ //Saldırı şansı canavarın ise :
                        System.out.println(this.getObstacle().getName() + " vurdu !!");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock(); //Zırh, canavarın hasarını emer.
                        if(obstacleDamage < 0){ //obstacleDamage negatif olursa, canavarın hasarı oyuncuya can olarak eklenir. Bu bugı engelledim.
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage); //canavarın vurduğu toplam hasar
                        afterHit();
                        if(this.getPlayer().getHealth() > 0){//Oyuncu ölmediyse canavara karşılık verecek
                            System.out.println("Oyuncu vurdu !!");
                            this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                            afterHit();
                        }
                    }
                }else{
                    return false;
                }
            }
            if(this.getObstacle().getHealth() < this.getPlayer().getHealth()){
                if(this.getObstacle().getName() == "Yılan"){ //Oyuncu yılanla savaşmışsa, yılandan düşen itemları envantere ekliyorum
                    int odul = yaratikOdulu();
                    if (odul == 1){
                        if(this.getPlayer().getWeapon().getId() < 1){
                            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(1));
                        }
                    }
                    else if(odul == 2){
                        if(this.getPlayer().getWeapon().getId() < 2){
                            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(2));
                        }
                    }
                    else if(odul == 3){
                            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(3));
                    }
                    else if(odul == 4){
                        if(this.getPlayer().getArmor().getId() < 3){
                            this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(3));
                        }
                    }
                    else if(odul == 5){
                        if(this.getPlayer().getArmor().getId() < 2){
                            this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(2));
                        }
                    }
                    else if(odul == 6){
                        if(this.getPlayer().getArmor().getId() < 1){
                            this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(1));
                        }
                    }
                    else if(odul == 7)
                        this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
                    else if(odul == 8)
                        this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                    else if(odul == 9)
                        this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                }
                System.out.println("Düşmanı Yendiniz !!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel Paranız : " + this.getPlayer().getMoney());
            }else{
                return false;
            }
        }
        //BÖLGE TEMİZLENMİŞSE :
        if(this.getName() == "Mağara"){
            System.out.println("Bölge Temizlendi, Mağara Ödülü Alındı!!");
            this.getPlayer().getInventory().setCaveAward(true);
        }else if(this.getName() == "Orman"){
            System.out.println("Bölge Temizlendi, Orman Ödülü Alındı!!");
            this.getPlayer().getInventory().setForestAward(true);
        }else if(this.getName() == "Nehir"){
            System.out.println("Bölge Temizlendi, Nehir Ödülü Alındı!!");
            this.getPlayer().getInventory().setRiverAward(true);
        }else if(this.getName() == "Maden"){
            System.out.println("Bölge Temizlendi, Maden Ödülü Alındı!!");
            this.getPlayer().getInventory().setQuarryAward(true);
        }
        return true;
    }

    public void afterHit(){
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı : " + this.getObstacle().getHealth());
        System.out.println("-----------------------");
    }

    public void playerStats(){
        System.out.println("Karakter Durumu");
        System.out.println("--------------------------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Hasar : " + this.getPlayer().getDamage());
        System.out.println("Silah : " + this.getPlayer().getWeapon().getName());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para : " + this.getPlayer().getMoney());
        System.out.println("**");
    }
    public void obstacleStats(int i){
        System.out.println(i + ". " +this.getObstacle().getName() + " Durumu");
        System.out.println("--------------------------------------");
        System.out.println("Sağlık : " + this.getObstacle().getHealth());
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        System.out.println("Ödül : " + this.getObstacle().getAward());
        System.out.println();
    }

    public int randomObstacleNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }
    public int yaratikOdulu(){
        Random rand = new Random();
        int rand_int1 = rand.nextInt(100) + 1;
        if(rand_int1 <= 15){
            int rand_int2 = rand.nextInt(100) + 1;
            if(rand_int2 <= 20){
                System.out.println("Tüfek kazandınız.");
                return 1;
            }else if(rand_int2 <= 50){
                System.out.println("Kılıç kazandınız.");
                return 2;
            }else{
                System.out.println("Tabanca kazandınız.");
                return 3;
            }
        }else if(rand_int1 <= 30){
            int rand_int2 = rand.nextInt(100) + 1;
            if(rand_int2 <= 20){
                System.out.println("Ağır Zırh kazandınız.");
                return 4;
            }else if(rand_int2 <= 50){
                System.out.println("Orta Zırh kazandınız.");
                return 5;
            }else{
                System.out.println("Hafif Zırh kazandınız.");
                return 6;
            }
        }else if(rand_int1 <= 55){
            int rand_int2 = rand.nextInt(100) + 1;
            if(rand_int2 <= 20){
                System.out.println("10 para kazandınız.");
                return 7;
            }else if(rand_int2 <= 50){
                System.out.println("5 para kazandınız.");
                return 8;
            }else{
                System.out.println("1 para kazandınız.");
                return 9;
            }
        }else{
            System.out.println("");
            return 0;
        }
    }

}
