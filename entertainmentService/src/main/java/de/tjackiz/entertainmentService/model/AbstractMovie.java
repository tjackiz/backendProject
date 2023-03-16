package de.tjackiz.entertainmentService.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.tjackiz.entertainmentService.model.common.AbstractBase;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "@class")
@JsonSubTypes({@JsonSubTypes.Type(value = ActionMovie.class, name = "action"),
        @JsonSubTypes.Type(value = HorrorMovie.class, name = "horror")})
// use entity instead of mappedSuperClass as this will override inheritance strategy
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractMovie extends AbstractBase {

    @NotBlank
    private String name;

    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @ManyToMany
    @JoinTable(name = "table_abstract_movie_actors",
            joinColumns = @JoinColumn(name = "abstract_movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actors_id"))
    private Set<@Valid Actor> actorSet = new LinkedHashSet<>();

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
