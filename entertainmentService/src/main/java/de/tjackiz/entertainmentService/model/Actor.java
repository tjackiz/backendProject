package de.tjackiz.entertainmentService.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.tjackiz.entertainmentService.model.common.AbstractBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TABLE_ACTOR")
public class Actor extends AbstractBase {

    @NotBlank
    private String forename;

    @NotBlank
    private String surname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Temporal(TemporalType.DATE)
    @Past
    private Date birthdate;

    @Min(0)
    @Max(10)
    private int imdbRating;

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(int imdbRating) {
        this.imdbRating = imdbRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Actor actor = (Actor) o;
        return imdbRating == actor.imdbRating && Objects.equals(forename, actor.forename) && Objects.equals(surname, actor.surname) && Objects.equals(birthdate, actor.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), forename, surname, birthdate, imdbRating);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                ", imdbRating=" + imdbRating +
                '}';
    }
}
