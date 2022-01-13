package cybersoft.javabackend.gamedoanso.model;

public class Player {
	private String name;
	private String username;
	private String password;
	
	public Player() {
		
	}
	
	public Player(String username, String password) {
		this.password = password;
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
