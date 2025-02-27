package com.futurespace.exercises.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.futurespace.exercises.model.UpdateUserDTO;
import com.futurespace.exercises.model.UserModel;

//
/* It is a good practice to use an interface instead of a class immediately.
 * In this way, our code is extensible, we can interchange different implementations, and it is
 * much clearer, since it indicates exactly which operations are available and what their contracts are.
 */
public interface UserService {
    List<UserModel> getAllUsers();
    Optional<UserModel> getUserById(String userId);
    UserModel createUser(String name, String firstSurname, String secondSurname, LocalDate birthDate, String sex);
    Optional<UserModel> updateUser(String userId, UpdateUserDTO updatedUser);
    boolean deleteUser(String userId);

}
