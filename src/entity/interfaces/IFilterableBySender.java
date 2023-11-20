package entity.interfaces;

import entity.RepositoryList;
import entity.user.User;

/**
 * Interface for filtering items by sender.
 * @param <T> The type of the items to filter.
 */
public interface IFilterableBySender<T extends ITaggedItem> {
    /**
     * Filters the items by sender.
     * @param user The sender to filter by.
     * @return A RepositoryList of the filtered items.
     */
    public RepositoryList<T> filterBySender(User user);
}