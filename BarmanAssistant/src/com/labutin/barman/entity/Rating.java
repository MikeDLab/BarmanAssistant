package com.labutin.barman.entity;

public class Rating {
	protected int estimatingId;
	protected int estimatedId;
	protected int rating;
	public int getEstimating() {
		return estimatingId;
	}
	public void setEstimating(int estimating) {
		this.estimatingId = estimating;
	}
	public int getEstimated() {
		return estimatedId;
	}
	public void setEstimated(int estimated) {
		this.estimatedId = estimated;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + estimatedId;
		result = prime * result + estimatingId;
		result = prime * result + rating;
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
		Rating other = (Rating) obj;
		if (estimatedId != other.estimatedId)
			return false;
		if (estimatingId != other.estimatingId)
			return false;
		if (rating != other.rating)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Rating [estimatingId=" + estimatingId + ", estimatedId=" + estimatedId + ", rating=" + rating + "]";
	}	
	
}
