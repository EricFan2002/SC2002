package entity.interfaces;

import entity.RepositoryList;

public interface ISortableByEndDate<T extends ITaggedItem> {
    public RepositoryList<T> sortByEndDate();
}
