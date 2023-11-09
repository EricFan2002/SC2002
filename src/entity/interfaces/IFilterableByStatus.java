package entity.interfaces;

import entity.RepositoryList;

public interface IFilterableByStatus<T extends ITaggedItem, K> {
    public RepositoryList<T> filterByStatus(K status);
}
