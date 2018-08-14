package com.labutin.barman.entity;

import java.io.InputStream;

public class Cocktail {
	private int cocktailId;
	private String cocktailName;
	private int userId;
	private String cocktailDescription;
	private int cocktailVol;
	private boolean isPublished;
	private InputStream image;

	public Cocktail(int cocktaiId, String cocktailName, int userId, String cocktailDescription, int cocktailVol,
			boolean isPublished) {
		super();
		this.cocktailId = cocktaiId;
		this.cocktailName = cocktailName;
		this.userId = userId;
		this.cocktailDescription = cocktailDescription;
		this.cocktailVol = cocktailVol;
		this.isPublished = isPublished;
	}

	public Cocktail() {
	}

	public int getCocktailId() {
		return cocktailId;
	}

	public void setCocktailId(int cocktailId) {
		this.cocktailId = cocktailId;
	}

	public String getCocktailName() {
		return cocktailName;
	}

	public void setCocktailName(String cocktailName) {
		this.cocktailName = cocktailName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCocktailDescription() {
		return cocktailDescription;
	}

	public void setCocktailDescription(String cocktailDescription) {
		this.cocktailDescription = cocktailDescription;
	}

	public int getCocktailVol() {
		return cocktailVol;
	}

	public void setCocktailVol(int cocktailVol) {
		this.cocktailVol = cocktailVol;
	}

	public boolean getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}

	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cocktailId;
		result = prime * result + ((cocktailDescription == null) ? 0 : cocktailDescription.hashCode());
		result = prime * result + ((cocktailName == null) ? 0 : cocktailName.hashCode());
		result = prime * result + cocktailVol;
		result = prime * result + userId;
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
		Cocktail other = (Cocktail) obj;
		if (cocktailId != other.cocktailId)
			return false;
		if (cocktailDescription == null) {
			if (other.cocktailDescription != null)
				return false;
		} else if (!cocktailDescription.equals(other.cocktailDescription))
			return false;
		if (cocktailName == null) {
			if (other.cocktailName != null)
				return false;
		} else if (!cocktailName.equals(other.cocktailName))
			return false;
		if (cocktailVol != other.cocktailVol)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cocktail [cocktaiId=" + cocktailId + ", cocktailName=" + cocktailName + ", userId=" + userId
				+ ", cocktailDescription=" + cocktailDescription + ", cocktailVol=" + cocktailVol + "]";
	}

}
