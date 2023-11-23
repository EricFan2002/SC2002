package entity.report;

import java.util.ArrayList;

public abstract class Report {
    private static String[] fields;

    public static String[] getFields() {
        return fields;
    }

    public abstract ArrayList<ArrayList<String>> serialize();
}
