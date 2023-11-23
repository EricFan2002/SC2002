package entity.report;

import java.util.ArrayList;

import entity.interfaces.ISerializeable;

public abstract class Report implements ISerializeable {
    private static String[] fields;

    public static String[] getFields() {
        return fields;
    }

    public abstract ArrayList<ArrayList<String>> serialize();
}
