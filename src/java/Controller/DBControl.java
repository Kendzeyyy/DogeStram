
 
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
import javax.persistence.Query;
import model.Comments;
import model.Users;
import model.Photos;
 
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
        
        return em.createNamedQuery("Users.findAll").getResultList();

    }
    
     public List<Comments> getCOmments(){
        
        return em.createNamedQuery("Comments.findAll").getResultList();
        
    }
    
    public List<Users> findNameAndPass(String name, String pass) {
        
        return em.createNamedQuery("Users.findByLol")
                .setParameter("name", name)
                .setParameter("passwd", pass).getResultList();
    }
    
    
    public List<Users> findUser(String name) {
    
        return em.createNamedQuery("Users.findByName")
                .setParameter("name", name).getResultList();
    
    }
    public List<Users> findpass(String pass) {
    
        return em.createNamedQuery("Users.findByPasswd")
                .setParameter("passwd", pass).getResultList();
    
    }
    
    public List<Photos> getAllPhotos(){
        
        return em.createNamedQuery("Photos.findAll").getResultList();
        
    }
    
    
    
        //asking user object, because we don't now that user id yet
    public Users insert (Users u){
        em.persist(u);
        
        
        return u;
    }
    
    public Comments insertComm (Comments c){
        em.persist(c);
        
        
        return c;
    }
    
    
    

    void getAll(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}