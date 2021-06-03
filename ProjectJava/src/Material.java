public class Material extends Entity{
    private static double level1; // 1 person
    private static double level2; // 2-4 people
    private static double level3; // >=5 people
    private int noPersons=1;

    public Material(String name, String description, int id) {
        super(name, description, id);
    }

    String getDetails() {
        if (noPersons == 1)
            return getName() + getDescription() + getId() + "Level of quantity that can get:" + level1 + "Type: Material";
        else if (noPersons>=2 && noPersons<=4)
            return getName() + getDescription() + getId() + "Level of quantity that can get:" + level2 + "Type: Material";
        else
            return getName() + getDescription() + getId() + "Level of quantity that can get:" + level3 + "Type: Material";
    }


}
