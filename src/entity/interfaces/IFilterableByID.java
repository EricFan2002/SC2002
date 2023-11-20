package entity.interfaces;

import entity.RepositoryList;

/**
 * Interface for filtering a list of items by ID.
 * @param <T> The type of item to filter.
 */
public interface IFilterableByID<T extends ITaggedItem> {
    /**
     * Filters the list of items by ID.
     * @param id The ID to filter by.
     * @return A new list of items that match the ID.
     */
    public RepositoryList<T> filterByID(String id);
}