public class CountingMeowable implements Meowable {

    private final Meowable original;
    private int count;

    public CountingMeowable(Meowable original) {
        if (original == null) {
            throw new IllegalArgumentException("Объект для декорирования не должен быть null");
        }
        this.original = original;
        this.count = 0;
    }

    @Override
    public void meow() {
        original.meow();
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Количество мяуканий: " + count;
    }
}
