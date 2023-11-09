import java.util.Iterator;
import java.util.List;

public abstract class Repository {
    protected String filePath;
    protected boolean empty;
    protected List<Object> all;

    private int size;

    abstract public Object getByID(String id);

    abstract public void save();

    abstract public void load();

    public Repository(String filePath) {
        this.filePath = filePath;
        this.empty = true;
    }

    public void add(Object object) {
        if (this.empty) {
            this.empty = false;
        }
        all.add(object);
        this.size++;
    }

    public void remove(Object object) {
        all.remove(object);
        this.size--;
    }

    public Iterator<Object> iterator() {
        return all.iterator();
    }

    public void updateAll(List<Object> all) {
        this.all = all;
    }

    public void update(Object object) {
        if (all.contains(object)) {
            all.remove(object);
            all.add(object);
        } else {
            all.add(object);
        }
    }

    abstract public List<Object> findByFilter(IFilter filter);

    public boolean contains(Object object) {
        return all.contains(object);
    }

    public void clear() {
        all.clear();
        this.empty = true;
    }
}
