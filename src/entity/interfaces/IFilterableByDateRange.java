package entity.interfaces;

import java.util.Date;

import entity.RepositoryList;

/**
 * Interface for filtering a list of items by date range.
 * @param <T> The type of item to filter.
 */
public interface IFilterableByDateRange<T extends ITaggedItem> {
    /**
     * Filters the list by date range.
     * @param start The start date.
     * @param end The end date.
     * @return A new list containing the filtered items.
     */
    public RepositoryList<T> filterByDateRange(Date start, Date end);
}
