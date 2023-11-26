package app.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import app.entity.interfaces.ISerializable;
import app.entity.interfaces.ITaggedItem;
/**
 * The {@code RepositoryList} class is an abstract base class representing a list-based repository of items
 * implementing the {@link ITaggedItem} interface.
 *
 * @param <T> The type of items in the repository, which must implement the {@link ITaggedItem} interface.
 */
public abstract class RepositoryList<T extends ITaggedItem> implements Iterable<T>, ISerializable {
    /**
     * The list containing all items in the repository.
     */
    protected List<T> all;

    /**
     * Returns the size of the repository.
     *
     * @return The number of items in the repository.
     */
    public int size() {
        return all.size();
    }

    /**
     * Constructs a repository list with the given list of items.
     *
     * @param all The list of items to initialize the repository.
     */
    public RepositoryList(List<T> all) {
        this.all = all;
    }
    /**
     * Constructs an empty repository list.
     */
    public RepositoryList() {
        this.all = new ArrayList<T>();
    }

    /**
     * Constructs a repository list with the given array of items.
     *
     * @param all The array of items to initialize the repository.
     */
    public RepositoryList(T[] all) {
        this.all = new ArrayList<T>();
        for (T object : all) {
            this.all.add(object);
        }
    }
    /**
     * Constructs a repository list with the given set of items.
     *
     * @param all The set of items to initialize the repository.
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
     * Gets the item at the specified index in the repository.
     *
     * @param index The index of the item to retrieve.
     * @return The item at the specified index, or {@code null} if the index is out of bounds.
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
     * Adds an item to the repository. If an item with the same ID already exists in the repository, it is replaced.
     *
     * @param object The item to add or replace in the repository.
     * @return {@code true} if the item was added or replaced successfully, {@code false} otherwise.
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
     * @param object The item to remove from the repository.
     * @return {@code true} if the item was successfully removed, {@code false} otherwise.
     */
    public boolean remove(T object) {
        return all.remove(object);
    }

    /**
     * Updates the item at the specified index in the repository.
     *
     * @param object The item to use for the update.
     * @param index  The index of the item to update.
     * @return {@code true} if the update was successful, {@code false} otherwise.
     */
    public boolean update(T object, int index) {
        all.set(index, object);
        return true;
    }

    /**
     * Updates an item in the repository based on its ID. If an item with the same ID is found, it is replaced.
     *
     * @param object The item to use for the update.
     * @return {@code true} if the update was successful, {@code false} otherwise.
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
     * Clears all items from the repository.
     *
     * @return {@code true} if the repository was cleared successfully, {@code false} otherwise.
     */
    public boolean clear() {
        all.clear();
        return true;
    }
    /**
     * Converts the repository to an array.
     *
     * @return An array containing all items in the repository.
     */
    public Object[] toArray() {
        Object[] array = new Object[all.size()];
        return all.toArray(array);
    }
}
