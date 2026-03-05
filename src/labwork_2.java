import java.util.Locale;

public class labwork_2 {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        System.out.println("labwork_2 - Cube demonstrations\n");

        // Typical cube
        Cubes c1 = new Cubes(3.0);
        System.out.printf("Cube with side=%.3f -> volume=%.3f, face area=%.3f, space diagonal=%.3f\n",
                c1.getSide(), c1.volume(), c1.faceArea(), c1.spaceDiagonal());

        // Zero side
        Cubes c2 = new Cubes(0.0);
        System.out.printf("Cube with side=%.3f -> volume=%.3f, face area=%.3f, space diagonal=%.3f\n",
                c2.getSide(), c2.volume(), c2.faceArea(), c2.spaceDiagonal());

        // Demonstrate setter validation
        Cubes c3 = new Cubes();
        try {
            c3.setSide(-2.5);
        } catch (IllegalArgumentException ex) {
            System.out.println("Attempt to set negative side: " + ex.getMessage());
            // set a valid value
            c3.setSide(2.5);
        }
        System.out.printf("Cube with side=%.3f -> volume=%.3f, face area=%.3f, space diagonal=%.3f\n",
                c3.getSide(), c3.volume(), c3.faceArea(), c3.spaceDiagonal());
    }
}

class Cubes {
    private double side; // length of cube side

    // Constructors
    public Cubes() {
        this.side = 2;
    }

    public Cubes(double side) {
        setSide(side);
    }

    // Getter
    public double getSide() {
        return side;
    }

    // Setter with validation: side must be non-negative
    public void setSide(double side) {
        if (Double.isNaN(side)) {
            throw new IllegalArgumentException("side cannot be NaN");
        }
        if (side < 0.0) {
            throw new IllegalArgumentException("side must be non-negative");
        }
        this.side = side;
    }

    // Calculate volume (side^3)
    public double volume() {
        return side * side * side;
    }

    // Calculate area of one face (side^2)
    public double faceArea() {
        return side * side;
    }

    // Calculate space (large) diagonal length: side * sqrt(3)
    public double spaceDiagonal() {
        return side * Math.sqrt(3.0);
    }

    @Override
    public String toString() {
        return String.format("Cube(side=%.3f)", side);
    }
}

