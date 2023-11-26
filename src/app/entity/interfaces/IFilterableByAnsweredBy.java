package app.entity.interfaces;

import app.entity.RepositoryList;
import app.entity.user.User;

/**
 * Interface for items that can be filtered by the user who answered them.
 *
 * @param <T> The type of the item.
 */
public interface IFilterableByAnsweredBy<T extends ITaggedItem> {
    /**
     * Filters the items by the user who answered them.
     *
     * @param answeredBy The user who answered the items.
     * @return A list of items answered by the user.
     */
    public RepositoryList<T> filterByAnsweredBy(User answeredBy);
}
