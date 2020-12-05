package com.skilldistillery.giftr.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	// FIELDS ============================================
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;

	private String email;

	private String password;

	private boolean enabled = true;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@CreationTimestamp
	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@UpdateTimestamp
	@Column(name = "last_update")
	private LocalDateTime lastUpdate;

	@Column(name = "birth_date")
	private String birthDate;

	private String gender;
	
	@JsonIgnore
	@OneToMany (mappedBy = "user")
	private List<EventPost> eventPosts;
	
	@JsonIgnore
	@ManyToMany(mappedBy="users")
	private List<Event> events;
	
	@JsonIgnore
	@OneToMany(mappedBy="eventManager")
	private List<Event> managerEvents;
	
	@JsonIgnore
	@OneToMany(mappedBy="gifter")
	private List<Gift> sentGifts;
	
	@JsonIgnore
	@OneToMany(mappedBy="receiver")
	private List<Gift> recievedGifts;
	
	@JsonIgnore
	@OneToMany(mappedBy="privateEventManager")
	private List<PrivateEvent> managerPrvEvents;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<PrivatePost> prvEventPosts;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<PrivateComment> prvEventComments;
	
	@JsonIgnore
	@ManyToMany(mappedBy="privateGroupUsers")
	private List<PrivateEvent> prvEvents;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Payment> payments;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<EventComment> eventComments;
	
	
	
	

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	// CONSTRUCTORS ======================================
	public User() {
		super();
	}

	public User(int id, String username, String email, String password, boolean enabled, String firstName,
			String lastName, LocalDateTime createdDate, LocalDateTime lastUpdate, String birthDate, String gender) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.firstName = firstName;
		this.lastName = lastName;
		this.createdDate = createdDate;
		this.lastUpdate = lastUpdate;
		this.birthDate = birthDate;
		this.gender = gender;
	}

	// Getters AND Setters =============================
	
	
	public List<Gift> getSentGifts() {
		return sentGifts;
	}
	
	public List<EventComment> getEventComments() {
		return eventComments;
	}

	public void setEventComments(List<EventComment> eventComments) {
		this.eventComments = eventComments;
	}

	public List<PrivateEvent> getManagerPrvEvents() {
		return managerPrvEvents;
	}

	public void setManagerPrvEvents(List<PrivateEvent> managerPrvEvents) {
		this.managerPrvEvents = managerPrvEvents;
	}

	public List<PrivatePost> getPrvEventPosts() {
		return prvEventPosts;
	}

	public void setPrvEventPosts(List<PrivatePost> prvEventPosts) {
		this.prvEventPosts = prvEventPosts;
	}

	public List<PrivateComment> getPrvEventComments() {
		return prvEventComments;
	}

	public void setPrvEventComments(List<PrivateComment> prvEventComments) {
		this.prvEventComments = prvEventComments;
	}

	public List<PrivateEvent> getPrvEvents() {
		return prvEvents;
	}

	public void setPrvEvents(List<PrivateEvent> prvEvents) {
		this.prvEvents = prvEvents;
	}

	public void setSentGifts(List<Gift> sentGifts) {
		this.sentGifts = sentGifts;
	}
	
	public List<Gift> getRecievedGifts() {
		return recievedGifts;
	}
	
	public void setRecievedGifts(List<Gift> recievedGifts) {
		this.recievedGifts = recievedGifts;
	}
	
	public List<EventPost> getEventPosts() {
		return eventPosts;
	}
	
	public void setEventPosts(List<EventPost> eventPosts) {
		this.eventPosts = eventPosts;
	}
	
	public List<Event> getEvents() {
		return events;
	}
	
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	public List<Event> getManagerEvents() {
		return managerEvents;
	}
	
	public void setManagerEvents(List<Event> managerEvents) {
		this.managerEvents = managerEvents;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	
	// Hashcode AND Equals =============================
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	// ToString =============================
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(id).append(", username=").append(username).append(", email=").append(email)
				.append(", password=").append(password).append(", enabled=").append(enabled).append(", firstName=")
				.append(firstName).append(", lastName=").append(lastName).append(", createdDate=").append(createdDate)
				.append(", lastUpdate=").append(lastUpdate).append(", birthDate=").append(birthDate).append(", gender=")
				.append(gender).append("]");
		return builder.toString();
	}

}
