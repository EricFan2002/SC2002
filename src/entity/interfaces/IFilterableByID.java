package entity.interfaces;

import entity.RepositoryList;

public interface IFilterableByID<T extends ITaggedItem> {
    public RepositoryList<T> filterByID(String id);
}