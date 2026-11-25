package task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListProcessor {

    private final List<Integer> values;

    public ListProcessor(List<Integer> values) {
        if (values == null) {
            throw new IllegalArgumentException("Список не должен быть null");
        }
        this.values = new ArrayList<>(values);
    }

    public List<Integer> removeConsecutiveDuplicates() {
        List<Integer> result = new ArrayList<>();

        for (Integer value : values) {
            if (result.isEmpty()
                    || !Objects.equals(result.get(result.size() - 1), value)) {
                result.add(value);
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "Исходный список: " + values;
    }
}
