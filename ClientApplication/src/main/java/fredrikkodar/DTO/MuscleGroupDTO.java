package fredrikkodar.DTO;

import java.util.ArrayList;
import java.util.List;

public class MuscleGroupDTO {

    private long id;
    private String name;
    List<ExerciseDTO> exercises = new ArrayList<>();

    public MuscleGroupDTO(String name) {this.name = name;}
    public MuscleGroupDTO() {}

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public List<ExerciseDTO> getExercises() {return exercises;}

    public void setExercises(List<ExerciseDTO> exercises) {this.exercises = exercises;}


    @Override
    public String toString() {
        return "MuscleGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
