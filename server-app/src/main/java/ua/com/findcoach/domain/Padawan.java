package ua.com.findcoach.domain;

import javax.persistence.*;

@Entity
@Table(name = "padawan")
@DiscriminatorColumn(name = "is_padawan", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("1")
public class Padawan extends User {
}
