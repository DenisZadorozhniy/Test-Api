package model;

import lombok.Data;

import java.util.Objects;

@Data
public class UsersPajo {

	private String website;
	private Address address;
	private String phone;
	private String name;
	private Company company;
	private int id;
	private String email;
	private String username;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		return this.id == ((UsersPajo) o).id &&
				this.website.equals(((UsersPajo) o).website) &&
				this.address.equals(((UsersPajo) o).address) &&
				this.phone.equals(((UsersPajo) o).phone) &&
				this.name.equals(((UsersPajo) o).name) &&
				this.company.equals(((UsersPajo) o).company) &&
				this.email.equals(((UsersPajo) o).email) &&
				this.username.equals(((UsersPajo) o).username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(website, address, phone, name, company, id, email, username);
	}

	@Override
	public String toString() {
		return "UsersPajo{" +
				"website='" + website + '\'' +
				", address=" + address +
				", phone='" + phone + '\'' +
				", name='" + name + '\'' +
				", company=" + company +
				", id=" + id +
				", email='" + email + '\'' +
				", username='" + username + '\'' +
				'}';

	}
}