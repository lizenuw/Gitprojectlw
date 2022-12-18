package Befavior;

import Befavior.SwimBehavior;

public class AbleSwim implements SwimBehavior {
    @Override
    public void swim() {
        System.out.println("鱼翔浅底");
    }
}
