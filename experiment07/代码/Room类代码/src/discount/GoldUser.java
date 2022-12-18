package discount;

import discount.UseCard;

public class GoldUser implements UseCard {
    @Override
    public double discount() {
        return 0.5;
    }

    @Override
    public String name() {
        return "金卡用户";
    }
}
