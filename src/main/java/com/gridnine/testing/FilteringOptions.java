package com.gridnine.testing;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Bean with parameters for filtering flights.
 */
public class FilteringOptions {
    private LocalDate departureDate;
    private LocalTime departureTimeMin;
    private LocalTime departureTimeMax;
    private LocalTime arrivalTimeMin;
    private LocalTime arrivalTimeMax;
    private LocalDate arrivalDate;
    private boolean noTransfer = true;
    private boolean oneTransfer = true;
    private boolean twoTransfers = true;
    private Integer maxDurationOfTransfers;
    private Integer maxTravelTime;
    private boolean flightsFromNow;
    private boolean segmentDatesFilter;

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTimeMin() {
        return departureTimeMin;
    }

    public void setDepartureTimeMin(LocalTime departureTimeMin) {
        this.departureTimeMin = departureTimeMin;
    }

    public LocalTime getDepartureTimeMax() {
        return departureTimeMax;
    }

    public void setDepartureTimeMax(LocalTime departureTimeMax) {
        this.departureTimeMax = departureTimeMax;
    }

    public LocalTime getArrivalTimeMin() {
        return arrivalTimeMin;
    }

    public void setArrivalTimeMin(LocalTime arrivalTimeMin) {
        this.arrivalTimeMin = arrivalTimeMin;
    }

    public LocalTime getArrivalTimeMax() {
        return arrivalTimeMax;
    }

    public void setArrivalTimeMax(LocalTime arrivalTimeMax) {
        this.arrivalTimeMax = arrivalTimeMax;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public boolean isNoTransfer() {
        return noTransfer;
    }

    public void setNoTransfer(boolean noTransfer) {
        this.noTransfer = noTransfer;
    }

    public boolean isOneTransfer() {
        return oneTransfer;
    }

    public void setOneTransfer(boolean oneTransfer) {
        this.oneTransfer = oneTransfer;
    }

    public boolean isTwoTransfers() {
        return twoTransfers;
    }

    public void setTwoTransfers(boolean twoTransfers) {
        this.twoTransfers = twoTransfers;
    }

    public Integer getMaxDurationOfTransfers() {
        return maxDurationOfTransfers;
    }

    public void setMaxDurationOfTransfers(Integer maxDurationOfTransfers) {
        this.maxDurationOfTransfers = maxDurationOfTransfers;
    }

    public Integer getMaxTravelTime() {
        return maxTravelTime;
    }

    public void setMaxTravelTime(Integer maxTravelTime) {
        this.maxTravelTime = maxTravelTime;
    }

    public boolean isFlightsFromNow() {
        return flightsFromNow;
    }

    public void setFlightsFromNow(boolean flightsFromNow) {
        this.flightsFromNow = flightsFromNow;
    }

    public boolean isSegmentDatesFilter() {
        return segmentDatesFilter;
    }

    public void setSegmentDatesFilter(boolean segmentDatesFilter) {
        this.segmentDatesFilter = segmentDatesFilter;
    }
}
