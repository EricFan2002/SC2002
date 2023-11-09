package entity.interfaces;

import entity.RepositoryList;

public interface IFilterableBySchool<T> {
    public RepositoryList<T> filterBySchool(String school);
}
