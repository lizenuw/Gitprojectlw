package Enity;

import discount.Season;
import discount.UseCard;

public class SuperRoom extends Room {
    double price=1000;
    public SuperRoom(Season season, UseCard useCard) {
        super(season, useCard);
    }

    @Override
    public void display() {
        price=price*season.discount()*useCard.discount();
        System.out.println("此用户为"+useCard.name()+","+"当前季节为"+season.name());
        System.out.println("贵宾房原价为1000"+","+"现价为"+price);
    }
}
