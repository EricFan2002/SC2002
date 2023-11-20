package entity.interfaces;

import java.util.Date;

import org.apache.poi.ss.formula.functions.T;

import entity.RepositoryList;

public interface IFilterableByRegistrationDate<T extends ITaggedItem> {
    public RepositoryList<T> filterByRegistrationDate(Date currentDate);
}
