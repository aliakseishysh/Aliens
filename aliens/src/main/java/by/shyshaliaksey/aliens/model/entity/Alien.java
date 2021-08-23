package by.shyshaliaksey.aliens.model.entity;

import java.util.Objects;

/**
 * Class {@code Alien} designed for storing information about alien
 * 
 * @author Aliaksey Shysh
 *
 */
public class Alien {

	private final int id;
	private final String name;
	private String smallDescription;
	private String bigDescription;
	private final String imageUrl;

	public enum Status {
		NORMAL, UNDER_CONSIDERATION, DECLINED
	}

	public Alien(int id, String name, String smallDescription, String bigDescription, String imageUrl) {
		this.id = id;
		this.name = name;
		this.smallDescription = smallDescription;
		this.bigDescription = bigDescription;
		this.imageUrl = imageUrl;
	}

	public Alien(int id, String name, String imageUrl) {
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
	}

	public int getId() {
		return id;
	}

	@SuppressWarnings("unused")
	public String getName() {
		return name;
	}

	@SuppressWarnings("unused")
	public String getSmallDescription() {
		return smallDescription;
	}

	@SuppressWarnings("unused")
	public String getBigDescription() {
		return bigDescription;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bigDescription == null) ? 0 : bigDescription.hashCode());
		result = prime * result + id;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((smallDescription == null) ? 0 : smallDescription.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Alien)) return false;

		Alien alien = (Alien) o;

		if (id != alien.id) return false;
		if (!Objects.equals(name, alien.name)) return false;
		if (!Objects.equals(smallDescription, alien.smallDescription))
			return false;
		if (!Objects.equals(bigDescription, alien.bigDescription))
			return false;
		return Objects.equals(imageUrl, alien.imageUrl);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Alien [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", smallDescription=");
		builder.append(smallDescription);
		builder.append(", bigDescription=");
		builder.append(bigDescription);
		builder.append(", imageUrl=");
		builder.append(imageUrl);
		builder.append("]");
		return builder.toString();
	}

}
