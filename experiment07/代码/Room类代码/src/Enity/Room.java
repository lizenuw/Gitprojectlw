package Enity;

import discount.Season;
import discount.UseCard;

public abstract class Room {
    protected Season season;
    protected UseCard useCard;
    public Room(Season season, UseCard useCard) {
        this.season=season;
        this.useCard=useCard;
    }
    public abstract void display();
}
