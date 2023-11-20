package entity.interfaces;

import entity.RepositoryList;

public interface ISortableByLocation<T extends ITaggedItem> {
    public RepositoryList<T> sortByLocation();
}
