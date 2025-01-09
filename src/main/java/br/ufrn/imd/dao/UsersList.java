package br.ufrn.imd.dao;

import br.ufrn.imd.model.User;

import java.util.ArrayList;

public class UsersList {

    private ArrayList<User> users;

    public UsersList() {
        this.users = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void addUser(User u) {
        users.add(u);
    }

    public User findUser(String userName, String userPassword) throws UserException {
        User user = new User();
        for (User u : users){
            if (u.getUsername().equals(userName)){
                if (u.getPassword().equals(userPassword)){
                    user.setUsername(u.getUsername());
                    user.setPassword(u.getPassword());
                    user.setMaxScore(u.getMaxScore());
                    return user;
                }
                throw new UserException("Incorrect password");
            }
        }
        throw new UserException("User not found");
    }
}
