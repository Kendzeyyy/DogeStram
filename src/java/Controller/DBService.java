
 
 
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
 
import static java.lang.System.out;
import java.math.BigDecimal;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Produces;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import model.Users;
import model.Photos;
import model.Comments;
 
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
    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public List<Users> getJson() {
     
              
        return dbc.getAll();
    }*/
 
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
            /*
            JsonBuilderFactory factory = Json.createBuilderFactory(null);
            JsonObject value = factory.createObjectBuilder()
                    .add("id", users.getId())
                    .build();
            
            String rtstrn ="{\"status\": \"ok\",\"cookie\": \"" +
            users.getId()+"\"}";*/
            
            NewCookie cookie = new NewCookie("id",""+users.getId());
            String cs = cookie.getName();
            
            return Response.ok(users,MediaType.APPLICATION_JSON).cookie(cookie).build();
          
            
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
         
         
         List<Photos> photos = dbc.getAllPhotos();
         //Collections.shuffle(photos);
         
         return photos;
         
         
     }
     
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addcomment") 
    public JsonArray showComments(){
        String retstring = "";
        List<Comments> comments = dbc.findCommentsByPhoto(1);
        return createJsonArrayFromList(createJsonObjectList(comments));
    }
    
    private JsonArray createJsonArrayFromList(List<JsonObject> jlist) {
    JsonArrayBuilder jb = Json.createArrayBuilder();
      for (JsonObject item: jlist) {
        jb.add(item);
    }
    
    return jb.build();
    }
    
    private List<JsonObject> createJsonObjectList(List<Comments> cl){
        ArrayList<JsonObject> jl = new ArrayList();
        for (int i = 0; i< cl.size();++i){
            JsonBuilderFactory jbf = Json.createBuilderFactory(null);
            JsonObject job = jbf.createObjectBuilder().add("name", cl.get(i).getUserId().getName())
                    .add("comment", cl.get(i).getComment()).build();
            jl.add(job); 
        }
        return jl;   
    }
    
     private List<JsonObject> createJObjlistFromPhotosByDate(List<Photos> ph){
        ArrayList<JsonObject> jl = new ArrayList();
        for (int i = 0; i< ph.size();++i){
            JsonBuilderFactory jbf = Json.createBuilderFactory(null);
            JsonObject job = jbf.createObjectBuilder().add("path", ph.get(i).getPhotoLocation())
                    .add("date", ph.get(i).getDateAdded().toString()).build();
            jl.add(job); 
        }
        return jl;
     }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("photosbydate")
    public JsonArray getPhotos(){
        return createJsonArrayFromList(createJObjlistFromPhotosByDate(dbc.findPhotosOrganizedByDate(10, 0)));
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
 
