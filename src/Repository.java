import java.util.Iterator;
import java.util.List;

public abstract class Repository<Object> implements Iterable<Object> {
    protected String filePath;
    protected List<Object> all;

    protected int size;

    public Repository(String filePath) {
        this.filePath = filePath;
        this.size = 0;
    }

    public abstract Object getByID(String id);

    public abstract void save();

    public abstract void load();

    public boolean isEmpty() {
        return this.size == 0;
    }

    public abstract void add(Object object);

    public abstract void update(Object object);

    public void updateAll(List<Object> all) {
        this.all = all;
    }

    public abstract void remove(Object object);

    public Iterator<Object> iterator() {
        return all.iterator();
    }

    abstract public List<Object> findByFilter(IFilter filter);

    public boolean contains(Object object) {
        return all.contains(object);
    }

    public abstract void clear();
}
