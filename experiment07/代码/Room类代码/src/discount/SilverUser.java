package discount;

import discount.UseCard;

public class SilverUser implements UseCard {
    @Override
    public double discount() {
        return 0.8;
    }

    @Override
    public String name() {
        return "银卡用户";
    }
}
