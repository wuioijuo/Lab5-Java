package task4;

public class SourCreamOffer {

    private final String company;
    private final String street;
    private final int fatness;
    private final int price;

    public SourCreamOffer(String company, String street, int fatness, int price) {
        if (company == null || company.trim().isEmpty()
                || street == null || street.trim().isEmpty()) {
            throw new IllegalArgumentException("Фирма и улица должны быть указаны");
        }
        if (fatness != 15 && fatness != 20 && fatness != 25) {
            throw new IllegalArgumentException("Допустимая жирность: 15, 20 или 25");
        }
        if (price < 2000 || price > 5000) {
            throw new IllegalArgumentException("Цена должна быть от 2000 до 5000 копеек");
        }

        this.company = company;
        this.street = street;
        this.fatness = fatness;
        this.price = price;
    }

    public int getFatness() {
        return fatness;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return company + " " + street + " " + fatness + "% " + price + " коп.";
    }
}
