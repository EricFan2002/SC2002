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

    public abstract boolean save();

    public abstract boolean load();

    public boolean isEmpty() {
        return this.size == 0;
    }

    public abstract boolean add(Object object);

    public abstract boolean update(Object object);

    public boolean updateAll(List<Object> all) {
        this.all = all;
    }

    public abstract boolean remove(Object object);

    public Iterator<Object> iterator() {
        return all.iterator();
    }

    abstract public List<Object> findByFilter(IFilter filter);

    public boolean contains(Object object) {
        return all.contains(object);
    }

    public abstract boolean clear();
}
