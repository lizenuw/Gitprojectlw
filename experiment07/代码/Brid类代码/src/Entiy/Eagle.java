package Entiy;

import Befavior.FlyBefavior;
import Befavior.SwimBehavior;

public class Eagle extends Brid {
    public Eagle(FlyBefavior flyBefavior, SwimBehavior swimBehavior) {
        super(flyBefavior, swimBehavior);
    }

    @Override
    public void dispaly() {
        System.out.println("老鹰：");
        flyBefavior.fly();
        swimBehavior.swim();
    }
}
