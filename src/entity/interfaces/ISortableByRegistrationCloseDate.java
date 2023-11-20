package entity.interfaces;

import entity.RepositoryList;

public interface ISortableByRegistrationCloseDate<T extends ITaggedItem> {
    public RepositoryList<T> sortByRegistrationCloseDate();
}
