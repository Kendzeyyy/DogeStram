
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
 
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Users;
 
/**
 *
 
 */
@Stateless
public class DBControl {
   
    @PersistenceContext
    private EntityManager em;
 
    public DBControl() {
    }
    public List<Users> getAll(){
        return em.createNamedQuery("User.findAll").getResultList();
        //List<User> lst = em.createNamedQuery("User.findAll").getResultList(); return lst;
    }
    //asking user object, because we don't now that user id yet
    public Users insert (Users u){
        em.persist(u);
        return u;
    }
}