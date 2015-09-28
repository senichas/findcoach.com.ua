package ua.com.findcoach.domain;

import org.eclipse.persistence.annotations.Customizer;

import javax.persistence.*;

@Entity
@DiscriminatorValue("1")
public class Coach extends User {
}
