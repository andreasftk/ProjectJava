public class RequestDonation {

    private Entity entity;
    private double quantity;

    RequestDonation(Entity entity, double quantity) {
        this.entity = entity;
        this.quantity = quantity;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}