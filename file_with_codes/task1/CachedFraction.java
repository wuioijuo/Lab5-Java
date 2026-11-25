package task1;

public class CachedFraction implements FractionOperations {

    private final FractionOperations original;
    private Double cachedValue;

    public CachedFraction(FractionOperations original) {
        if (original == null) {
            throw new IllegalArgumentException("Исходная дробь не должна быть null");
        }
        this.original = original;
        this.cachedValue = null;
    }

    @Override
    public double getValue() {
        if (cachedValue == null) {
            System.out.println("Кэш пуст — вычисляем значение дроби.");
            cachedValue = original.getValue();
        } else {
            System.out.println("Используем сохранённое значение из кэша.");
        }
        return cachedValue;
    }

    @Override
    public void setNumerator(int numerator) {
        original.setNumerator(numerator);
        cachedValue = null;
    }

    @Override
    public void setDenominator(int denominator) {
        original.setDenominator(denominator);
        cachedValue = null;
    }

    @Override
    public String toString() {
        String cacheText = cachedValue == null ? "пуст" : cachedValue.toString();
        return "Кэширующая дробь{" + original + ", кэш=" + cacheText + "}";
    }
}
