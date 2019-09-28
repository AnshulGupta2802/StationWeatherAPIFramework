package openweathermap;

public class RegisterStationResponse {

	private String id;
	private String created_at;
	private String updated_at;
	private String external_id;
	private String name;
	private Double latitude;
	private Double longitude;
	private int altitude;
	private int rank;
	
	public String getId(){
		return id;
	}
	
	public void setId(String id){
	this.id = id;
	}
	
	public String getCreated_at(){
		return created_at;
	}
	
	public void setCreated_at(String created_at){
	this.created_at = created_at;
	}
	
	public String getUpdated_at(){
		return updated_at;
	}
	
	public void setUpdated_at(String updated_at){
	this.updated_at = updated_at;
	}
	
	public String getExternal_id(){
		return external_id;
	}
	
	public void setExternal_id(String external_id){
	this.external_id = external_id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
	this.name = name;
	}
	
	public Double getLatitude(){
		return latitude;
	}
	
	public void setLatitude(Double latitude){
	this.latitude = latitude;
	}
	
	public Double getLongitude(){
		return longitude;
	}
	
	public void setLongitude(Double longitude){
	this.longitude = longitude;
	}
	
	public int getAltitude(){
		return altitude;
	}
	
	public void setAltitude(int altitude){
	this.altitude = altitude;
	}

	public int getRank(){
		return rank;
	}
	
	public void setRank(int rank){
	this.rank = rank;
	}
}
