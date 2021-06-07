abstract public class Entity {
    private String name;
    private String description;
    private int id;

    public Entity(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public String getEntityInfo() {
        return "Name: " + this.name + "\nID: " + this.id + "\nDescription: " + this.description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return this.getEntityInfo() + this.getDetails();
    }

    public int getID() {
        return this.id;
    }

    abstract String getDetails();

}
