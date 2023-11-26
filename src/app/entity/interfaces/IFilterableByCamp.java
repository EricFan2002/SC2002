package app.entity.interfaces;

import app.entity.RepositoryList;
import app.entity.camp.Camp;

/**
 * Interface for items that can be filtered by camp.
 *
 * @param <T> The type of the item.
 */
public interface IFilterableByCamp<T extends ITaggedItem> {
    /**
     * Filters the items by camp.
     *
     * @param camp The camp of the items.
     * @return A list of items in the camp.
     */
    public RepositoryList<T> filterByCamp(Camp camp);
}
