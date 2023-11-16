package entity;

import entity.interfaces.ITaggedItem;

public abstract class Repository<T extends ITaggedItem> {
    protected String filePath;
    protected RepositoryList<T> all;

    public Repository(String filePath) {
        this.filePath = filePath;
        this.all = new RepositoryList<T>();
    }

    public abstract boolean save();

    public abstract boolean load();

    public int size() {
        return all.size();
    }

    public RepositoryList<T> getAll() {
        // deep copy
        RepositoryList<T> all = new RepositoryList<T>();
        for (T item : this.all) {
            all.add(item);
        }
        return all;
    }

    public void insert(T item) {
        all.add(item);
    }

    public void insert(T[] items) {
        for (T item : items) {
            all.add(item);
        }
    }

    public void insert(RepositoryList<T> items) {
        for (T item : items) {
            all.add(item);
        }
    }

    public boolean remove(T item) {
        return all.remove(item);
    }

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

    // dangerous
    protected boolean setAll(RepositoryList<T> all) {
        this.all = all;
        return true;
    }

}