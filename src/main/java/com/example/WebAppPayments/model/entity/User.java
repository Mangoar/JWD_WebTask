package com.example.WebAppPayments.model.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * The class of user
 */
public class User implements Serializable {

    private int id;
    private int idRole;
    private int idPhoto;
    private String login;
    private String password;
    private String fullname;
    private String birthdate;
    private String passport;
    private String email;
    private int blocked;
    private String photoPath;

    public User() {
    }

    public User(int id, int idRole, int idPhoto, String login, String password, String fullname, String birthdate, String passport, String email, int blocked) {
        this.id = id;
        this.idRole = idRole;
        this.idPhoto = idPhoto;
        this.login = login;
        this.password = password;
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.passport = passport;
        this.email = email;
        this.blocked = blocked;
    }

    public User(String login, String password, String fullname, String birthdate, String passport, String email) {
        this.login = login;
        this.password = password;
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.passport = passport;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public int getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBlocked() {
        return blocked;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && idRole == user.idRole && idPhoto == user.idPhoto && login.equals(user.login) && passport.equals(user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idRole, idPhoto, login, passport);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", id_role=" + idRole +
                ", id_photo=" + idPhoto +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", passport='" + passport + '\'' +
                ", email='" + email + '\'' +
                ", blocked=" + blocked +
                '}';
    }
}
