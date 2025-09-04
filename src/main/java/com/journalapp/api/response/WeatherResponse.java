package com.journalapp.api.response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {

    private Current current;




    public static class Current {
      
        private int temperature;
     @JsonProperty("weather_descriptions")
        private ArrayList<String> weatherDescriptions;
        private int feelslike;
		public int getTemperature() {
			return temperature;
		}
		public void setTemperature(int temperature) {
			this.temperature = temperature;
		}
		public ArrayList<String> getWeatherDescriptions() {
			return weatherDescriptions;
		}
		public void setWeatherDescriptions(ArrayList<String> weatherDescriptions) {
			this.weatherDescriptions = weatherDescriptions;
		}
		public int getFeelslike() {
			return feelslike;
		}
		public void setFeelslike(int feelslike) {
			this.feelslike = feelslike;
		}
    
    }




	public Current getCurrent() {
		return current;
	}




	public void setCurrent(Current current) {
		this.current = current;
	}




}

