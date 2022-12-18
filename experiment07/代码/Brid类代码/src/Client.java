import Befavior.AbleSwim;
import Befavior.FreeFly;
import Befavior.UnableFly;
import Befavior.UnableSwim;
import Entiy.Brid;
import Entiy.Eagle;
import Entiy.Peigon;

public class Client {
    public static void main(String[] args) {
        Brid eagle=new Eagle(new FreeFly(),new UnableSwim());
        eagle.dispaly();
        Brid peigon=new Peigon(new UnableFly(),new AbleSwim());
        peigon.dispaly();
    }
}