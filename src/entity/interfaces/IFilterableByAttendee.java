package entity.interfaces;

import entity.RepositoryList;
import entity.user.Student;

/**
 * The {@code IFilterableByAttendee} interface defines a method for filtering items by attendee (e.g., student).
 *
 * @param <T> The type of items implementing the {@code ITaggedItem} interface.
 */
public interface IFilterableByAttendee<T extends ITaggedItem> {

    /**
     * Filters items based on the specified attendee (e.g., student).
     *
     * @param student The student whose attendance is used as a filter criterion.
     * @return A {@code RepositoryList} containing items filtered by the specified attendee.
     */
    RepositoryList<T> filterByAttendee(Student student);
}
