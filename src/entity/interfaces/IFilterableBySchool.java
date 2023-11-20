package entity.interfaces;

import entity.RepositoryList;

/**
 * Interface for filtering items by school.
 *
 * @param <T> The type of the items to filter.
 * @version 1.0
 * @since 2021-09-30
 */
public interface IFilterableBySchool<T extends ITaggedItem> {
    /**
     * Filters the items by school.
     *
     * @param school The school to filter by.
     * @return A list of items that were registered on the given school.
     */
    public RepositoryList<T> filterBySchool(String school);
}
