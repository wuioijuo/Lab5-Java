import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class QueueChecker {

    private final Queue<Integer> queue;

    public QueueChecker(Queue<Integer> queue) {
        if (queue == null) {
            throw new IllegalArgumentException("Очередь не должна быть null");
        }
        this.queue = new ArrayDeque<>(queue);
    }

    public boolean hasEqualNeighboursInCircle() {
        List<Integer> items = new ArrayList<>(queue);

        if (items.isEmpty()) {
            return false;
        }

        for (int i = 0; i < items.size(); i++) {
            int nextIndex = (i + 1) % items.size();
            if (Objects.equals(items.get(i), items.get(nextIndex))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "Очередь: " + queue;
    }
}
