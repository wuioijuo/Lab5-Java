import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SourCreamAnalyzer {

    private final List<SourCreamOffer> offers;

    public SourCreamAnalyzer(List<SourCreamOffer> offers) {
        if (offers == null) {
            throw new IllegalArgumentException("Список предложений не должен быть null");
        }
        this.offers = new ArrayList<>(offers);
    }

    public Map<Integer, Integer> countCheapestStores() {
        Map<Integer, Integer> minPrices = new HashMap<>();
        Map<Integer, Integer> counts = new LinkedHashMap<>();

        for (Integer fatness : Arrays.asList(15, 20, 25)) {
            counts.put(fatness, 0);
        }

        for (SourCreamOffer offer : offers) {
            int fatness = offer.getFatness();
            int price = offer.getPrice();
            Integer currentMin = minPrices.get(fatness);

            if (currentMin == null || price < currentMin) {
                minPrices.put(fatness, price);
                counts.put(fatness, 1);
            } else if (price == currentMin) {
                counts.put(fatness, counts.get(fatness) + 1);
            }
        }

        return counts;
    }

    @Override
    public String toString() {
        return "Предложения сметаны: " + offers;
    }
}
