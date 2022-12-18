package Entiy;

import Befavior.FlyBefavior;
import Befavior.SwimBehavior;

public abstract class Brid {
    protected FlyBefavior flyBefavior;
    protected SwimBehavior swimBehavior;
    public Brid(FlyBefavior flyBefavior, SwimBehavior swimBehavior) {
        this.flyBefavior=flyBefavior;
        this.swimBehavior=swimBehavior;
    }

    public abstract void dispaly();
}
