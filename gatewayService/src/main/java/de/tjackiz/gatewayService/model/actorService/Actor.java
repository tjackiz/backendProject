package de.tjackiz.gatewayService.model.actorService;

import de.tjackiz.gatewayService.model.common.AbstractBase;

import java.util.Date;
import java.util.Objects;

public class Actor extends AbstractBase {

    private String forename;

    private String surname;

    private Date birthdate;

    // 0-10
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
