package discount;

import discount.Season;

public class HighSeason implements Season {
    @Override
    public double discount() {
        return 1.5;
    }

    @Override
    public String name() {
        return "旺季";
    }
}
