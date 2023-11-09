package entity.user;

public class Student extends User {
    protected int points;

    public Student(String ID, String name, String faculty) {
        super(ID, name, faculty);
        points = 0;
    }

    public Student() {
        super();
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
