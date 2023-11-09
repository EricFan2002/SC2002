package entity;

public interface IFilterableByVisibility<T> {
    public RepositoryList<T> filterByVisibility(boolean visible);
}