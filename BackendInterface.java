import java.io.FileNotFoundException;
import java.util.List;

/**
 * Backend interface for the Meteorite catcher project. It provides methods that will allow the
 * frontend to read data from a file, get a list of meteorites with the maximum mass in the data
 * set, get a list of meteorites with a mass between two specified thresholds
 *
 * List<IndividualMeteoriteInterface> is meant to refer to a list of meteorite objects that
 * implement IndividualMeteoriteInterface
 */
public interface BackendInterface {

  /* Constructor:
   * public BackendInterface(IterableMultiKeySortedCollectionInterface<T> redBlackTree);
   */

  /**
   * This method reads a file containing meteorite data and inserts it into a redBlackTree
   *
   * @param fileName the name of the file containing the data (that needs to be read)
   * @throws FileNotFoundException throws FileNotFoundException if the file could not be read
   */
  public void readFile(String fileName) throws FileNotFoundException;

  /**
   * (Assuming a redBlackTree was passed through constructor call, this method needs no parameters)
   * This method iterates through the iterable redBlackTree that was passed through the constructor
   * and returns a list of the meteorite object(s) with the highest mass.
   *
   * @return list of meteorite object(s) with the highest mass in the provided tree
   */
  public List<MeteoriteObjectInterface> getMaxMassMeteorites();

  /**
   * This method iterates through the iterable redBlackTree provided through the constructor call
   * and returns a list of meteorites objects whose mass is within the range low-high
   *
   * @param lowerBound integer representing the low threshold mass
   * @param higherBound integer representing the high threshold mass
   * @return list of meteorite objects whose mass is within the range low-high
   */
  public List<MeteoriteObjectInterface> getMeteoritesBetween(double lowerBound, double higherBound)
      throws IllegalArgumentException;
}
