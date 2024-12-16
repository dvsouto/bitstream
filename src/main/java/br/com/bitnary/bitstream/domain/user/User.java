package br.com.bitnary.bitstream.domain.user;

public class User {
    private String email;
    private String password;

    private String name;
    private Integer age;
    private Boolean active;

    public User(String email, String password, String name, Integer age, Boolean active) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.active = active;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
