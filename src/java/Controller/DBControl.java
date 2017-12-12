
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Users;
import model.Photos;
import model.Comments;
import model.Likes;
 
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
        //List<User> lst = em.createNamedQuery("User.findAll").getResultList(); return lst;
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
    
    public List<Photos> findPhotosByUser(int id){
        List<Photos> photos = em.createNamedQuery("Photos.findAll").getResultList();
        ArrayList<Photos> photosbyuser = new ArrayList();
        for(int i = 0; i < photos.size();++i){
            if(((int)photos.get(i).getUserId().getId()) == id){
                photosbyuser.add(photos.get(i));
            }
        }
        return photosbyuser;
    }
    
    public List<Photos> findPhotosOrganizedByDate(int amount, int startingIndex){
        return em.createNamedQuery("Photos.orderedByDateAdded")
                .setFirstResult(startingIndex).setMaxResults(amount).getResultList();
    }
    
    public List<Comments> findCommentsByPhoto(int id){
        
        List<Comments> comments = em.createNamedQuery("Comments.findAll").getResultList();
        ArrayList<Comments> commentsbyphoto = new ArrayList();
        for(int i = 0; i < comments.size(); ++i){
            //Comments.getPhotoId returns Photos-instance, so to get Integer out of
            //it one has to call method getPhotoId for Photos-instance
            if(((int)comments.get(i).getPhotoId().getPhotoId() == id)){
                commentsbyphoto.add(comments.get(i));
            }
        }
        return commentsbyphoto;
    }
    
    public Comments addCommentToDatabase(int userid, int photoid, Date dateadded, String comment){
        Users u = new Users();
        u.setId(userid);
        Photos p = new Photos();
        p.setPhotoId(photoid);
        Comments c = new Comments();
        c.setUserId(u);
        c.setPhotoId(p);
        c.setComment(comment);
        c.setDateAdded(dateadded);
        this.insert(c);
        return c; 
    }
    
        //asking user object, because we don't now that user id yet
    public Users insert (Users u){
        em.persist(u);
        
        
        return u;
    }
    
    public Comments insert(Comments c){
        
        em.persist(c);
        
        return c;
    }
    
    
    

    void getAll(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}