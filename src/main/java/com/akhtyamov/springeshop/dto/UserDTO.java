package com.akhtyamov.springeshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String username;
    private String password;
    private String matchingPassword;
    private String email;

//    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getMatchingPassword() {
//        return matchingPassword;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setMatchingPassword(String matchingPassword) {
//        this.matchingPassword = matchingPassword;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}
