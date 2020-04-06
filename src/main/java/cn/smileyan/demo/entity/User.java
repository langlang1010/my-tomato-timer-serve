package cn.smileyan.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author Smileyan
 */

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    @Column
    private String username;
    @Column
    @JsonIgnore
    private String password;
    @Column
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
