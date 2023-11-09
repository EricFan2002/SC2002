package entity.interfaces;

import entity.RepositoryList;
import entity.camp.Camp;

public interface IFilterableByCamp<T extends ITaggedItem> {
    public RepositoryList<T> filterByCamp(Camp camp);
}
