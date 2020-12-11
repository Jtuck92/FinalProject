package com.skilldistillery.giftr.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "private_comment")
public class PrivateComment {

	// FIELDS ============================================
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String comment;
	
	@CreationTimestamp
	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@UpdateTimestamp
	@Column(name = "last_update")
	private LocalDateTime lastUpdate;
	
	private boolean enabled = true;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@JsonIgnoreProperties("comments")
	@ManyToOne
	@JoinColumn(name="post_id")
	private PrivatePost post;
	
	// CONSTRUCTORS ======================================
	public PrivateComment() {
		super();
	}

	public PrivateComment(int id, String comment, LocalDateTime createdDate, LocalDateTime lastUpdate,
			boolean enabled) {
		super();
		this.id = id;
		this.comment = comment;
		this.createdDate = createdDate;
		this.lastUpdate = lastUpdate;
		this.enabled = enabled;
	}

	// Getters AND Setters =============================
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PrivatePost getPost() {
		return post;
	}

	public void setPost(PrivatePost post) {
		this.post = post;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
		PrivateComment other = (PrivateComment) obj;
		if (id != other.id)
			return false;
		return true;
	}

	// ToString =============================
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PrivateComment [id=").append(id).append(", comment=").append(comment).append(", createdDate=")
				.append(createdDate).append(", lastUpdate=").append(lastUpdate).append(", enabled=").append(enabled)
				.append("]");
		return builder.toString();
	}

	
	

}
