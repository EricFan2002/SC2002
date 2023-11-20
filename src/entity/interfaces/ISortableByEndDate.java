package entity.interfaces;

import entity.RepositoryList;

/**
 * Interface for sorting by end date.
 * @param <T> The type of the items to be sorted.
 */
public interface ISortableByEndDate<T extends ITaggedItem> {
    /**
     * Sorts the items by end date.
     * @return A RepositoryList of the sorted items.
     */
    public RepositoryList<T> sortByEndDate();
}
