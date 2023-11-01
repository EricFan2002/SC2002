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

    public void update(Camp camp, int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.set(i, camp);
                return;
            }
        }
    }

    public void remove(int id) {
        list.removeIf(camp -> camp.getId() == id);
    }

}
