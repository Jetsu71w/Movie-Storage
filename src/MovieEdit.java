import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MovieEdit {
    private Connection conn = null;
     public MovieEdit() throws Exception{
        
         try {
            String url = "jdbc:sqlite:DataBase.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     
     public ArrayList<Movie> getMoviesFromSQL(String sql){
      ArrayList<Movie> results = new ArrayList<Movie>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String catagory  = rs.getString("catagory");
                String seasons = rs.getString("seasons");
                Double episode = rs.getDouble("episode");
                BufferedImage  img = ImageIO.read(rs.getBinaryStream("picture"));
                Movie p = new Movie(id,name,catagory,seasons,episode,img);
                results.add(p);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return results;
    }
     public ArrayList<Movie> sortByName(){
       String sql = "SELECT * FROM movie order by name";
        ArrayList<Movie> results = getMoviesFromSQL(sql);
        return results;
    }
    
    public ArrayList<Movie> sortByCatagory(){
        String sql = "SELECT * FROM movie order by catagory";
        ArrayList<Movie> results = getMoviesFromSQL(sql);
        return results;
    }
    public ArrayList<Movie> searchByName(String name){
        String sql = "SELECT * FROM movie WHERE name LIKE '%" + name + "%'";
        ArrayList<Movie> results = getMoviesFromSQL(sql);
        return results;
    }
    
    public ArrayList<Movie> searchByCatagory(String catagory){
        String sql = "SELECT * FROM movie WHERE catagory LIKE '%" + catagory + "%'";
        ArrayList<Movie> results = getMoviesFromSQL(sql);
        return results;
    }
     public boolean insertMovie(int id,String name,String catagory,String seasons,double episode,BufferedImage picture){
        boolean success = false;
        try {
            String sql = "INSERT INTO movie VALUES(?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(picture, "jpg", baos);
            byte[] bytes = baos.toByteArray();
            
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, catagory);
            stmt.setString(4, seasons);
            stmt.setDouble(5, episode);
            stmt.setBytes(6, bytes);
            stmt.executeUpdate();
            success = true;
            
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            
        } 
        return success;
    }
    
     public boolean updateMovie(int id,String name,String catagory,String seasons,double episode,BufferedImage picture){
         boolean success = false;
        try {
            String sql = "UPDATE Movie "
                       + " SET name = ?, "
                       + " catagory = ?, "
                       + "  seasons = ?, "
                       + "  episode = ?, "
                       + "   picture = ? "
                       + "  WHERE id = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(picture, "jpg", baos);
            byte[] bytes = baos.toByteArray();
            
            stmt.setString(1, name);
            stmt.setString(2, catagory);
            stmt.setString(3, seasons);
            stmt.setDouble(4, episode);
            stmt.setBytes(5, bytes);
            stmt.setInt(6, id);
            stmt.executeUpdate();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            
        } 
        return success;
    }
    
    public boolean deleteMovie(int id){
        boolean success = false;
        try {
            String sql = "DELETE FROM movie WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            
        } 
        return success;
    }
}
    
