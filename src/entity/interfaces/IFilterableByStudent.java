package entity.interfaces;

import entity.RepositoryList;
import entity.user.Student;

public interface IFilterableByStudent<T extends ITaggedItem> {
    public RepositoryList<T> filterByStudent(Student student);
}
