package Modelo.Interface;

import java.util.List;

public interface CRUD<T> {
    public List listar();
    public int add(T t);
    public int actualizar(T t);
    public void eliminar(int id);
}