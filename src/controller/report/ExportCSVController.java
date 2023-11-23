package controller.report;

import java.text.SimpleDateFormat;

import entity.report.Report;
import utils.CSV;

public class ExportCSVController {
    public static String export(Report report, String filename) {
        boolean res = CSV.exportToCSV(filename + ".csv", report.serialize());
        if (res) {
            return filename + ".csv";
        } else {
            return null;
        }
    }

    public static String export(Report report) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(System.currentTimeMillis());
        boolean res = CSV.exportToCSV(timestamp + ".csv", report.serialize());
        if (res) {
            return timestamp + ".csv";
        } else {
            return null;
        }
    }
}
