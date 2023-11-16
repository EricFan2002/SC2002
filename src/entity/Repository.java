package entity;

import entity.interfaces.ITaggedItem;

public abstract class Repository<T extends ITaggedItem> {
    protected String filePath;
    protected RepositoryList<T> all;

    //constructor
    public Repository(String filePath) {
        this.filePath = filePath;
        this.all = new RepositoryList<T>();
    }

    public abstract boolean save();

    public abstract boolean load();

    public int size() {
        return all.size(); 
    }

    //getter
    public RepositoryList<T> getAll() {
        // deep copy
        RepositoryList<T> all = new RepositoryList<T>();
        for (T item : this.all) {
            all.add(item);
        }
        return all;
    }

    //add 1 item to RepositoryList 
    public void insert(T item) {
        all.add(item);
    }

    //add multiple items to RepositoryList from an array
    public void insert(T[] items) {
        for (T item : items) {
            all.add(item);
        }
    }

    //add multiple items to RepositoryList from another RepositoryList
    public void insert(RepositoryList<T> items) {
        for (T item : items) {
            all.add(item);
        }
    }

    //remove 1 item from RepositoryList
    public boolean remove(T item) {
        return all.remove(item);
    }

    //go to RepositoryList then to List and find item with the same ID as the input item and replace it with the input item
    public boolean update(T object) {
        for (T item : all) {
            if (item.getID().equals(object.getID())) {
                // replace the old one
                all.all.set(all.all.indexOf(item), object);
                return true;
            }
        }
        return false;
    }

    //setter
    protected boolean setAll(RepositoryList<T> all) {
        this.all = all;
        return true;
    }

}