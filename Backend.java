
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * this class implements MeteoriteObjectInterface
 */
public class Backend implements BackendInterface {

    private IterableMultiKeyRBT<MeteoriteObjectInterface> redBlackTree;

    public Backend(IterableMultiKeyRBT<MeteoriteObjectInterface> redBlackTree) {
        this.redBlackTree = redBlackTree;
    }

    /**
     * This method reads a file containing meteorite data and inserts it into a
     * redBlackTree
     *
     * @param fileName the name of the file containing the data (that needs to
     * be read)
     * @throws FileNotFoundException throws FileNotFoundException if the file
     * could not be read
     */
    @Override
    public void readFile(String fileName) throws FileNotFoundException {
        if (fileName == null) {
            throw new FileNotFoundException("Filename invalid!");
        }
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] lineArray = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                String name = lineArray[0];
                double mass = Double.parseDouble(lineArray[2].trim());
                String fall = lineArray[3];
                String coordinate = lineArray[6];  // Changed from 8 to 6
                coordinate = coordinate.replace("\"", "");
                coordinate = coordinate.replace("(", "");
                coordinate = coordinate.replace(")", "");
                String[] coords = coordinate.split(",");
                String latitudeStr = coords[0];
                String longitudeStr = coords[1];
                double latitude = Double.parseDouble(latitudeStr.trim());
                double longitude = Double.parseDouble(longitudeStr.trim());
                MeteoriteObjectInterface met = new Meteorite(name, latitude, longitude, fall, mass);
                redBlackTree.insertSingleKey(met);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * (Assuming a redBlackTree was passed through constructor call, this method
     * needs no parameters) This method iterates through the iterable
     * redBlackTree that was passed through the constructor and returns a list
     * of the meteorite object(s) with the highest mass.
     *
     * @return list of meteorite object(s) with the highest mass in the provided
     * tree
     */
    @Override
    public List<MeteoriteObjectInterface> getMaxMassMeteorites() {
        double maxMass = -1;
        List<MeteoriteObjectInterface> maxMassMeteorite = new ArrayList<>();
        for (MeteoriteObjectInterface meteorite : redBlackTree) {
            if (meteorite.getMass() > maxMass) {
                maxMass = meteorite.getMass();
                maxMassMeteorite.clear();
                maxMassMeteorite.add(meteorite);
            } else if (meteorite.getMass() == maxMass) {
                maxMassMeteorite.add(meteorite);
            }
        }
        return maxMassMeteorite;
    }

    /**
     * This method iterates through the iterable redBlackTree provided through
     * the constructor call and returns a list of meteorites objects whose mass
     * is within the range low-high
     *
     * @param lowerBound integer representing the low threshold mass
     * @param higherBound integer representing the high threshold mass
     * @return list of meteorite objects whose mass is within the range low-high
     */
    @Override
    public List<MeteoriteObjectInterface> getMeteoritesBetween(double lowerBound, double higherBound)
            throws IllegalArgumentException {
        // First validate that the bounds are in correct order
        if (lowerBound > higherBound) {
            throw new IllegalArgumentException("Lower bound cannot be greater than higher bound");
        }

        // Validate bounds are non-negative
        if (lowerBound < 0) {
            throw new IllegalArgumentException("Lower bound cannot be negative");
        }

        List<MeteoriteObjectInterface> meteoritesInBound = new ArrayList<>();
        for (MeteoriteObjectInterface meteorite : redBlackTree) {
            double mass = meteorite.getMass();
            if (mass >= lowerBound && mass <= higherBound) {
                meteoritesInBound.add(meteorite);
            }
        }
        return meteoritesInBound;
    }
}
