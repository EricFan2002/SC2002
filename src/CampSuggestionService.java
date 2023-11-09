import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class CampSuggestionService {
    private CampRepository campRepository;
    private List<Suggestion> suggestions;

    public CampSuggestionService(CampRepository campRepository) {
        this.campRepository = campRepository;
        this.suggestions = new ArrayList<>();
    }

    public void suggest(Student student, Camp camp) {
        Suggestion suggestion = new Suggestion(student, camp);
        suggestions.add(suggestion);
    }

    public List<Suggestion> getSubmittedSuggestions(int campId, Student student) {
        return suggestions.stream()
                .filter(suggestion -> suggestion.getSender().equals(student) && suggestion.getSuggestion().getId() == campId)
                .collect(Collectors.toList());
    }

    public List<Suggestion> getRespondedSuggestions(User user) {
        return suggestions.stream()
                .filter(suggestion -> suggestion.getReviewedBy() != null && suggestion.getReviewedBy().equals(user))
                .collect(Collectors.toList());
    }
}
