package entity.interfaces;

import entity.RepositoryList;

public interface ISortableByStartingDate<T extends ITaggedItem> {
    public RepositoryList<T> sortByStartingDate();
}
