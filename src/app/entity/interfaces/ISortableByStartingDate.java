package app.entity.interfaces;

import app.entity.RepositoryList;

/**
 * Interface for sorting items by starting date.
 * @param <T> The type of the items to be sorted.
 */
public interface ISortableByStartingDate<T extends ITaggedItem> {
    /**
     * Sorts the items by starting date.
     * @return A new RepositoryList with the sorted items.
     */
    public RepositoryList<T> sortByStartingDate();
}
