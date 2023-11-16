package entity;

import entity.camp.CampRepository;
import entity.enquiry.EnquiryRepository;
import entity.suggestion.SuggestionRepository;
import entity.user.UserRepository;

public class RepositoryCollection {
    public static final UserRepository userRepository = new UserRepository("data/user.csv");
    public static final CampRepository campRepository = new CampRepository("data/camp.csv", userRepository);
    public static final EnquiryRepository enquiryRepository = new EnquiryRepository("data/enquiry.csv", userRepository,
            campRepository);
    public static final SuggestionRepository suggestionRepository = new SuggestionRepository("data/suggestion.csv",
            userRepository, campRepository);

    public static void load() {
        userRepository.load();
        campRepository.load();
        enquiryRepository.load();
        suggestionRepository.load();
    }

    public static void save() {
        userRepository.save();
        campRepository.save();
        enquiryRepository.save();
        suggestionRepository.save();
    }

}
