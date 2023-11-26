package app.entity.interfaces;

import app.entity.RepositoryList;

/**
 * Interface for sorting a RepositoryList by ID.
 * @param <T> The type of the RepositoryList.
 */
public interface ISortableByID<T extends ITaggedItem> {
    /**
     * Sorts the RepositoryList by ID.
     * @return The sorted RepositoryList.
     */
    public RepositoryList<T> sortByID();
}
