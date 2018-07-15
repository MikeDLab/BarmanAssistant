package com.labutin.barman.entity;

public class Cocktail {
	private int cocktaiId;
	private String cocktailName;
	private int userId;
	private String cocktailDescription;
	private int cocktailVol;
	public Cocktail(int cocktaiId, String cocktailName, int userId, String cocktailDescription, int cocktailVol) {
		super();
		this.cocktaiId = cocktaiId;
		this.cocktailName = cocktailName;
		this.userId = userId;
		this.cocktailDescription = cocktailDescription;
		this.cocktailVol = cocktailVol;
	}
	public Cocktail() {
		// TODO Auto-generated constructor stub
	}
	public int getCocktaiId() {
		return cocktaiId;
	}
	public void setCocktaiId(int cocktaiId) {
		this.cocktaiId = cocktaiId;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cocktaiId;
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
		if (cocktaiId != other.cocktaiId)
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
		return "Cocktail [cocktaiId=" + cocktaiId + ", cocktailName=" + cocktailName + ", userId=" + userId
				+ ", cocktailDescription=" + cocktailDescription + ", cocktailVol=" + cocktailVol + "]";
	}
	
	
}
