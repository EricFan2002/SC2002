package entity.interfaces;

import entity.RepositoryList;

/**
 * Interface for sorting items by location.
 * @param <T> The type of item to sort.
 */
public interface ISortableByLocation<T extends ITaggedItem> {
    /**
     * Sorts the items by location.
     * @return A new RepositoryList with the sorted items.
     */
    public RepositoryList<T> sortByLocation();
}
