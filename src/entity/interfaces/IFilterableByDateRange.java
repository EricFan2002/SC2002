package entity.interfaces;

import java.util.Date;

import entity.RepositoryList;

public interface IFilterableByDateRange<T> {
    public RepositoryList<T> filterByDateRange(Date start, Date end);
}
