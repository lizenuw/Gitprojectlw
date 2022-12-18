package discount;

import discount.UseCard;

public class CommonUser implements UseCard {
    @Override
    public double discount() {
        return 1;
    }

    @Override
    public String name() {
        return "普通用户";
    }
}
