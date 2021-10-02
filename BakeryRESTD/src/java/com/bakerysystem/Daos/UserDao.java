/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Daos;

import com.bakerysystem.Model.User;
import java.util.ArrayList;

/**
 *
 * @author keoagile
 */
public interface UserDao {
    
    User getUser(String email, String password);
    boolean removeUser(int userID);
    boolean updateUser(User user);
    boolean addUser(User user);
    ArrayList<User> getAllUsers();

    public boolean updatePassword(String email);
    
}
