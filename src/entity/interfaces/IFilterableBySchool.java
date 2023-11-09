package entity.interfaces;

import entity.RepositoryList;

public interface IFilterableBySchool<T extends ITaggedItem> {
    public RepositoryList<T> filterBySchool(String school);
}
