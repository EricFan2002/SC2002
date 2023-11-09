package entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import entity.interfaces.ITaggedItem;

public class RepositoryList<T extends ITaggedItem> implements Iterable<T> {
    protected List<T> all;

    public int size() {
        return all.size();
    }

    public RepositoryList(List<T> all) {
        this.all = all;
    }

    public RepositoryList() {
        this.all = new ArrayList<T>();
    }

    public RepositoryList(T[] all) {
        this.all = new ArrayList<T>();
        for (T object : all) {
            this.all.add(object);
        }
    }

    public RepositoryList(Set<T> all) {
        this.all = new ArrayList<T>();
        for (T object : all) {
            this.all.add(object);
        }
    }

    public Iterator<T> iterator() {
        return all.iterator();
    }

    public T get(int index) {
        return all.get(index);
    }

    public boolean add(T object) {
        for (T item : all) {
            if (item.getID().equals(object.getID())) {
                // replace the old one
                all.set(all.indexOf(item), object);
                return true;
            }
        }
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
