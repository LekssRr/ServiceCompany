package ru.warrantyauto.repository;

import java.util.List;

public interface Repository <Entity, Key>{
    default boolean create(Entity entity){
        return false;
    };
    default boolean update(Entity entity)
    {
        return false;
    }
    default boolean delete(Entity entity)
    {
        return false;
    }
    default String get(Key key)
    {
        return null;
    }
    default List<String> getAll()
    {
        return null;
    }



}
