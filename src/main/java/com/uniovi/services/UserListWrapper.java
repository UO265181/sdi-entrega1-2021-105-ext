package com.uniovi.services;

import java.util.ArrayList;

import com.uniovi.entities.User;

public class UserListWrapper {

	
	private ArrayList<User> users;

	public UserListWrapper(ArrayList<User> users) {
		this.users = users;
	}
	
	public UserListWrapper() {
	}

	public ArrayList<User> getUsers() {
	    return users;
	}

	public void setUsers(ArrayList<User> users) {
	    this.users = users;
	}
	
	
}
