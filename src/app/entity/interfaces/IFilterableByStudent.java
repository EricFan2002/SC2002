package app.entity.interfaces;

import app.entity.RepositoryList;
import app.entity.user.Student;

/**
 * The {@code IFilterableByStudent} interface defines a method for filtering items by student.
 *
 * @param <T> The type of items implementing the {@code ITaggedItem} interface.
 */
public interface IFilterableByStudent<T extends ITaggedItem> {

    /**
     * Filters items based on the specified student.
     *
     * @param student The student whose information is used as a filter criterion.
     * @return A {@code RepositoryList} containing items filtered by the specified student.
     */
    RepositoryList<T> filterByStudent(Student student);
}

