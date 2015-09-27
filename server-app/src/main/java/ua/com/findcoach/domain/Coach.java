package ua.com.findcoach.domain;

import org.eclipse.persistence.annotations.Customizer;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(name="is_coach")
@Customizer(CoachCustomizer.class)
public class Coach extends User {
}
