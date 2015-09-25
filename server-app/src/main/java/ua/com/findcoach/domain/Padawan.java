package ua.com.findcoach.domain;

import org.eclipse.persistence.annotations.Customizer;

import javax.persistence.*;

@Entity
@Table(name = "padawan")
@DiscriminatorColumn(name = "is_padawan")
@Customizer(Padawan.class)
public class Padawan extends User {
}
