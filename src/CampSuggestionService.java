//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//import entity.user.Student;
//import entity.user.User;
//import entity.CampRepository;
//
//import java.util.ArrayList;
//
//public class CampSuggestionService {
//    private CampRepository campRepository;
//    private List<Suggestion> suggestions;
//
//    public CampSuggestionService(CampRepository campRepository) {
//        this.campRepository = campRepository;
//        this.suggestions = new ArrayList<>();
//    }
//
//    public void suggest(Student student, Camp camp) {
//        Suggestion suggestion = new Suggestion(student, camp);
//        suggestions.add(suggestion);
//    }
//
//    public List<Suggestion> getSubmittedSuggestions(String campId, Student student) {
//        return suggestions.stream()
//                .filter(suggestion -> suggestion.getSender().equals(student)
//                        && Objects.equals(suggestion.getSuggestion().getId(), campId))
//                .collect(Collectors.toList());
//    }
//
//    public List<Suggestion> getRespondedSuggestions(User user) {
//        return suggestions.stream()
//                .filter(suggestion -> suggestion.getReviewedBy() != null && suggestion.getReviewedBy().equals(user))
//                .collect(Collectors.toList());
//    }
//}
