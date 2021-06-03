public class Service extends Entity{

    public Service(String name, String description, int id) {
        super(name, description, id);
    }

    String getDetails() {
        return getName() + getDescription() + getId() + "Type: Service";
    }
}
