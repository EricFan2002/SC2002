package controller.deserializer;

import java.util.ArrayList;
import java.util.Date;

import entity.camp.Camp;
import entity.camp.CampList;

import entity.user.Staff;
import entity.user.Student;
import entity.user.UserList;

public class CampDeserializer {

    public static CampList deserialize(ArrayList<ArrayList<String>> data, UserList userList) {

        CampList cur = new CampList();
        data.forEach(record -> {
            // id, name, desc, visibility (0/1), startDate, endDate, closeRegDate, school,
            // location, staff, attend, committee
            String id = record.get(0);
            String name = record.get(1);
            String desc = record.get(2);
            String visibilityRaw = record.get(3);
            boolean visibility = (visibilityRaw.equals("0")) ? false : true;

            String startDateRaw = record.get(4);
            Date startDate = new Date(Long.parseLong(startDateRaw));

            String endDateRaw = record.get(5);
            Date endDate = new Date(Long.parseLong(endDateRaw));

            String closeRegDateRaw = record.get(6);
            Date closeRegDate = new Date(Long.parseLong(closeRegDateRaw));

            String school = record.get(7);
            String location = record.get(8);

            String staffIDRaw = record.get(9);
            UserList tempStaff = userList.filterByID(staffIDRaw);

            String totalSlotsRaw = record.get(13);
            int totalSlots = Integer.parseInt(totalSlotsRaw);

            Staff staff;
            if (tempStaff.size() > 0) {
                staff = (Staff) tempStaff.get(0);
            } else {
                staff = new Staff();
                // do something if not found
            }
            Camp camp = new Camp(id, name, desc, visibility, startDate, endDate, closeRegDate, school, location,
                    staff, totalSlots);

            String[] attendeeIDsRaw = record.get(10).split(";");
            for (String attendeeIDRaw : attendeeIDsRaw) {
                UserList tempUser = userList.filterByID(attendeeIDRaw);
                if (tempUser.size() > 0) {
                    camp.addAttendee((Student) tempUser.get(0));
                } else {
                    // skip
                }
            }

            String[] committeeIDsRaw = record.get(11).split(";");
            for (String committeeIDRaw : committeeIDsRaw) {
                UserList tempUser = userList.filterByID(committeeIDRaw);
                if (tempUser.size() > 0) {
                    camp.addCommittee((Student) tempUser.get(0));
                } else {
                    /// skip
                }
            }

            String[] registeredIDsRaw = record.get(12).split(";");
            for (String registeredIDRaw : registeredIDsRaw) {
                UserList tempUser = userList.filterByID(registeredIDRaw);
                if (tempUser.size() > 0) {
                    camp.addRegisteredStudent((Student) tempUser.get(0));
                } else {
                    // skip
                }
            }

            cur.add(camp);
        });

        cur.forEach(camp -> {
            camp.getAttendees().forEach(attendee -> {
                attendee.addAttendedCamp(camp);
            });
            camp.getCommittees().forEach(committee -> {
                committee.addCommitteeCamp(camp);
            });
            camp.getStaffInCharge().addOrganizedCamp(camp);
        });

        return cur;
    }
}