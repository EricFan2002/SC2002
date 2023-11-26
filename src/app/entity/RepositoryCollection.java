package app.entity;

import java.util.ArrayList;

import app.controller.deserializer.CampDeserializer;
import app.controller.deserializer.EnquiryDeserializer;
import app.controller.deserializer.SuggestionDeserializer;
import app.controller.deserializer.UserDeserializer;
import app.entity.camp.CampList;
import app.entity.enquiry.EnquiryList;
import app.entity.suggestion.SuggestionList;
import app.entity.user.UserList;
import app.utils.CSV;
import app.consts.Config;

/**
 * The {@code RepositoryCollection} class provides a central collection of
 * repositories for users, camps, enquiries, and suggestions.
 * It handles loading and saving data to CSV files using deserializers and CSV
 * utilities.
 */
public class RepositoryCollection {

    /**
     * The repository for user objects.
     */
    private static UserList userRepository;

    /**
     * The repository for camp objects.
     */
    private static CampList campRepository;

    /**
     * The repository for enquiry objects.
     */
    private static EnquiryList enquiryRepository;

    /**
     * The repository for suggestion objects.
     */
    private static SuggestionList suggestionRepository;

    /**
     * Private constructor to prevent instantiation.
     */
    private RepositoryCollection() {
    }

    /**
     * Loads data from CSV files into the repositories using deserializers.
     */
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

    /**
     * Saves data from the repositories to CSV files.
     */
    public static void save() {
        CSV.exportToCSV(Config.USER_REPOSITORY_PATH, userRepository);
        CSV.exportToCSV(Config.CAMP_REPOSITORY_PATH, campRepository);
        CSV.exportToCSV(Config.ENQUIRY_REPOSITORY_PATH, enquiryRepository);
        CSV.exportToCSV(Config.SUGGESTION_REPOSITORY_PATH, suggestionRepository);
    }

    /**
     * Gets the repository for user objects.
     *
     * @return The user repository.
     */
    public static UserList getUserRepository() {
        return userRepository;
    }

    /**
     * Gets the repository for camp objects.
     *
     * @return The camp repository.
     */
    public static CampList getCampRepository() {
        return campRepository;
    }

    /**
     * Gets the repository for enquiry objects.
     *
     * @return The enquiry repository.
     */
    public static EnquiryList getEnquiryRepository() {
        return enquiryRepository;
    }

    /**
     * Gets the repository for suggestion objects.
     *
     * @return The suggestion repository.
     */
    public static SuggestionList getSuggestionRepository() {
        return suggestionRepository;
    }
}
