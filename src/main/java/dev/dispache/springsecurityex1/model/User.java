package dev.dispache.springsecurityex1.model;

public class User {
    private String email;
    private String password;
    private int age;
    private String role = "user";

    public User User() {
        return this;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }
    public User age(int age) {
        this.age = age;
        return this;
    }
    public User role(String role) {
        this.role = role;
        return this;
    }

    public User build() {
        return this;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return this.email + " " + this.password + " " + this.age + " " + this.role;
    }
}
