import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CampList implements ICampListFilter {
    private List<Camp> camps;

    public CampList() {
        this.camps = new ArrayList<>();
    }

    public void addCamp(Camp camp) {
        camps.add(camp);
    }

    @Override
    public CampList filterByVisibility(boolean isVisible) {
        CampList filteredList = new CampList();
        Date currentDate = new Date();

        for (Camp camp : this.camps) {
            // Here, we assume the visibility is determined by whether the camp is still open for registration.
            if (camp.getRegistrationClosingDate().after(currentDate) == isVisible) {
                filteredList.addCamp(camp);
            }
        }
        return filteredList;
    }
}
