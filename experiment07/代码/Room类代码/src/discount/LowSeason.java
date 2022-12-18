package discount;

import discount.Season;

public class LowSeason implements Season {
    @Override
    public double discount() {
        return 0.5;
    }

    @Override
    public String name() {
        return "淡季";
    }
}
