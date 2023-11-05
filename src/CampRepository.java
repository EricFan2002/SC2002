import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

//public class CampRepository implements ICommitteeReportGenerator {
public class CampRepository {
    private List<Camp> list;
    private Map<Student, Camp> committee;

    public CampRepository() {
        this.list = new ArrayList<>();
        this.committee = new HashMap<>();
    }

    public void insert(Camp camp) {
        list.add(camp);
    }

    public Camp get(int id) {
        return list.stream()
                .filter(camp -> camp.getId() == id)
                .findFirst()
                .orElse(null); // Returns null if Camp is not found
    }

    public List<Camp> getAllCamps() {
        return list;
    }

    public boolean update(Camp camp, int id) {
        int index = list.indexOf(get(id));
        if (index != -1) {
            list.set(index, camp);
            return true;
        }
        return false;
    }

    public boolean remove(int id) {
        return list.removeIf(camp -> camp.getId() == id);
    }

}
