import java.util.List;

public interface FlightsDao {
	public List<Flights> getFlights();
	// return all flights
	public List<Flights> getFlights(String user);
	//return all flights assigned to a specific user
	
	public List<Flights> getFlights(int date);
	//return all flights assigned to a specific date
	
	public  void setFlights(int date);
	//Edit a flight's date
	
	public  void setFlights(String name);
	//Edit a flight's name
}
