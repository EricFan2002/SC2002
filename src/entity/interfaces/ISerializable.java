package entity.interfaces;

import java.util.ArrayList;
/**
 * The {@code ISerializable} interface represents an entity that can be serialized into a list of lists of strings.
 */
public interface ISerializable {

    /**
     * Serializes the object into a list of lists of strings.
     *
     * @return An {@link ArrayList} containing lists of strings representing the serialized data.
     */
    public ArrayList<ArrayList<String>> serialize();
}
