import java.lang.reflect.Array;
import java.util.Date;

import entity.RepositoryCollection;
import entity.user.UserList;
import entity.camp.Camp;
import entity.camp.CampDetails;
import entity.camp.CampList;
import entity.enquiry.Enquiry;
import entity.enquiry.EnquiryList;
import entity.suggestion.Suggestion;
import entity.suggestion.SuggestionList;
import entity.user.Staff;
import entity.user.Student;
import entity.user.User;

/**
 * The main class responsible for initializing the application data and entities for testing purposes.
 * It sets up user repositories, camp repositories, enquires, suggestions, and interactions among them.
 */
public class Main {
    /**
     * The main method initializes various entities and interactions for testing purposes within the application.
     * @param args The command line arguments (unused in this context).
     */
    public static void main(String[] args) {
        RepositoryCollection.load();

        UserList userRepository = RepositoryCollection.getUserRepository();
        CampList campRepository = RepositoryCollection.getCampRepository();
        EnquiryList enquiryRepository = RepositoryCollection.getEnquiryRepository();
        SuggestionList suggestionRepository = RepositoryCollection.getSuggestionRepository();

        /*
         * // instantiate users
         * Student att1 = new Student("1", "John Doe", "NBS");
         * Student att2 = new Student("2", "Jane Doe", "EEE");
         * Student att3 = new Student("3", "John Smith", "MAE");
         * Student com = new Student("4", "Alan Walker", "SPMS");
         * Staff staff = new Staff("5", "John Cena", "SPMS");
         * 
         * // add users to repository
         * userRepository.add(att1);
         * userRepository.add(att2);
         * userRepository.add(att3);
         * userRepository.add(com);
         * userRepository.add(staff);
         * 
         * // instantiate camp
         * Camp camp1 = new Camp("abc", "Camp Nou", "Soccer camp for young students!",
         * true, new Date(1), new Date(1),
         * new Date(1), "1", "1", staff, 12);
         * Camp camp2 = new Camp("cde", "Old Trafford", "Summer camp soccer training",
         * true, new Date(1), new Date(1),
         * new Date(1), "1", "1", staff, 15);
         * 
         * campRepository.add(camp1);
         * campRepository.add(camp2);
         * camp1.addAttendee(att3);
         * camp1.addAttendee(att2);
         * camp2.addAttendee(att1);
         * camp1.addCommittee(com);
         * camp2.addCommittee(com);
         * 
         * // instantiate enquiry
         * Enquiry enquiry1 = new Enquiry("1", att1, "How much is the camp fee?",
         * camp1);
         * Enquiry enquiry2 = new Enquiry("2", att2, "What is the camp schedule?",
         * camp1);
         * Enquiry enquiry3 = new Enquiry("3", att3, "What is the camp schedule?",
         * camp2);
         * 
         * // insert enquiry
         * enquiryRepository.add(enquiry1);
         * enquiryRepository.add(enquiry2);
         * enquiryRepository.add(enquiry3);
         * 
         * // instantiate suggestion
         * CampDetails suggestionPlan = camp1.createSuggestionPlan();
         * suggestionPlan.setSchool("SCSE");
         * Suggestion suggestion1 = new Suggestion(com, suggestionPlan, camp1);
         * 
         * // insert suggestion
         * suggestionRepository.add(suggestion1);
         * 
         * // prints suggestion
         */

        // RepositoryCollection.setCampRepository(campRepository);
        // RepositoryCollection.setUserRepository(userRepository);
        // RepositoryCollection.setEnquiryRepository(enquiryRepository);
        // RepositoryCollection.setSuggestionRepository(suggestionRepository);

        // instantiate users
        Student att1 = new Student("att1", "John Doe", "NBS");
        Student att2 = new Student("att2", "Jane Doe", "SCSE");
        Student att3 = new Student("att3", "John Smith", "MSE");
        Student att4 = new Student("att4", "Angela Merkel", "SCSE");
        Student att5 = new Student("att5", "Winston Churchill", "SCSE");

        Student com = new Student("com", "Alan Walker", "MSE");
        Student com2 = new Student("com2", "Justin Trudeau", "SCSE");
        Student com3 = new Student("com3", "Margaret Thatcher", "NIE");

        Staff staff = new Staff("staff", "John Cena", "SPMS");
        Staff staff2 = new Staff("staff2", "Donald J. Trump", "SSS");

        // add users to repository
        userRepository.add(att1);
        userRepository.add(att2);
        userRepository.add(att3);
        userRepository.add(att4);
        userRepository.add(att5);
        userRepository.add(com);
        userRepository.add(com2);
        userRepository.add(com3);
        userRepository.add(staff);
        userRepository.add(staff2);

        // instantiate camp
        Camp camp1 = new Camp("C001", "Soccer Match", "Night Soccer Scrimmage", true, new Date(1702734600000L),
                new Date(1702741800000L),
                new Date(1697464267000L), "NTU", "Camp Nou", staff, 12);
        Camp camp2 = new Camp("C002", "Christmas Party", "Sugar and chocolate drinks party", true,
                new Date(1703512200000L), new Date(1703523000000L),
                new Date(1707541200000L), "NTU", "SCSE Lounge", staff, 15);
        Camp camp3 = new Camp("C003", "Chinese New Year", "Official 2024 Chinese New Year Celebration", true,
                new Date(1707534000000L), new Date(1707541200000L),
                new Date(1704862800000L), "NTU", "Gaia", staff, 15);
        Camp camp4 = new Camp("C004", "1792 United States Presidential Election",
                "The second election after we won against those Brits", true,
                new Date(-5587873200000L), new Date(-5587786800000L),
                new Date(-5588132400000L), "NTU", "Washington", staff, 1500);
        Camp camp5 = new Camp("C005", "Christmas Party 0th SCSE", "Sugar and chocolate drinks party", true,
                new Date(1703512200000L), new Date(1703523000000L),
                new Date(1707541200000L), "SCSE", "AIA Canopy", staff, 50);
        Camp camp6 = new Camp("C006", "Christmas Party 1st SCSE", "Sugar and chocolate drinks party", true,
                new Date(1703512200000L), new Date(1703523000000L),
                new Date(1707541200000L), "MSE", "Lee Wee Nam Library", staff, 10);
        Camp camp7 = new Camp("C007", "Christmas Party MSE", "Sugar and chocolate drinks party", true,
                new Date(1703512200000L), new Date(1703523000000L),
                new Date(1707541200000L), "SCSE", "Lee Wee Nam Library", staff, 50);
        Camp camp8 = new Camp("C008", "Christmas Party 2nd Round SCSE", "Sugar and chocolate drinks party", true,
                new Date(1703512200000L), new Date(1703523000000L),
                new Date(1707541200000L), "NTU", "1", staff, 10);
        Camp camp9 = new Camp("C009", "Sad Empty Christmas Party", "No one is here", true,
                new Date(1703512200000L), new Date(1703523000000L),
                new Date(1707541200000L), "NTU", "1", staff, 12);
        Camp camp10 = new Camp("C010", "Christmas Party X++", "Sugar and chocolate drinks party", true,
                new Date(1703512200000L), new Date(1703523000000L),
                new Date(1707541200000L), "NTU", "1", staff, 10);

        campRepository.add(camp1);
        campRepository.add(camp2);
        campRepository.add(camp3);
        campRepository.add(camp4);
        campRepository.add(camp5);
        campRepository.add(camp6);
        campRepository.add(camp7);
        campRepository.add(camp8);
        campRepository.add(camp9);
        campRepository.add(camp10);

        camp1.addAttendee(att3);
        camp1.addAttendee(att2);
        camp2.addAttendee(att1);
        camp1.addCommittee(com);
        camp2.addCommittee(com);

        camp3.addAttendee(att4);
        camp3.addAttendee(att5);
        camp3.addCommittee(com2);

        camp4.addAttendee(att4);
        camp4.addAttendee(att5);
        camp4.addCommittee(com2);

        camp5.addAttendee(att4);
        camp5.addAttendee(att5);
        camp5.addCommittee(com2);

        camp6.addAttendee(att4);
        camp6.addAttendee(att5);
        camp6.addCommittee(com2);

        camp7.addAttendee(att3);
        camp7.addCommittee(com);

        camp8.addAttendee(att4);
        camp8.addAttendee(att5);
        camp8.addCommittee(com2);

        camp9.addAttendee(att4);
        camp9.addAttendee(att5);
        camp9.addCommittee(com2);

        camp10.addAttendee(att4);
        camp10.addAttendee(att5);
        camp10.addCommittee(com2);

        // instantiate enquiry
        Enquiry enquiry1 = new Enquiry("1", att1, "How much is the camp fee?", camp1);
        Enquiry enquiry2 = new Enquiry("2", att2, "What is the camp schedule?", camp1);
        Enquiry enquiry3 = new Enquiry("3", att3, "What is the camp schedule?", camp2);

        // insert enquiry
        enquiryRepository.add(enquiry1);
        enquiryRepository.add(enquiry2);
        enquiryRepository.add(enquiry3);

        // instantiate suggestion
        CampDetails suggestionPlan = camp1.createSuggestionPlan();

        suggestionPlan.setSchool("SCSE");
        Suggestion suggestion1 = new Suggestion("1", com, suggestionPlan, camp1);

        // insert suggestion
        System.out.println(suggestion1.getOriginalCamp().getID());
        suggestionRepository.add(suggestion1);

        // prints suggestion

        RepositoryCollection.save();

    }
}
