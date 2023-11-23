package entity.interfaces;

import entity.RepositoryList;
import entity.user.Student;

public interface IFilterableByCampCommittee<T extends ITaggedItem> {
    public RepositoryList<T> filterByCampCommittee(Student student);
}
