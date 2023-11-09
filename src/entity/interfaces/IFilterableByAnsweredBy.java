package entity.interfaces;

import entity.RepositoryList;
import entity.user.User;

public interface IFilterableByAnsweredBy<T extends ITaggedItem> {
    public RepositoryList<T> filterByAnsweredBy(User answeredBy);
}
