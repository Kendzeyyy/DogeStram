
 
 
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
 
import static java.lang.System.out;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Produces;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import model.Users;
import model.Photos;
 
/**
 * REST Web Service
 */


@Path("service")
public class DBService {
 
    @EJB
    private DBControl dbc;
 
    /**
     * Creates a new instance of DBService
     */
    public DBService() {
    }
 
    /**
     * Retrieves representation of an instance of Controller.DBService
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public List<Users> getJson() {
     
              
        return dbc.getAll();
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
    @Produces(MediaType.APPLICATION_JSON)
        @Path("login")
     public Response login(@FormParam("username") String name, @FormParam("password") String pass) {
        
                Users u = new Users();
                
                List<Users> nameAndPassList = dbc.findNameAndPass(name, pass);
                //List<Photos> photos = dbc.getAllPhotos();
                
                
            if (nameAndPassList.isEmpty()) {
                
               return null;
                
                
            }else {
                
            Users users = nameAndPassList.get(0);
            int id = users.getId();
            try{
            URI indexuri = new URI("");
            List<Photos> photos = dbc.findPhotosByUser(id);
            StringBuilder data = new StringBuilder();
            for(int i = 0; i < photos.size();i++){
                data.append("Adder of photo: ");
                Users photoadder = photos.get(i).getUserId();
                data.append(photoadder.getId());
                data.append("Photo location: ");
                data.append(photos.get(i).getPhotoLocation());
                data.append("\n");
            }
           NewCookie idcookie = new NewCookie("myIdCookie",""+id);
            Response.ResponseBuilder rb = Response.ok(data.toString());
            rb.contentLocation(indexuri);
            Response response = rb.cookie(idcookie).build();
                return response;
            }catch(Exception e){
                Response.ResponseBuilder rb = Response.status(1, e.toString());
                Response responce = rb.build();
                return responce;
            }
            
        }
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
        @Path("signup")
     public List<Photos> signup(@FormParam("username") String name, @FormParam("password") String pass, @FormParam("password2") String pass2) {
                
                         Users u = new Users();

        List<Users> nameList = dbc.findUser(name);
        
        if (nameList.isEmpty()) {
            
            if(pass.equals(pass2)) {
            u.setName(name);
            u.setPasswd(pass);
            
            return dbc.findPhotosOrganizedByDate(3, 0);
            
            
            }
            
            
     } 
        return null;
                
    }
     
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("random") 
     public List<Photos> randomPhotos() {
         
         
         List<Photos> photos = dbc.getAllPhotos();
         //Collections.shuffle(photos);
         
         return photos;
         
         
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
 
