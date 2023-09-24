package com.tracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LocationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lid")
	private int lid;
	@Column(name = "country")
	private String country;
	@Column(name = "state")
	private String state;
	@Column(name = "latest_total")
	private int latesttotal;
	@Column(name = "differfromprevay")
	private int differfromprevay;
	public LocationEntity() {
		// TODO Auto-generated constructor stub
	}
	public LocationEntity(int lid, String country, String state, int latesttotal, int differfromprevay) {
		super();
		this.lid = lid;
		this.country = country;
		this.state = state;
		this.latesttotal = latesttotal;
		this.differfromprevay = differfromprevay;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getLatesttotal() {
		return latesttotal;
	}
	public void setLatesttotal(int latesttotal) {
		this.latesttotal = latesttotal;
	}
	public int getDifferfromprevay() {
		return differfromprevay;
	}
	public void setDifferfromprevay(int differfromprevay) {
		this.differfromprevay = differfromprevay;
	}
	@Override
	public String toString() {
		return "LocationEntity [lid=" + lid + ", country=" + country + ", state=" + state + ", latesttotal="
				+ latesttotal + ", differfromprevay=" + differfromprevay + "]";
	}
	
	
	
	
}
