package app.entity.interfaces;

import app.entity.RepositoryList;
import app.entity.user.Student;

/**
 * The {@code IFilterableByCampCommittee} interface defines a method for filtering items by camp committee membership.
 *
 * @param <T> The type of items implementing the {@code ITaggedItem} interface.
 */
public interface IFilterableByCampCommittee<T extends ITaggedItem> {

    /**
     * Filters items based on the specified camp committee member (e.g., student).
     *
     * @param student The student representing a camp committee member whose membership is used as a filter criterion.
     * @return A {@code RepositoryList} containing items filtered by the specified camp committee member.
     */
    RepositoryList<T> filterByCampCommittee(Student student);
}

