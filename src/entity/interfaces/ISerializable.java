package entity.interfaces;

import java.util.ArrayList;

<<<<<<< Updated upstream:src/entity/interfaces/ISerializable.java
public interface ISerializable {
    public ArrayList<ArrayList<String>> serialize();
=======
/**
 * The {@code ISerializeable} interface defines a method for serializing objects into an ArrayList of ArrayList of Strings.
 */
public interface ISerializeable {

    /**
     * Serializes the object and represents its data as an ArrayList of ArrayList of Strings.
     *
     * @return An {@code ArrayList<ArrayList<String>>} representing the serialized data of the object.
     */
    ArrayList<ArrayList<String>> serialize();
>>>>>>> Stashed changes:src/entity/interfaces/ISerializeable.java
}
