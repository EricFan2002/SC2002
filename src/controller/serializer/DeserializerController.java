package controller.serializer;

import java.util.ArrayList;
import java.util.Date;

import entity.camp.Camp;
import entity.camp.CampDetails;
import entity.camp.CampList;
import entity.enquiry.Enquiry;
import entity.enquiry.EnquiryList;

import entity.user.Student;
import entity.user.User;
import entity.user.UserFactory;
import entity.user.UserList;

public class DeserializerController {
    public static UserList userDeserializer(String filePath) {
        ArrayList<ArrayList<String>> data = utils.CSV.importFromCSV(filePath);

        UserList cur = new UserList();
        data.forEach(record -> {
            // id, name, password, faculty, type, points
            String id = record.get(0);
            String name = record.get(1);
            String password = record.get(2);
            String faculty = record.get(3);
            int type = Integer.parseInt(record.get(4));

            String typeName = (type == 1) ? "Staff" : "Student";

            String pointsRaw = record.get(5);

            User user = UserFactory.getUser(typeName, id, name, faculty);
            if (user instanceof Student) {
                ((Student) user).setPoints(Integer.parseInt(pointsRaw));
            }

            if (user != null) {
                user.setPassword(password);
                cur.add(user);
                // add to UserList which extends Repository List
            }

            cur.add(user);
        });

        return cur;

    }

    public static EnquiryList enquiryDeserializer(String filePath, UserList userList, CampList campList) {
        ArrayList<ArrayList<String>> data = utils.CSV.importFromCSV(filePath);

        EnquiryList cur = new EnquiryList();

        data.forEach(record -> {
            // id, senderID, question, answer, campID, answeredByID

            String id = record.get(0);
            String senderID = record.get(1);
            UserList senderTmp = userList.filterByID(senderID);
            Student sender = null;
            if (senderTmp.size() > 0 && senderTmp.get(0) instanceof Student) {
                sender = (Student) senderTmp.get(0);
            }

            String question = record.get(2);
            String answer = record.get(3);

            String campID = record.get(4);
            CampList campTmp = campList.filterByID(campID);
            Camp camp = null;
            if (campTmp.size() > 0) {
                camp = campTmp.get(0);
            }

            String answeredByID = record.get(5);
            UserList answeredByTmp = userList.filterByID(answeredByID);
            User answeredBy = null;
            if (answeredByTmp.size() > 0) {
                answeredBy = answeredByTmp.get(0);
            }

            Enquiry enquiry = new Enquiry(id, sender, question, answer, camp, answeredBy);

            cur.add(enquiry);
        });

        // create connection to user and camp repository
        cur.forEach(enquiry -> {
            if (enquiry.getCamp() != null)
                enquiry.getCamp().addEnquiry(enquiry);
            if (enquiry.getSender() != null)
                enquiry.getSender().addEnquiry(enquiry);
        });

        return cur;
    }

}
