package com.futurespace.exercises;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.futurespace.exercises.controller.UserController;
import com.futurespace.exercises.model.UpdateUserDTO;
import com.futurespace.exercises.model.UserModel;
import com.futurespace.exercises.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    //We verify that the get call to /users, which calls the getUsers controller works correctly
    @Test
    public void getAllUsers_shouldReturnUserList() throws Exception {
        List<UserModel> userList = Arrays.asList(
            new UserModel("John", "Doe", "Smith", LocalDate.of(1990, 1, 1), "M", "12345"),
            new UserModel("Jane", "Doe", "Smith", LocalDate.of(1992, 2, 2), "F", "67890")
        );

        when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("John")))
                .andExpect(jsonPath("$[1].name", is("Jane")));
    }
    
    //We verify that the get call to /users/{id}, which calls the getUser controller works correctly when the user exists
    @Test
    public void getUserById_whenUserExists_shouldReturnUser() throws Exception{
        UserModel user = new UserModel("John", "Doe", "Smith", LocalDate.of(1990, 1, 1), "M", "12345");

        when(userService.getUserById("12345")).thenReturn(Optional.of(user));

        mockMvc.perform(get("/users/12345")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.firstSurname", is("Doe")));
    }

    //We verify that the get call to /users/{id}, which calls the getUser controller works correctly when the user does not exist
    @Test
    public void getUserById_whenUserDoesNotExist_shouldReturnNotFound() throws Exception {
        when(userService.getUserById("888")).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/888")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    //We verify that the post call to /users, which calls the createUser controller works correctly
    @Test
    public void createUser_withValidData_shouldReturnCreatedUser() throws Exception {
        UserModel newUser = new UserModel("John", "Doe", "Smith", LocalDate.of(1990, 1, 1), "M", "12345");

        when(userService.createUser("John", "Doe", "Smith", LocalDate.of(1990, 1, 1), "M")).thenReturn(newUser);

        mockMvc.perform(post("/users")
                .param("name", "John")
                .param("firstSurname", "Doe")
                .param("lastSurname", "Smith")
                .param("birthDate", "1990-01-01")
                .param("sex", "M")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.firstSurname", is("Doe")));
            
    }

    //We verify that the post call to /users, which calls the createUser controller with incorrect data works correctly
    @Test
    public void createUser_withInvalidData_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/users")
                .param("name", "")
                .param("firstSurname", "Doe")
                .param("lastSurname", "Smith")
                .param("birthDate", "1990-01-01")
                .param("sex", "M")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    //We verify that the put call to /users/{id}, which calls the updateUser controller works correctly when the user exists
    @Test
    public void updateUser_whenUserExists_shouldReturnUpdatedUser() throws Exception {
        UserModel existingUser = new UserModel("John", "Doe", "Smith", LocalDate.of(1990, 1, 1), "M", "12345");
        
        when(userService.updateUser(eq("12345"), any(UpdateUserDTO.class)))
                .thenReturn(Optional.of(new UserModel("Harry", "Potte", "Granger", LocalDate.of(1990, 1, 1), "M", "12345")));

        mockMvc.perform(put("/users/12345")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Harry\",\"firstSurname\":\"Potte\", \"secondSurname\":\"Granger\", \"birthDate\":\"1990-01-01\", \"sex\":\"M\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Harry")))
                .andExpect(jsonPath("$.firstSurname", is("Potte")));
    }

    //We verify that the put call to /users/{id}, which calls the updateUser controller works correctly when the user does not exist
    @Test
    public void updateUser_whenUserDoesNotExist_shouldReturnNotFound() throws Exception {
        when(userService.updateUser(eq("999"), any(UpdateUserDTO.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/users/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Harry\",\"firstSurname\":\"Potte\", \"secondSurname\":\"Granger\", \"birthDate\":\"1990-01-01\", \"sex\":\"M\"}"))
                .andExpect(status().isNotFound());
    }

    //We verify that the delete call to /users/{id}, which calls the deleteUser controller works correctly when the user exists
    @Test
    public void deleteUser_whenUserExists_shouldReturnOk() throws Exception {
        when(userService.deleteUser("12345")).thenReturn(true);

        mockMvc.perform(delete("/users/12345"))
                .andExpect(status().isOk());
    }

    //We verify that the delete call to /users/{id}, which calls the deleteUser controller works correctly when the user does not exist
    @Test
    public void deleteUser_whenUserDoesNotExist_shouldReturnNotFound() throws Exception {
        when(userService.deleteUser("999")).thenReturn(false);

        mockMvc.perform(delete("/users/999"))
                .andExpect(status().isNotFound());
    }
        
}
