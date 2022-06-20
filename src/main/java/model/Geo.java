package model;

import lombok.Data;

@Data
public class Geo{
	private String lng;
	private String lat;

	@Override
	public String toString() {
		return "Geo{" +
				"lng='" + lng + '\'' +
				", lat='" + lat + '\'' +
				'}';
	}
}