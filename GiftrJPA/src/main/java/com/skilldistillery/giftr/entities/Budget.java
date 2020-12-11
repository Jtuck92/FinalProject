package com.skilldistillery.giftr.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Budget {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="low_price")
	private Integer lowPrice;
	
	@Column(name="high_price")
	private Integer highPrice;
	
	private Boolean enabled = true;
	
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="budget")
	private List<Event> events;

	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Budget() {
		super();
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
		Budget other = (Budget) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Budget [id=" + id + ", lowPrice=" + lowPrice + ", highPrice=" + highPrice + ", enabled=" + enabled
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(Integer lowPrice) {
		this.lowPrice = lowPrice;
	}

	public Integer getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(Integer highPrice) {
		this.highPrice = highPrice;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
