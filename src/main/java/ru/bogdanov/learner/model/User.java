package ru.bogdanov.learner.model;

import org.springframework.util.CollectionUtils;

import java.util.*;

import static ru.bogdanov.learner.util.LessonUtil.DEFAULT_DAILY_GOAL;

/**
 * Denis, 16.09.2018
 */
public class User extends AbstractNamedEntity {

    private String email;

    private String password;

    private boolean enabled = true;

    private Date registrationDate = new Date();

    private Set<Role> roles;

    private int dailyGoal = DEFAULT_DAILY_GOAL;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getDailyGoal(), u.isEnabled(), u.getRegistrationDate(), u.getRoles());
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, DEFAULT_DAILY_GOAL, true, new Date(), EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, int dailyGoal, boolean enabled, Date registrationDate, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.dailyGoal = dailyGoal;
        this.enabled = enabled;
        this.registrationDate = registrationDate;
        setRoles(roles);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getDailyGoal() {
        return dailyGoal;
    }

    public void setDailyGoal(int dailyGoal) {
        this.dailyGoal = dailyGoal;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", email=" + email +
                ", name=" + name +
                ", enabled=" + enabled +
                ", roles=" + roles +
                ", dailyGoal=" + dailyGoal +
                ')';
    }
}
