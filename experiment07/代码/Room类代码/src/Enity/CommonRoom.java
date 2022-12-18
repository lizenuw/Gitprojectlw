package Enity;

import discount.Season;
import discount.UseCard;

public class CommonRoom extends Room {
    double price=500;
    public CommonRoom(Season season, UseCard useCard) {
        super(season, useCard);
    }

    @Override
    public void display() {
        price=price*season.discount()*useCard.discount();
        System.out.println("此用户为"+useCard.name()+","+"当前季节为"+season.name());
        System.out.println("贵宾房原价为500"+","+"现价为"+price);
    }
}
