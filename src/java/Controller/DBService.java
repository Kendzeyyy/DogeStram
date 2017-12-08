
 
 
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
 

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Produces;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import model.Comments;
import model.Users;
import model.Photos;
 
/**
 * REST Web Service
 */


@Path("service")
public class DBService {
    
 
    @EJB
    private DBControl dbc;
    
    private NewCookie cookie;
 
    /**
     * Creates a new instance of DBService
     */
    public DBService() {
    }
 
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public List<Users> getJson() {
                   
        return dbc.getAll();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getcomments")

    public List<Comments> getComments() {
                   
        return dbc.getCOmments();

    }
 
   /* @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Users post(@FormParam("username") String name, @FormParam("password") String pass) {
        
        Users u = new Users();
        
       
            u.setName(name);
            u.setPasswd(pass);
            
        return dbc.insert(u);
        
    }*/
     @POST
    //@Produces(MediaType.APPLICATION_JSON)
        @Path("login")
     public Response login(@FormParam("username") String name, @FormParam("password") String pass) {
        
                Users u = new Users();
                
                List<Users> nameAndPassList = dbc.findNameAndPass(name, pass);
                
                
            if (nameAndPassList.isEmpty()) {
                
               return null;
                
                
            }else {
           
            Users users = nameAndPassList.get(0);
            
            int id = users.getId();
            
            NewCookie cookieNew = new NewCookie("cookieNew", "newCookie"+id);
            
            return Response.ok("OK").cookie(cookie).build();
            
            
            
            }
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
        @Path("signup")
     public Users signup(@FormParam("username") String name, @FormParam("password") String pass, @FormParam("password2") String pass2) {
                
                         Users u = new Users();

        List<Users> nameList = dbc.findUser(name);
        
        if (nameList.isEmpty()) {
            
            if(pass.equals(pass2)) {
            u.setName(name);
            u.setPasswd(pass);
            
            return dbc.insert(u);
            
            
            }
            
            
     } 
        return null;
                
    }
     
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("random") 
     public List<Photos> randomPhotos() {
         
        
         
         if (cookie == null) {
             
        return null;
    } else {
             
       List<Photos> photos = dbc.getAllPhotos();
         //Collections.shuffle(photos);         //Collections.shuffle(photos);
         
         return photos;
    }

         
     }
     
      @POST
    @Produces(MediaType.APPLICATION_JSON)
        @Path("addcomment")
           public Comments addComment(@FormParam("comments") String comm) {

               Comments c = new Comments();
          
               
               
               c.setComment(comm);
               
               return dbc.insertComm(c);
           }


         

   
    
   
    /**
     * PUT method for updating or creating an instance of DBService
     * @param content representation for the resource
     */
    //@PUT
    //@Consumes(MediaType.APPLICATION_JSON)
    //public void putJson(String content) {
    //}
}
 
