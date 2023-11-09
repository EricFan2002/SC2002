
/*class 1)inserts camp into camp repo, 
        2)finds a camp from id 
        3)gets all camps
        4)updates camp
        5)removes camp
*/
import java.util.*;

import entity.Repository;
import entity.user.Student;

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
        return all.add(camp);
    }

    public boolean update(Camp camp) {
        if (!all.contains(camp)) {
            return false;
        }
        all.set(all.indexOf(camp), camp);
        return true;
    }

    public boolean updateAll(List<Camp> all) {
        if (all == null) {
            return false;
        }
        this.all = all;
        return true;
    }

    public boolean remove(Camp camp) {
        if (!all.contains(camp)) {
            return false;
        }
        all.remove(camp);
        return true;
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

    public Camp get(String id) {
        return all.stream()
                .filter(camp -> camp.getId().equals(id))
                .findFirst()
                .orElse(null); // Returns null if Camp is not found
    }

    public List<Camp> getAllCamps() {
        return all;
    }
}
