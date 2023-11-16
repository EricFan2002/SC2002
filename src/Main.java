import java.lang.reflect.Array;
import java.util.Date;

import entity.camp.CampRepository;
import entity.enquiry.EnquiryRepository;
import entity.RepositoryCollection;
import entity.suggestion.SuggestionRepository;
import entity.user.UserList;
import entity.user.UserRepository;
import entity.camp.Camp;
import entity.camp.CampDetails;
import entity.enquiry.Enquiry;
import entity.suggestion.Suggestion;
import entity.user.Staff;
import entity.user.Student;
import entity.user.User;

public class Main {
    public static void main(String[] args) {
        RepositoryCollection.load();

        UserRepository userRepository = RepositoryCollection.userRepository;
        CampRepository campRepository = RepositoryCollection.campRepository;
        EnquiryRepository enquiryRepository = RepositoryCollection.enquiryRepository;
        SuggestionRepository suggestionRepository = RepositoryCollection.suggestionRepository;

        /*
         * // instantiate users
         * Student att1 = new Student("1", "John Doe", "NBS");
         * Student att2 = new Student("2", "Jane Doe", "EEE");
         * Student att3 = new Student("3", "John Smith", "MAE");
         * Student com = new Student("4", "Alan Walker", "SPMS");
         * Staff staff = new Staff("5", "John Cena", "SPMS");
         * 
         * // add users to repository
         * userRepository.insert(att1);
         * userRepository.insert(att2);
         * userRepository.insert(att3);
         * userRepository.insert(com);
         * userRepository.insert(staff);
         * 
         * // instantiate camp
         * Camp camp1 = new Camp("abc", "Camp Nou", "Soccer camp for young students!",
         * true, new Date(1), new Date(1),
         * new Date(1), "1", "1", staff, 12);
         * Camp camp2 = new Camp("cde", "Old Trafford", "Summer camp soccer training",
         * true, new Date(1), new Date(1),
         * new Date(1), "1", "1", staff, 15);
         * 
         * campRepository.insert(camp1);
         * campRepository.insert(camp2);
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
         * enquiryRepository.insert(enquiry1);
         * enquiryRepository.insert(enquiry2);
         * enquiryRepository.insert(enquiry3);
         * 
         * // instantiate suggestion
         * CampDetails suggestionPlan = camp1.createSuggestionPlan();
         * suggestionPlan.setSchool("SCSE");
         * Suggestion suggestion1 = new Suggestion(com, suggestionPlan, camp1);
         * 
         * // insert suggestion
         * suggestionRepository.insert(suggestion1);
         * 
         * // prints suggestion
         */

        // RepositoryCollection.setCampRepository(campRepository);
        // RepositoryCollection.setUserRepository(userRepository);
        // RepositoryCollection.setEnquiryRepository(enquiryRepository);
        // RepositoryCollection.setSuggestionRepository(suggestionRepository);

        // instantiate users
        Student att1 = new Student("1", "John Doe", "NBS");
        Student att2 = new Student("2", "Jane Doe", "SCSE");
        Student att3 = new Student("3", "John Smith", "MSE");
        Student att4 = new Student("4", "Angela Merkel", "SCSE");
        Student att5 = new Student("5", "Winston Churchill", "SCSE");

        Student com = new Student("6", "Alan Walker", "MSE");
        Student com2 = new Student("7", "Justin Trudeau", "SCSE");

        Staff staff = new Staff("8", "John Cena", "SPMS");
        Staff staff2 = new Staff("9", "Donald J. Trump", "SSS");

        // add users to repository
        UserList userList = userRepository.getAll();
        userList.add(att1);
        userList.add(att2);
        userRepository.insert(att3);
        userRepository.insert(com);
        userRepository.insert(staff);
        userRepository.insert(staff2);

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

        campRepository.insert(camp1);
        campRepository.insert(camp2);
        campRepository.insert(camp3);
        campRepository.insert(camp4);
        campRepository.insert(camp5);
        campRepository.insert(camp6);
        campRepository.insert(camp7);
        campRepository.insert(camp8);
        campRepository.insert(camp9);
        campRepository.insert(camp10);

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
        enquiryRepository.insert(enquiry1);
        enquiryRepository.insert(enquiry2);
        enquiryRepository.insert(enquiry3);

        // instantiate suggestion
        CampDetails suggestionPlan = camp1.createSuggestionPlan();
        suggestionPlan.setSchool("SCSE");
        Suggestion suggestion1 = new Suggestion(com, suggestionPlan, camp1);

        // insert suggestion
        suggestionRepository.insert(suggestion1);

        // prints suggestion
        System.out.println(suggestionRepository.getAll().get(0).getSuggestion().getSchool());

        RepositoryCollection.save();

    }
}
