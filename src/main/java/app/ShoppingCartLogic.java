package app;

import java.util.List;


// logic of calculation

public class ShoppingCartLogic {

    public static double calculateTotal(List<Double> prices, List<Integer> quantities) {
        double total = 0.0;
        for (int i = 0; i < prices.size(); i++) {
            total += prices.get(i) * quantities.get(i);
        }
        return total;
    }
}
