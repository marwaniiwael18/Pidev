    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.services;

import java.util.List;

/**
 *
 * @author medom
 */
public interface IService1<T> {
    void insert(T t);
    List<T>readAll();
    boolean modifier(T t);
    void supprimer(int t);
}
