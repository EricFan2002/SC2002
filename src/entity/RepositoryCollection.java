package entity;

import entity.user.User;

public class RepositoryCollection {
    private static CampRepository campRepository;
    private static UserRepository userRepository;
    private static EnquiryRepository enquiryRepository;
    private static SuggestionRepository suggestionRepository;

    public static CampRepository getCampRepository() {
        return campRepository;
    }

    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public static EnquiryRepository getEnquiryRepository() {
        return enquiryRepository;
    }

    public static SuggestionRepository getSuggestionRepository() {
        return suggestionRepository;
    }

    public static void setCampRepository(CampRepository campRepository) {
        RepositoryCollection.campRepository = campRepository;
    }

    public static void setUserRepository(UserRepository userRepository) {
        RepositoryCollection.userRepository = userRepository;
    }

    public static void setEnquiryRepository(EnquiryRepository enquiryRepository) {
        RepositoryCollection.enquiryRepository = enquiryRepository;
    }

    public static void setSuggestionRepository(SuggestionRepository suggestionRepository) {
        RepositoryCollection.suggestionRepository = suggestionRepository;
    }
}
