package entity;

import java.util.Iterator;
import java.util.List;

public class RepositoryList<T> implements Iterable<T> {
    protected List<T> all;

    public RepositoryList(List<T> all) {
        this.all = all;
    }

    public Iterator<T> iterator() {
        return all.iterator();
    }

    public RepositoryList() {
    }

    public T get(int index) {
        return all.get(index);
    }

    public boolean add(T object) {
        return all.add(object);
    }

    public boolean remove(T object) {
        return all.remove(object);
    }

    public boolean update(T object, int index) {
        all.set(index, object);
        return true;
    }

    public boolean clear() {
        all.clear();
        return true;
    }
}
