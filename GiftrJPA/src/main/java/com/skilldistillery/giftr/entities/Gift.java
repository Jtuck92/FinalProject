package com.skilldistillery.giftr.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gift {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Double price;

	@Column(name = "weight_kg")
	private Double weight;
	private String description;
	private Boolean enabled;

	@Column(name = "event_id")
	private int eventId;

	@Column(name = "gifter_id")
	private int gifterId;

	@Column(name = "receiver_id")
	private int recieverId;

	private int rating;
	private String name;

	@Column(name = "image_url")
	private String imageUrl;

	public Gift() {
		super();
	}

	public Gift(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getGifterId() {
		return gifterId;
	}

	public void setGifterId(int gifterId) {
		this.gifterId = gifterId;
	}

	public int getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(int recieverId) {
		this.recieverId = recieverId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gift other = (Gift) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Gift [id=" + id + ", price=" + price + ", weight=" + weight + ", description=" + description
				+ ", enabled=" + enabled + ", eventId=" + eventId + ", gifterId=" + gifterId + ", recieverId="
				+ recieverId + ", rating=" + rating + ", name=" + name + ", imageUrl=" + imageUrl + "]";
	}

}
