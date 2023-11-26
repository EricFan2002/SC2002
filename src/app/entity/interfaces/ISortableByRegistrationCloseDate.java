package app.entity.interfaces;

import app.entity.RepositoryList;

/**
 * Interface for filtering a list of items by ID.
 * @param <T> The type of item to filter.
 */
public interface ISortableByRegistrationCloseDate<T extends ITaggedItem> {
    /**
     * Sorts the list by registration close date.
     * @return A new list sorted by registration close date.
     */
    public RepositoryList<T> sortByRegistrationCloseDate();
}
