package Befavior;

import Befavior.FlyBefavior;

public class UnableFly implements FlyBefavior {
    @Override
    public void fly() {
        System.out.println("飞不起来");
    }
}
