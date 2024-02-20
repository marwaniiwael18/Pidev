/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.services;
import trenna.entities.Match;
import java.util.List;

/**
 *
 * @author Amirov
 */
public interface IService_1<T> {
    public void ajouter(T m);
     public List<T> afficher();
     public void modifer(T m);
    public void supprimer(T m);
     public List<Match> sortByDate();
         
     
}
