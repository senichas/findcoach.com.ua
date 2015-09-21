package ua.com.findcoach.domain;

import javax.persistence.*;

@Entity
@Table(name="coach")
@DiscriminatorColumn(name="is_coach", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("1")
public class Coach extends User {
}
