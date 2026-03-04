package com.example.listycity;

/**
 * This is a class that defines a City.
 */
public class City implements Comparable<City> {
    /**
     * This is the name of the city.
     */
    private String city;

    /**
     * This is the name of the province.
     */
    private String province;

    /**
     * This is a constructor for City.
     * @param city
     *      The name of the city
     * @param province
     *      The name of the province
     */
    City(String city, String province){
        this.city = city;
        this.province = province;
    }

    /**
     * This returns the name of the city.
     * @return
     *      Return the name of the city
     */
    String getCityName(){
        return this.city;
    }

    /**
     * This returns the name of the province.
     * @return
     *      Return the name of the province
     */
    String getProvinceName(){
        return this.province;
    }

    @Override
    public int compareTo(City city) {
        return this.city.compareTo(city.getCityName());
    }
}
