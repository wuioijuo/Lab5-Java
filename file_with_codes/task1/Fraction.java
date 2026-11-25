package task1;

public class Fraction implements FractionOperations {

    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю");
        }

        if (denominator == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Слишком большое значение знаменателя");
        }

        if (denominator < 0) {
            if (numerator == Integer.MIN_VALUE) {
                throw new IllegalArgumentException("Невозможно изменить знак числителя");
            }
            numerator = -numerator;
            denominator = -denominator;
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    @Override
    public double getValue() {
        return (double) numerator / denominator;
    }

    @Override
    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    @Override
    public void setDenominator(int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю");
        }

        if (denominator == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Слишком большое значение знаменателя");
        }

        if (denominator < 0) {
            if (numerator == Integer.MIN_VALUE) {
                throw new IllegalArgumentException("Невозможно изменить знак числителя");
            }
            this.numerator = -this.numerator;
            this.denominator = -denominator;
        } else {
            this.denominator = denominator;
        }
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Fraction)) {
            return false;
        }

        Fraction other = (Fraction) object;
        return numerator == other.numerator
                && denominator == other.denominator;
    }

    @Override
    public int hashCode() {
        return 31 * numerator + denominator;
    }
}
