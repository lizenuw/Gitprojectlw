import Enity.CommonRoom;
import Enity.Room;
import Enity.SuperRoom;
import discount.GoldUser;
import discount.HighSeason;

public class Client {
    public static void main(String[] args) {
        Room commonroom=new CommonRoom(new HighSeason(),new GoldUser());
        commonroom.display();
        Room superroom=new SuperRoom(new HighSeason(),new GoldUser());
        superroom.display();
    }
}