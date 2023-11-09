package entity;

public interface IFilterableByID<T> {
    public RepositoryList<T> filterByID(String id);
}