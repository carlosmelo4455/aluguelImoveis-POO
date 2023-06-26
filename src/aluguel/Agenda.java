package aluguel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.text.SimpleDateFormat;

public class Agenda {
    private ArrayList<Calendar> availableDates;
    private ArrayList<Calendar> rentedDates;
    private ArrayList<Calendar> blockedDates;

    public Agenda() {
        availableDates = new ArrayList<>();
        rentedDates = new ArrayList<>();
        blockedDates = new ArrayList<>();
    }

    public void addAvailableDate(Calendar date) {
        availableDates.add(date);
    }

    public void addRentedDate(Calendar date) {
        rentedDates.add(date);
    }

    public void addBlockedDate(Calendar date) {
        blockedDates.add(date);
    }

    public void removeAvailableDate(Calendar date) {
        availableDates.remove(date);
    }

    public void removeRentedDate(Calendar date) {
        rentedDates.remove(date);
    }

    public void removeBlockedDate(Calendar date) {
        blockedDates.remove(date);
    }

    public List<Calendar> getAvailableDates() {
        return availableDates;
    }

    public List<Calendar> getRentedDates() {
        return rentedDates;
    }

    public List<Calendar> getBlockedDates() {
        return blockedDates;
    }

    public void showAvailableDates() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        System.out.println("Datas disponíveis:");
        for (Calendar availableDate : availableDates) {
            System.out.println(dateFormat.format(availableDate.getTime()));
        }
    }
}
