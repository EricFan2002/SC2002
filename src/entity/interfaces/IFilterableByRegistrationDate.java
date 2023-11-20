package entity.interfaces;

import java.util.Date;

import org.apache.poi.ss.formula.functions.T;

import entity.RepositoryList;

/**
 * Interface for filtering items by registration date.
 *
 * @param <T> The type of the items to filter.
 * @version 1.0
 * @since 2021-09-30
 */
public interface IFilterableByRegistrationDate<T extends ITaggedItem> {
    /**
     * Filters the items by registration date.
     *
     * @param currentDate The date to filter by.
     * @return A list of items that were registered on the given date.
     */
    public RepositoryList<T> filterByRegistrationDate(Date currentDate);
}
