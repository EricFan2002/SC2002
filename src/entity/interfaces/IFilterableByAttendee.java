package entity.interfaces;

import entity.RepositoryList;
import entity.user.Student;

public interface IFilterableByAttendee<T extends ITaggedItem> {
    public RepositoryList<T> filterByAttendee(Student student);
}
