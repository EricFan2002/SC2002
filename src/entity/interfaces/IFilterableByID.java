package entity.interfaces;

import entity.RepositoryList;

public interface IFilterableByID<T> {
    public RepositoryList<T> filterByID(String id);
}