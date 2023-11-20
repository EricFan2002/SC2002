package entity.interfaces;

import entity.RepositoryList;

/**
 * Interface for sorting items by name.
 * @param <T> The type of the items to be sorted.
 */
public interface ISortableByName<T extends ITaggedItem> {
    /**
     * Sorts the items by name.
     * @return A new RepositoryList with the sorted items.
     */
    public RepositoryList<T> sortByName();
}
