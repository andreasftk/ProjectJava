public class Material extends Entity {
    private final double level1; // 1 person
    private final double level2; // 2-4 people
    private final double level3; // >=5 people

    public Material(String name, String description, int id, double level1, double level2, double level3) {
        super(name, description, id);
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
    }

    @Override
    public String getDetails() {
        return "Type: Material" + "\n One person can get: " + level1 + "\n Two to four people can get: " + level2
                + "\n More than four people can get: " + level3;
    }

    public double getLvl1() {
        return level1;
    }

    public double getLvl2() {
        return level2;
    }

    public double getLvl3() {
        return level3;
    }
}
