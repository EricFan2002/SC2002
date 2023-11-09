package entity.interfaces;

import entity.RepositoryList;

public interface IFilterableByVisibility<T> {
    public RepositoryList<T> filterByVisibility(boolean visible);
}