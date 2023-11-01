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
}
