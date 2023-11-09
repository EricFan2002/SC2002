package entity.interfaces;

import entity.RepositoryList;

public interface IFilterableByStatus<T extends ITaggedItem> {
    public RepositoryList<T> filterByStatus(boolean status);
}
