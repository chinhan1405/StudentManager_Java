package StudentManager;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class ObjData<T> {
    private HashMap<String, T> data = new HashMap<>();
    private int count = 0;

    public HashMap<String, T> getData() {
        return data;
    }

    public ArrayList<T> getDataAsList() {
        ArrayList<T> objs = new ArrayList<>();
        for (String key : data.keySet()) {
            objs.add(data.get(key));
        }
        return objs;
    }
 
    public T get(String id) {
        return getData().get(id);
    }

    public boolean contain(String id) {
        return getData().containsKey(id);
    }

    public boolean contain(T obj) {
        return getData().containsValue(obj);
    }

    public int getCount() {
        return count;
    }

    private void increaseCount() {
        this.count++;
    }

    private void decreaseCount() {
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
