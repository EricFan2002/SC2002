package entity.interfaces;

import entity.RepositoryList;
import entity.user.User;

public interface IFilterableBySender<T extends ITaggedItem> {
    public RepositoryList<T> filterBySender(User user);
}