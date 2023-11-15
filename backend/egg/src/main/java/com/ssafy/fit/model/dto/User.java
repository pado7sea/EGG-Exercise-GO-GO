package com.ssafy.fit.model.dto;

public class User {
	private String id;
	private String password;
	private String name;
	private String birthday;
	private int egg_count;

	public int getEgg_count() {
		return egg_count;
	}

	public void setEgg_count(int egg_count) {
		this.egg_count = egg_count;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public User() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
