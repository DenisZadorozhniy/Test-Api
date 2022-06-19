package model;

import lombok.Data;

@Data
public class Address{

	private String zipcode;
	private Geo geo;
	private String suite;
	private String city;
	private String street;

	@Override
	public String toString() {
		return "Address{" +
				"zipcode='" + zipcode + '\'' +
				", geo=" + geo +
				", suite='" + suite + '\'' +
				", city='" + city + '\'' +
				", street='" + street + '\'' +
				'}';
	}
}