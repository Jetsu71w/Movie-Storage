import java.awt.image.BufferedImage;

public class Movie {
    private int id;
    private String name;
    private String catagory;
    private String seasons;
    private double ep;
    private BufferedImage picture;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movie other = (Movie) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", name=" + name + ", catagory=" + catagory + ", seasons=" + seasons + ", ep=" + ep + ", picture=" + picture + '}';
    }

    public Movie(int id, String name, String catagory, String seasons, double ep, BufferedImage picture) {
        this.id = id;
        this.name = name;
        this.catagory = catagory;
        this.seasons = seasons;
        this.ep = ep;
        this.picture = picture;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getSeasons() {
        return seasons;
    }

    public void setSeasons(String seasons) {
        this.seasons = seasons;
    }

    public double getEp() {
        return ep;
    }

    public void setEp(double ep) {
        this.ep = ep;
    }

    public BufferedImage getPicture() {
        return picture;
    }

    public void setPicture(BufferedImage picture) {
        this.picture = picture;
    }
    
    
}
