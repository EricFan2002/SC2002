package entity.interfaces;

import entity.RepositoryList;

public interface ISortableByName<T extends ITaggedItem> {
    public RepositoryList<T> sortByName();
}
