import java.util.ArrayList;


public class Animals {
	
	public String name;
	public ArrayList<Owner> originalOwners;
	public String favFood;
	public int foodPerDay;
	
	public Animals(String n, ArrayList<Owner> owners, String food, int mealsPerDay){

		name = n;
		originalOwners = new ArrayList<Owner>();
		
		// We make a true copy of the owners ArrayList to make sure that we
		// don't break encapsulation: i.e. don't share object references with
		// other code
		for(Owner o: owners){
			Owner copy = new Owner(o.getName(), o.getPhone());
			originalOwners.add(copy);
		}
		
		this.favFood = food;
		this.foodPerDay = mealsPerDay;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
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
