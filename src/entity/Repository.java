package entity;

public abstract class Repository<T extends Object> {
    protected String filePath;
    protected RepositoryList<T> all;

    public abstract boolean save();

    public abstract boolean load();

    public RepositoryList<T> getAll() {
        return all;
    }

    public boolean setAll(RepositoryList<T> all) {
        this.all = all;
        return true;
    }

}