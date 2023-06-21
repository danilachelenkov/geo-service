package ru.netology.entity;

public class Location {

    private final String city;

    private final Country country;

    private final String street;

    private final int builing;

    public Location(String city, Country country, String street, int builing) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.builing = builing;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public int getBuiling() {
        return builing;
    }

    @Override
    public boolean equals(Object obj) {
        Location location = (Location) obj;
        if (this.getCountry() == location.getCountry()
                && this.getCity() == location.getCity()
                && this.getStreet() == location.getStreet()
                && this.getBuiling() == location.getBuiling()) {
            return true;
        }
        return false;
    }
}
