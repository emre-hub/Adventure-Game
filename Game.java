import java.util.Scanner;


public class Game {
    private Scanner input = new Scanner(System.in); //public de tanımlayabilirdim, onemli degil. sadece bu sınıf içinde kullanacağım için private tanımlaıdm

    public void start(){
        System.out.println("Macera Oyununa Hoşgeldiniz!");
        System.out.print("Lütfen bir isim giriniz : ");
        //String playerName = input.nextLine();
        Player player = new Player("Emre");
        System.out.println("Merhaba " + player.getName() + ". Bu karanlık ve sisli adaya hoşgeldin!! Burada yaşananların hepsi gerçek...");
        System.out.println("Lütfen bir karakter seçiniz : ");
        System.out.println("--------------------------------------");
        player.selectChar();
        Location location = null;

        while (true){
            player.printInfo();
            System.out.println();
            System.out.println("#########Bölgeler##########");
            System.out.println();
            System.out.println("1 - Güvenli Ev ----> Burası sizin için güvenli bir ev, burada düşman bulunmaz.");
            System.out.println("2 - Dükkan ----> Burada Silah veya Zırh satın alabilirsiniz !");
            System.out.println("3 - Mağara ----> Ödül : <Yemek>, dikkatli ol karşına Zombi çıkabilir !");
            System.out.println("4 - Orman ---->  Ödül : <Odun> , dikkatli ol karşına Vampir çıkabilir !");
            System.out.println("5 - Nehir ---->  Ödül : <Su>   , dikkatli ol karşına Ayı çıkabilir !");
            System.out.println("6 - Maden ---->  Ödül : <Rastgele>   , dikkatli ol karşına Yılan çıkabilir !");
            System.out.println("0 - Çıkış Yap ----> Oyunu Sonlandır.");
            System.out.println("Lütfen gitmek istediğiniz bölgeyi seçiniz : ");
            int selectLoc = input.nextInt();
            switch (selectLoc){
                case 0 : location = null; break;
                case 1 : location = new SafeHouse(player); break;
                case 2 : location = new ToolStore(player); break;
                case 3 :
                        if(player.getInventory().isCaveAward()){
                            System.out.println("Bu bölge temiz.");
                            location = new SafeHouse(player);
                        }else{
                            location = new Cave(player);
                        }
                        break;
                case 4 :
                        if(player.getInventory().isForestAward()){
                            System.out.println("Bu bölge temiz.");
                            location = new SafeHouse(player);
                        }else{
                            location = new Forest(player);
                        }
                        break;
                case 5 :
                        if(player.getInventory().isRiverAward()){
                            System.out.println("Bu bölge temiz.");
                            location = new SafeHouse(player);
                        }else{
                            location = new River(player);
                        }
                        break;
                case 6 :
                        if(player.getInventory().isQuarryAward()){
                            System.out.println("Bu bölge temiz.");
                            location = new SafeHouse(player);
                        }else{
                            location = new Quarry(player);
                        }
                        break;
                default:
                    System.out.println("Lütfen geçerli bir bölge seçiniz !");break;
            }

            if(location == null){
                System.out.println("Bu karanlık ve sisli adadan çabuk vazgeçtin!");
                break;
            }
            if(!location.onLocation()){
                System.out.println("Game Over!");
                break;
            }
        }
    }
}
