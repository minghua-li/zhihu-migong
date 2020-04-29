import java.util.ArrayList;
import java.util.List;

public class MySequentialStack<T> implements MyStack<T> {
    private final List<T> list = new ArrayList<>();

    @Override
    public T pop() {
        if (!list.isEmpty()) {
            return list.remove(list.size() - 1);
        }
        return null;
    }

    public T peek() {
        if (!list.isEmpty()) {
            return list.get(list.size() - 1);
        }
        return null;
    }

    @Override
    public void push(T t) {
        list.add(t);
    }
}
