/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trenna.services;

import java.util.List;


/**
 *
 * @author moham
 */
public interface IService<T> {
     public void ajouter(T t);

     public List<T> afficher();

     public Boolean update(T t);

     public Boolean supprimer(T t); 
     
     
     
    
     
      

}
