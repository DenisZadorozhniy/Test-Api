package model;

import lombok.Data;

@Data
public class Company{
	private String bs;
	private String catchPhrase;
	private String name;

	@Override
	public String toString() {
		return "Company{" +
				"bs='" + bs + '\'' +
				", catchPhrase='" + catchPhrase + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}