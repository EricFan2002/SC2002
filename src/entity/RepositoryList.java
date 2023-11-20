package entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import entity.interfaces.ITaggedItem;

public class RepositoryList<T extends ITaggedItem> implements Iterable<T> {
    protected List<T> all;

    // size of List
    public int size() {
        return all.size();
    }

    // constructors using different parameters
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

    // iterator
    public Iterator<T> iterator() {
        return all.iterator();
    }

    // getter
    public T get(int index) {
        if (index >= 0 && index < all.size()) {
            return all.get(index);
        } else {
            // Handle the invalid index, e.g., by returning null or throwing a custom
            // exception
            return null;
        }
    }

    // add 1 item to List
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

    // remove 1 item from List
    public boolean remove(T object) {
        return all.remove(object);
    }

    // change item at index to input item
    public boolean update(T object, int index) {
        all.set(index, object);
        return true;
    }

    // go to List and find item with the same ID as the input item and replace it
    // with the input item
    public boolean update(T object) {
        for (T item : all) {
            if (item.getID().equals(object.getID())) {
                // replace the old one
                all.set(all.indexOf(item), object);
                return true;
            }
        }
        return false;
    }

    // remove all items from List
    public boolean clear() {
        all.clear();
        return true;
    }

    public Object[] toArray() {
        Object[] array = new Object[all.size()];
        return all.toArray(array);
    }
}
