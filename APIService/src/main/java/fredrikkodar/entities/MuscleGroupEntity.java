package fredrikkodar.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "muscle_groups")
public class MuscleGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "muscle_group", referencedColumnName = "id")
    List<ExerciseEntity> exercises = new ArrayList<>();

    public MuscleGroupEntity(String name) {this.name = name;}
    public MuscleGroupEntity() {}

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public List<ExerciseEntity> getExercises() {return exercises;}

    public void setExercises(List<ExerciseEntity> exercises) {this.exercises = exercises;}


    @Override
    public String toString() {
        return "MuscleGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}