package entity;

import java.util.ArrayList;

import controller.deserializer.CampDeserializer;
import controller.deserializer.EnquiryDeserializer;
import controller.deserializer.SuggestionDeserializer;
import controller.deserializer.UserDeserializer;
import entity.camp.CampList;
import entity.enquiry.EnquiryList;
import entity.suggestion.SuggestionList;
import entity.user.UserList;
import utils.CSV;
import consts.Config;

public class RepositoryCollection {
    private static UserList userRepository;
    private static CampList campRepository;
    private static EnquiryList enquiryRepository;
    private static SuggestionList suggestionRepository;

    private RepositoryCollection() {
    };

    public static void load() {
        ArrayList<ArrayList<String>> userData = CSV.importFromCSV(Config.USER_REPOSITORY_PATH);
        ArrayList<ArrayList<String>> campData = CSV.importFromCSV(Config.CAMP_REPOSITORY_PATH);
        ArrayList<ArrayList<String>> enquiryData = CSV.importFromCSV(Config.ENQUIRY_REPOSITORY_PATH);
        ArrayList<ArrayList<String>> suggestionData = CSV.importFromCSV(Config.SUGGESTION_REPOSITORY_PATH);

        userRepository = UserDeserializer.deserialize(userData);
        campRepository = CampDeserializer.deserialize(campData, userRepository);
        enquiryRepository = EnquiryDeserializer.deserialize(enquiryData, userRepository, campRepository);
        suggestionRepository = SuggestionDeserializer.deserialize(suggestionData, userRepository, campRepository);
    }

    public static void save() {
        CSV.exportToCSV(Config.USER_REPOSITORY_PATH, userRepository.serialize());
        CSV.exportToCSV(Config.CAMP_REPOSITORY_PATH, campRepository.serialize());
        CSV.exportToCSV(Config.ENQUIRY_REPOSITORY_PATH, enquiryRepository.serialize());
        CSV.exportToCSV(Config.SUGGESTION_REPOSITORY_PATH, suggestionRepository.serialize());
    }

    public static UserList getUserRepository() {
        return userRepository;
    }

    public static CampList getCampRepository() {
        return campRepository;
    }

    public static EnquiryList getEnquiryRepository() {
        return enquiryRepository;
    }

    public static SuggestionList getSuggestionRepository() {
        return suggestionRepository;
    }

}
