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

    public RepositoryList<T> getAll() {
        return all;
    }

    public void insert(T item) {
        all.add(item);
    }

    protected boolean setAll(RepositoryList<T> all) {
        this.all = all;
        return true;
    }

}