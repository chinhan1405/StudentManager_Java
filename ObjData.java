import java.util.HashMap;

public abstract class ObjData<T> {
    private HashMap<String, T> data = new HashMap<>();
    private int count = 0;

    public HashMap<String, T> getData() {
        return data;
    }

    public int getCount() {
        return count;
    }

    public void increaseCount() {
        this.count++;
    }

    public void decreaseCount() {
        this.count--;
    }

    public void add(String id, T obj) {
        this.data.put(id, obj);
        increaseCount();
    }

    public void remove(String id) {
        this.data.remove(id);
        decreaseCount();
    }
}
