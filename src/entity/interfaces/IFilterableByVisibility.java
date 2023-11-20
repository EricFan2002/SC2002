package entity.interfaces;

import entity.RepositoryList;

/**
 * Interface for filtering a list of items by visibility.
 * @param <T> The type of the items to filter.
 */
public interface IFilterableByVisibility<T extends ITaggedItem> {
    /**
     * Filters the list of items by visibility.
     * @param visible The visibility to filter by.
     * @return A new list of items filtered by visibility.
     */
    public RepositoryList<T> filterByVisibility(boolean visible);
}