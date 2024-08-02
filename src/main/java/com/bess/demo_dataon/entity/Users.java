package com.bess.demo_dataon.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name="users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "use_id")
    private int id;
    @Column(name = "use_name",length = 256)
    private String userName;
    @Column(name = "use_password", length = 512)
    private String passWord;
    @Column(name = "use_email",length = 100)
    private String email;
    @Column(name = "use_phone",length = 50)
    private String phone;
    @Column(name = "use_birthdays")
    private Date birthdays;

    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
        }
    )
    @JoinTable(
            name = "users_role",
            joinColumns = @JoinColumn(name = "use_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roleList;

    public Users(String userName, String email, String phone, Date birthdays,String passWord) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.birthdays = birthdays;
        this.passWord= passWord;
    }

    public Users() {}

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirthdays(Date birthdays) {
        this.birthdays = birthdays;
    }

    public int getId() {
        return this.id;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public Date getBirthdays() {
        return this.birthdays;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
