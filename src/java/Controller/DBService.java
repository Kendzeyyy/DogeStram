
 
 
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
 

import com.sun.faces.action.RequestMapping;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Produces;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Cookie;
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
    
        private java.net.URI location;
           
    
    
    Photos p = new Photos();
    Users u = new Users();

 
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
        @Produces(MediaType.TEXT_PLAIN)
               
        //@Consumes(MediaType.TEXT_PLAIN)
        @Path("login")
     public Response login(@FormParam("username") String name, @FormParam("password") String pass) throws URISyntaxException {
        
                
                List<Users> nameAndPassList = dbc.findNameAndPass(name, pass);
                
                
                
            if (nameAndPassList.isEmpty()) {
                
               return null;
                
                
            }else {
           
            Users users = nameAndPassList.get(0);
            
            int id = users.getId();
            
            
            NewCookie cookie = new NewCookie("keksi", name);
            
              Response.ResponseBuilder rb = Response.ok("keksi, "
              + "myDateCookie and myIntCookie sent to the browser");
            
            Response response = rb.cookie(cookie).build();

            
            //java.net.URI location = new java.net.URI("../index.html");
            //return response;//.temporaryRedirect(location).build();
            return Response.temporaryRedirect(new URI("../index.html")).cookie(cookie).build();

            
            
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
     
    @GET
      @Path("logout")
    public Response deleteCookie(@CookieParam("keksi") Cookie cookie) throws URISyntaxException{
        
        if (cookie != null){
            NewCookie newCookie = new NewCookie(cookie, "delete cookie", 0, false);
             
            return Response.temporaryRedirect(new URI("../index.html")).
                    cookie(newCookie)
                    .build();
            
            
            
    }

            location = new java.net.URI("../index.html");
            return Response.temporaryRedirect(location).build();
}
     
    @GET
    @Path("random") 
     public String getCookies(@CookieParam("keksi") String ck) {
         
        
         
         if (ck == null) {
             
            return "keksi on rikki = " + ck;

    } else {
             
                 return "keksi toimii ja on " + ck; 
    }
    
    
         
     }
     
      @POST
    @Produces(MediaType.APPLICATION_JSON)
        @Path("addcomment")
           public Comments addComment(@FormParam("comments") String comm) {

               Comments c = new Comments();
          
               
               u.getId();
               p.getPhotoId();
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
 
