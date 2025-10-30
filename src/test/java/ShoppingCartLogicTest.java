import app.ShoppingCartLogic;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShoppingCartLogicTest {


    // added delta 1e-6 for accuracy
    @Test
    void testTotalCalculationWithMultipleItems() {
        List<Double> prices = Arrays.asList(10.0, 5.0, 2.5);
        List<Integer> quantities = Arrays.asList(2, 3, 4);
        double expectedTotal = 10.0*2 + 5.0*3 + 2.5*4;
        double actualTotal = ShoppingCartLogic.calculateTotal(prices, quantities);
        assertEquals(expectedTotal, actualTotal, 1e-6);
    }

    @Test
    void testTotalCalculationWithSingleItem() {
        List<Double> prices = Arrays.asList(12.5);
        List<Integer> quantities = Arrays.asList(4);
        double expectedTotal = 12.5 * 4;
        double actualTotal = ShoppingCartLogic.calculateTotal(prices, quantities);

        assertEquals(expectedTotal, actualTotal, 1e-6);
    }

    @Test
    void testTotalCalculationWithEmptyLists() {
        List<Double> prices = Arrays.asList();
        List<Integer> quantities = Arrays.asList();

        double expectedTotal = 0.0;
        double actualTotal = ShoppingCartLogic.calculateTotal(prices, quantities);

        assertEquals(expectedTotal, actualTotal, 1e-6);
    }

}
