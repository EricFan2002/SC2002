package entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import entity.interfaces.ISerializable;
import entity.interfaces.ITaggedItem;

<<<<<<< Updated upstream
public abstract class RepositoryList<T extends ITaggedItem> implements Iterable<T>, ISerializable {
=======
/**
 * The {@code RepositoryList} abstract class provides a generic implementation for managing a list of items
 * that implement the {@code ITaggedItem} interface. It supports basic operations like add, remove, update, and clear.
 *
 * @param <T> The type of items in the repository, which must implement the {@code ITaggedItem} interface.
 */
public abstract class RepositoryList<T extends ITaggedItem> implements Iterable<T>, ISerializeable {

    /**
     * The list containing items of type {@code T}.
     */
>>>>>>> Stashed changes
    protected List<T> all;

    /**
     * Gets the size of the repository.
     *
     * @return The size of the repository.
     */
    public int size() {
        return all.size();
    }

    /**
     * Constructs a repository with the specified list of items.
     *
     * @param all The list of items to be included in the repository.
     */
    public RepositoryList(List<T> all) {
        this.all = all;
    }

    /**
     * Constructs an empty repository.
     */
    public RepositoryList() {
        this.all = new ArrayList<T>();
    }

    /**
     * Constructs a repository with items from the specified array.
     *
     * @param all The array of items to be included in the repository.
     */
    public RepositoryList(T[] all) {
        this.all = new ArrayList<T>();
        for (T object : all) {
            this.all.add(object);
        }
    }

    /**
     * Constructs a repository with items from the specified set.
     *
     * @param all The set of items to be included in the repository.
     */
    public RepositoryList(Set<T> all) {
        this.all = new ArrayList<T>();
        for (T object : all) {
            this.all.add(object);
        }
    }

    /**
     * Returns an iterator over the items in the repository.
     *
     * @return An iterator over the items in the repository.
     */
    public Iterator<T> iterator() {
        return all.iterator();
    }

    /**
     * Gets the item at the specified index.
     *
     * @param index The index of the item to retrieve.
     * @return The item at the specified index, or {@code null} if the index is invalid.
     */
    public T get(int index) {
        if (index >= 0 && index < all.size()) {
            return all.get(index);
        } else {
            // Handle the invalid index, e.g., by returning null or throwing a custom
            // exception
            return null;
        }
    }

    /**
     * Adds an item to the repository. If an item with the same ID already exists, it is replaced.
     *
     * @param object The item to be added or replaced.
     * @return {@code true} if the item is added or replaced successfully, {@code false} otherwise.
     */
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

    /**
     * Removes an item from the repository.
     *
     * @param object The item to be removed.
     * @return {@code true} if the item is removed successfully, {@code false} otherwise.
     */
    public boolean remove(T object) {
        return all.remove(object);
    }

    /**
     * Updates the item at the specified index with the input item.
     *
     * @param object The item to replace the existing item.
     * @param index  The index of the item to be updated.
     * @return {@code true} if the item is updated successfully, {@code false} otherwise.
     */
    public boolean update(T object, int index) {
        all.set(index, object);
        return true;
    }

    /**
     * Finds an item in the repository with the same ID as the input item and replaces it with the input item.
     *
     * @param object The item to replace the existing item with the same ID.
     * @return {@code true} if the item is updated successfully, {@code false} otherwise.
     */
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

    /**
     * Removes all items from the repository.
     *
     * @return {@code true} if the repository is cleared successfully, {@code false} otherwise.
     */
    public boolean clear() {
        all.clear();
        return true;
    }

    /**
     * Converts the repository to an array of objects.
     *
     * @return An array of objects containing all items in the repository.
     */
    public Object[] toArray() {
        Object[] array = new Object[all.size()];
        return all.toArray(array);
    }
}


