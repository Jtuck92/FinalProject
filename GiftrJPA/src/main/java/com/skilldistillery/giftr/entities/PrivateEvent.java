package com.skilldistillery.giftr.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "private_event")
public class PrivateEvent {



		// FIELDS ============================================
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;

		private String name;

		@CreationTimestamp
		@Column(name = "created_date")
		private LocalDateTime createdDate;

		@UpdateTimestamp
		@Column(name = "last_update")
		private LocalDateTime lastUpdate;

		private boolean enabled = true;

		@Column(name="image_url")
		private String imageUrl;
		
		private String description;
		
		@ManyToOne
		@JoinColumn(name="manager_id")
		private User privateEventManager;
		
		@OneToMany(mappedBy="prvEvent")
		private List<PrivatePost> posts;	
		
		@ManyToMany
		@JoinTable(name = "user_has_group", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
		private List<User> privateGroupUsers;
		

		// CONSTRUCTORS ======================================
		public PrivateEvent() {
			super();
		}

		public PrivateEvent(int id, String name, LocalDateTime createdDate, LocalDateTime lastUpdate, boolean enabled,
				String imageUrl, String description) {
			super();
			this.id = id;
			this.name = name;
			this.createdDate = createdDate;
			this.lastUpdate = lastUpdate;
			this.enabled = enabled;
			this.imageUrl = imageUrl;
			this.description = description;
		}

		// Getters AND Setters =============================
		
		
		public int getId() {
			return id;
		}

		public User getPrivateEventManager() {
			return privateEventManager;
		}

		public void setPrivateEventManager(User privateEventManager) {
			this.privateEventManager = privateEventManager;
		}

		public List<PrivatePost> getPosts() {
			return posts;
		}

		public void setPosts(List<PrivatePost> posts) {
			this.posts = posts;
		}

		public List<User> getPrivateGroupUsers() {
			return privateGroupUsers;
		}

		public void setPrivateGroupUsers(List<User> privateGroupUsers) {
			this.privateGroupUsers = privateGroupUsers;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		
		// Hashcode AND Equals =============================
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
			PrivateEvent other = (PrivateEvent) obj;
			if (id != other.id)
				return false;
			return true;
		}
		
		// ToString =============================
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("PrivateEvent [id=").append(id).append(", name=").append(name).append(", createdDate=")
					.append(createdDate).append(", lastUpdate=").append(lastUpdate).append(", enabled=").append(enabled)
					.append(", imageUrl=").append(imageUrl).append(", description=").append(description).append("]");
			return builder.toString();
		}


		
		
	}

