package entity.interfaces;

import entity.RepositoryList;

public interface IFilterableByVisibility<T extends ITaggedItem> {
    public RepositoryList<T> filterByVisibility(boolean visible);
}