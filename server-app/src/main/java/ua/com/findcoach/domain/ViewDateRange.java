package ua.com.findcoach.domain;

/**
 * Created by Vladimir.Malinovskiy on 2/19/2016.
 */
public enum ViewDateRange {
    month ("month"),
    week ("week"),
    day ("day");

    private String dateRange;

    ViewDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public String getDateRange() {
        return this.dateRange;
    }
}
