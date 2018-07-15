package com.labutin.barman.entity;

public class Ingredient {
	private int ingredientId;
	private String ingredientName;
	private String ingredientDescription;
	public Ingredient() {
		// TODO Auto-generated constructor stub
	}
	public Ingredient(int ingredientId, String ingredientName, String ingredientDescription) {
		super();
		this.ingredientId = ingredientId;
		this.ingredientName = ingredientName;
		this.ingredientDescription = ingredientDescription;
	}
	public int getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}
	public String getIngredientName() {
		return ingredientName;
	}
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	public String getIngredientDescription() {
		return ingredientDescription;
	}
	public void setIngredientDescription(String ingredientDescription) {
		this.ingredientDescription = ingredientDescription;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ingredientDescription == null) ? 0 : ingredientDescription.hashCode());
		result = prime * result + ingredientId;
		result = prime * result + ((ingredientName == null) ? 0 : ingredientName.hashCode());
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
		Ingredient other = (Ingredient) obj;
		if (ingredientDescription == null) {
			if (other.ingredientDescription != null)
				return false;
		} else if (!ingredientDescription.equals(other.ingredientDescription))
			return false;
		if (ingredientId != other.ingredientId)
			return false;
		if (ingredientName == null) {
			if (other.ingredientName != null)
				return false;
		} else if (!ingredientName.equals(other.ingredientName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Ingredient [ingredientId=" + ingredientId + ", ingredientName=" + ingredientName
				+ ", ingredientDescription=" + ingredientDescription + "]";
	}

}
