package controller.report;

import entity.report.Report;
import utils.CSV;

public class ExportCSVController {
    public static String exportReportToCSV(String filename, Report report) {
        boolean res = CSV.exportToCSV(filename + ".csv", report.serialize());
        if (res) {
            return filename + ".csv";
        } else {
            return null;
        }
    }
}
