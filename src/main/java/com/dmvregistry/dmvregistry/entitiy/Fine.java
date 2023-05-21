package com.dmvregistry.dmvregistry.entitiy;

import java.time.LocalDate;
import java.time.LocalTime;

public class Fine {
    private int id;
    private String carId;
    private String offenderName;
    private String policemanName;
    private LocalTime time;
    private double sum;
    private boolean court = false;
    private boolean isPaid = false;
    private LocalDate date;
    private LocalDate deadline;

    public void pay() {
        this.isPaid = true;
    }

    public void sendCourt() {
        this.court = true;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getOffenderName() {
        return offenderName;
    }

    public void setOffenderName(String offenderName) {
        this.offenderName = offenderName;
    }

    public String getPolicemanName() {
        return policemanName;
    }

    public void setPolicemanName(String policemanName) {
        this.policemanName = policemanName;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isCourt() {
        return court;
    }

    public void setCourt(boolean court) {
        this.court = court;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Fine{" +
                "id=" + id +
                ", carId=" + carId +
                ", offenderName=" + offenderName +
                ", policemanName=" + policemanName +
                ", time=" + time +
                ", sum=" + sum +
                ", court=" + court +
                ", isPaid=" + isPaid +
                ", date=" + date +
                ", deadline=" + deadline +
                '}';
    }
}
