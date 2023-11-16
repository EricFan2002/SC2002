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
        System.out.println(userRepository.getAll().size());
        System.out.println(userRepository.getAll().filterByID("1").get(0).getName());

//        RepositoryCollection.setCampRepository(campRepository);
//        RepositoryCollection.setUserRepository(userRepository);
//        RepositoryCollection.setEnquiryRepository(enquiryRepository);
//        RepositoryCollection.setSuggestionRepository(suggestionRepository);

        // instantiate users
        Student att1 = new Student("1", "John Doe", "NBS");
        Student att2 = new Student("2", "Jane Doe", "EEE");
        Student att3 = new Student("3", "John Smith", "MAE");
        Student com = new Student("4", "Alan Walker", "SPMS");
        Staff staff = new Staff("5", "John Cena", "SPMS");

        // add users to repository
        UserList userList = userRepository.getAll();
        userList.add(att1);
        userList.add(att2);
        userRepository.insert(att3);
        userRepository.insert(com);
        userRepository.insert(staff);

        // instantiate camp
        Camp camp1 = new Camp("abc", "Camp Nou", "Soccer camp for young students!", true, new Date(1), new Date(1),
                new Date(1), "1", "1", staff, 12);
        Camp camp2 = new Camp("cde", "Old Trafford", "Summer camp soccer training", true, new Date(1), new Date(1),
                new Date(1), "1", "1", staff, 15);

        campRepository.insert(camp1);
        campRepository.insert(camp2);
        camp1.addAttendee(att3);
        camp1.addAttendee(att2);
        camp2.addAttendee(att1);
        camp1.addCommittee(com);
        camp2.addCommittee(com);

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

        // RepositoryCollection.save();

    }
}
