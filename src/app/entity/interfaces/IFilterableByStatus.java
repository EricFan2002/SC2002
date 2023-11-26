package app.entity.interfaces;

import app.entity.RepositoryList;

/**
 * Interface for filtering a list of items by status.
 * @param <T> The type of the items to filter.
 * @param <K> The type of the status to filter by.
 */
public interface IFilterableByStatus<T extends ITaggedItem, K> {
    /**
     * Filters the list of items by status.
     * @param status The status to filter by.
     * @return A new list of items filtered by status.
     */
    public RepositoryList<T> filterByStatus(K status);
}
