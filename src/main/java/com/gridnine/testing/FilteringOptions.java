package com.gridnine.testing;

import java.time.LocalDate;
import java.time.LocalTime;

public class FilteringParametres {
    private LocalDate depDate;
    private LocalTime depTimeMin;
    private LocalTime depTimeMax;
    private LocalTime arrTimeMin;
    private LocalTime arrTimeMax;
    private LocalDate arrDate;
    private boolean noTransfer;
    private boolean oneTransfer;
    private boolean twoTransfers;
    private int maxDurationOfTransfers;
    private int maxTravelTime;

    public FilteringParametres(LocalDate depDate, LocalTime depTimeMin, LocalTime depTimeMax, LocalTime arrTimeMin, LocalTime arrTimeMax, LocalDate arrDate, boolean noTransfer, boolean oneTransfer, boolean twoTransfers, int maxDurationOfTransfers, int maxTravelTime) {
        this.depDate = LocalDate.now();
        this.depTimeMin = LocalTime.of(0, 0);
        this.depTimeMax = LocalTime.of(23, 59);
        this.arrTimeMin = arrTimeMin = LocalTime.of(0, 0);
        this.arrTimeMax = arrTimeMax = LocalTime.of(23, 59);
        this.arrDate = null;
        this.noTransfer = noTransfer = true;
        this.oneTransfer = oneTransfer = true;
        this.twoTransfers = twoTransfers = true;
        this.maxDurationOfTransfers = 40;
        this.maxTravelTime = 37;
    }

    public LocalDate getDepDate() {
        return depDate;
    }

    public void setDepDate(LocalDate depDate) {
        this.depDate = depDate;
    }

    public LocalTime getDepTimeMin() {
        return depTimeMin;
    }

    public void setDepTimeMin(LocalTime depTimeMin) {
        this.depTimeMin = depTimeMin;
    }

    public LocalTime getDepTimeMax() {
        return depTimeMax;
    }

    public void setDepTimeMax(LocalTime depTimeMax) {
        this.depTimeMax = depTimeMax;
    }

    public LocalTime getArrTimeMin() {
        return arrTimeMin;
    }

    public void setArrTimeMin(LocalTime arrTimeMin) {
        this.arrTimeMin = arrTimeMin;
    }

    public LocalTime getArrTimeMax() {
        return arrTimeMax;
    }

    public void setArrTimeMax(LocalTime arrTimeMax) {
        this.arrTimeMax = arrTimeMax;
    }

    public LocalDate getArrDate() {
        return arrDate;
    }

    public void setArrDate(LocalDate arrDate) {
        this.arrDate = arrDate;
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

    public int getMaxDurationOfTransfers() {
        return maxDurationOfTransfers;
    }

    public void setMaxDurationOfTransfers(int maxDurationOfTransfers) {
        this.maxDurationOfTransfers = maxDurationOfTransfers;
    }

    public int getMaxTravelTime() {
        return maxTravelTime;
    }

    public void setMaxTravelTime(int maxTravelTime) {
        this.maxTravelTime = maxTravelTime;
    }
}
