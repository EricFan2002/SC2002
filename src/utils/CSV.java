package utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class CSV {
    public static boolean exportToCSV(String filename, ArrayList<ArrayList<String>> data) {
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(filename), CSVFormat.DEFAULT)) {
            for (ArrayList<String> row : data) {
                printer.printRecord(row);
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static ArrayList<ArrayList<String>> importFromCSV(String filename) {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        try (CSVParser parser = new CSVParser(new FileReader(filename), CSVFormat.DEFAULT)) {
            for (CSVRecord record : parser) {
                ArrayList<String> row = new ArrayList<String>();
                for (String field : record) {
                    row.add(field);
                }
                data.add(row);
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return data;
    }
}
