public class PerformanceReport{
    private Student student;
    private int point;

    public PerformanceReport(Student student, int point){
        this.student = student;
        this.point = point;
    }

    public Student getStudent(){
        return student;
    }
    public int getPoint(){
        return point;
    }
}