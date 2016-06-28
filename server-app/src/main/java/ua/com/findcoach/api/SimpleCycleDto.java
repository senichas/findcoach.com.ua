package ua.com.findcoach.api;

import org.hibernate.validator.constraints.NotEmpty;

public class SimpleCycleDto {
    @NotEmpty
    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
