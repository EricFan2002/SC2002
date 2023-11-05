public class CommitteeStudent extends Student {
    private PerformanceReport performanceReport;

    public CommitteeStudent(String ID, String name, String faculty) {
        super(ID, name, faculty);
    }

    public PerformanceReport getPerformanceReport() {
        return performanceReport;
    }

    public void setPerformanceReport(PerformanceReport performanceReport) {
        this.performanceReport = performanceReport;
    }

    
    public void printMenu(){
        System.out.println("1. View Students' Enquiries");
        System.out.println("2. Reply Students' Enquiries");
        System.out.println("3. View My Suggestions");
        System.out.println("4. Submit Camp Suggestions");
        System.out.println("5. Edit My Suggestions");
        System.out.println("6. Delete My Suggestions");
        System.out.println("7. Generate Attendance Report");//filter by attendee, committee, all, staff
        System.out.println("8. View Points");
    }

}
