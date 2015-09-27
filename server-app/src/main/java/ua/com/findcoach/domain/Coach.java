package ua.com.findcoach.domain;

import org.eclipse.persistence.annotations.Customizer;

import javax.persistence.*;

//@Entity
//@DiscriminatorColumn(name="is_coach", discriminatorType=DiscriminatorType.INTEGER)
//@DiscriminatorValue("1")
public class Coach extends User {
}
