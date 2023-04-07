import java.util.ArrayList;
import java.util.HashMap;

public abstract class ObjData<T> {
    private HashMap<String, T> data = new HashMap<>();
    private int count = 0;

    public HashMap<String, T> getData() {
        return data;
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

    public void update(String id, T obj) {
        this.remove(id);
        this.add(id, obj);
    }
}

interface IPeopleData<T> {
    public ArrayList<T> findByName(String name);
    public ArrayList<T> findByYearOfBirth(int yearOfBirth);
    public ArrayList<T> findByEmail(String email);
}