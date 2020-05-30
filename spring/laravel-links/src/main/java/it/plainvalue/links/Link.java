package it.plainvalue.links;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

@Entity
public class Link {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Size(max = 255)
	private String title;

	@NotEmpty
	@Size(max = 255)
	@URL
	private String url;

	@NotEmpty
	@Size(max = 255)
	private String description;

	public Link() {
	}

	public Link(String title, String url, String description) {
		this.title = title;
		this.url = url;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, url, description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Link other = (Link) obj;
		return Objects.equals(title, other.title) && Objects.equals(url, other.url)
				&& Objects.equals(description, other.description);
	}

	@Override
	public String toString() {
		return "Link [id=" + id + ", title=" + title + ", url=" + url + ", description=" + description + "]";
	}

}
