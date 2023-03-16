package de.tjackiz.gatewayService.model.entertainmentService.movie;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.tjackiz.gatewayService.model.common.AbstractBase;
import de.tjackiz.gatewayService.model.entertainmentService.actor.Actor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonSubTypes({@JsonSubTypes.Type(value = ActionMovie.class, name = "action"),
        @JsonSubTypes.Type(value = HorrorMovie.class, name = "horror")})
public abstract class AbstractMovie extends AbstractBase {

    @NotBlank
    private String name;

    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date releaseDate;

    @NotEmpty
    private Set<@Valid Actor> actorSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Actor> getActorSet() {
        return actorSet;
    }

    public void setActorSet(Set<Actor> actorSet) {
        this.actorSet = actorSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbstractMovie that = (AbstractMovie) o;
        return Objects.equals(name, that.name) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(actorSet, that.actorSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, releaseDate, actorSet);
    }

    @Override
    public String toString() {
        return "AbstractMovie{" +
                "name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", actorSet=" + actorSet +
                '}';
    }
}
