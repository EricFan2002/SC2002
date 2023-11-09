/*class 1)inserts camp into camp repo, 
        2)finds a camp from id 
        3)gets all camps
        4)updates camp
        5)removes camp
*/
import java.util.*;

//public class CampRepository implements ICommitteeReportGenerator {

//this class contains all the created camps even those not visible
public class CampRepository<Camp> extends Repository<Camp> {
    protected String filePath;
    protected List<Camp> all;

    protected int size;
    protected Map<Student, Camp> committee;

    public CampRepository() {
        super("src/data/camps.csv");
        this.all = new ArrayList<>();
        this.committee = new HashMap<>();
    }

    @Override
    public Camp getByID(String id) {
        return all.stream()
                .filter(camp -> camp.getId().equals(id))
                .findFirst()
                .orElse(null); // Returns null if Camp is not found
    }

    public boolean save() {

    }

    public boolean load() {

    }

    @Override
    public boolean add(Camp camp) {
        all.add(camp);
    }

    public boolean update(Camp camp) {
        all.set(all.indexOf(camp), camp);
    }

    public boolean updateAll(List<Camp> all) {
        this.all = all;
    }

    public boolean remove(Camp camp) {
        all.remove(camp);
    }

    public Iterator<Camp> iterator() {
        return all.iterator();
    }

    public List<Camp> findByFilter(IFilter filter) {
        return null;
    }

    public boolean contains(Camp camp) {
        return all.contains(camp);
    }

    public boolean clear() {
        all.clear();
    }

    public Camp get(int id) {
        return all.stream()
                .filter(camp -> camp.getId() == id)
                .findFirst()
                .orElse(null); // Returns null if Camp is not found
    }

    public List<Camp> getAllCamps() {
        return all;
    }
}
