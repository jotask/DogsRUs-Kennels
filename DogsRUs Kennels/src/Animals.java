import java.util.ArrayList;


public class Animals {
	
	private String name;
	private ArrayList<Owner> originalOwners;
	private boolean likesBones;
	private String dogName;
	private String favFood;
	private int foodPerDay;
	
	public Animals(){
		
	}

	public String getName() {
		return dogName;
	}

	public void setName(String newName) {
		dogName = newName;
	}
	
	/**
	 * Returns a copy of the original owners
	 * @return A copy of the original owners as an array
	 */
	public Owner[] getOriginalOwners(){
		Owner[] result = new Owner[originalOwners.size()];
		result = originalOwners.toArray(result);
		return result;
	}

	/**
	 * Does the dog like bones?
	 * @return true if he does
	 */
	public boolean getLikesBones() {
		return likesBones;
	}
	/**
	 * How many times a day to feed the dog
	 * @param feeds The number of feeds per day
	 */
	public void setFeedsPerDay(int feeds){
		foodPerDay = feeds;
	}
	
	/**
	 * The number of feeds per day the dog is fed
	 * @return The number of feeds per day
	 */
	public int getFeedsPerDay(){
		return foodPerDay;
	}
	
	/**
	 * What's his favourite food?
	 * @param food The food he likes
	 */
	public void setFavouriteFood(String food){
		favFood = food;
	}
	
	/**
	 * The food the dog likes to eat
	 * @return The food 
	 */
	public String getFavouriteFood(){
		return favFood;
	}

}
