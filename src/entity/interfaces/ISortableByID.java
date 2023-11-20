package entity.interfaces;

import entity.RepositoryList;

public interface ISortableByID<T extends ITaggedItem> {
    public RepositoryList<T> sortByID();
}
