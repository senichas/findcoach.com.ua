package ua.com.findcoach.domain;


public interface User {

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getEmail();

    void setEmail(String email);

    boolean isActive();

    void setActive(boolean isActive);

    String getAlias();

    void setAlias(String alias);

    Sex getSex();

    void setSex(Sex sex);
}
