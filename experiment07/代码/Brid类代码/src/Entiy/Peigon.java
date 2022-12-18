package Entiy;

import Befavior.FlyBefavior;
import Befavior.SwimBehavior;

public class Peigon extends Brid {
    public Peigon(FlyBefavior flyBefavior, SwimBehavior swimBehavior) {
        super(flyBefavior, swimBehavior);
    }

    @Override
    public void dispaly() {
        System.out.println("企鹅：");
        flyBefavior.fly();
        swimBehavior.swim();
    }
}
