package ua.com.findcoach.domain;

import org.eclipse.persistence.annotations.Customizer;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(name = "is_padawan")
@Customizer(Padawan.class)
public class Padawan extends User {
}
