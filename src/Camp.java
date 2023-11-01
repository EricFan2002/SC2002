import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Camp {
    private int id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private Date registrationClosingDate;
    private String group;
    private String location;
    private Staff staffInCharge;
    private int attendeeSlots;
    private int committeeSlots;
    private Set<Student> attendees;
    private Set<Student> committees;

    public Camp(int id, String name, String description, Date startDate, Date endDate, Date registrationClosingDate, String group, String location, Staff staffInCharge, int attendeeSlots) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationClosingDate = registrationClosingDate;
        this.group = group;
        this.location = location;
        this.staffInCharge = staffInCharge;
        this.attendeeSlots = attendeeSlots;
        this.committeeSlots = 0; // Initialize to 0
        this.attendees = new HashSet<>();
        this.committees = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public int getAttendeeSlots() {
        return attendeeSlots;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setRegistrationCloseDate(Date registrationClosingDate) {
        this.registrationClosingDate = registrationClosingDate;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAttendeeSlots(int attendeeSlots) {
        this.attendeeSlots = attendeeSlots;
    }

    public void setCommitteeSlots(int committeeSlots) {
        if (committeeSlots > 10) {
            throw new IllegalArgumentException("Committee slots cannot exceed 10.");
        }
        this.committeeSlots = committeeSlots;
    }

    // Methods to add or remove attendees/committees
    public boolean addAttendee(Student student) {
        if (attendees.size() < attendeeSlots) {
            attendees.add(student);
            return true;
        } else {
            System.out.println("Attendee slots are full.");
            return false;
        }
    }

    public boolean removeAttendee(Student student) {
        if(attendees.contains(student)) {
            attendees.remove(student);
            return true;
        }
        return false;
    }

    public boolean addCommittee(Student student) {
        if (committees.size() < committeeSlots) {
            committees.add(student);
            return true;
        } else {
            System.out.println("Committee slots are full.");
            return false;
        }
    }

}
