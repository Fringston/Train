package fredrikkodar.entities;

import jakarta.persistence.*;

//Entity-klass som motsvarar tabellen exercises i databasen
@Entity
@Table(name = "exercises")
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public ExerciseEntity(String name) {this.name = name;}
    public ExerciseEntity() {}

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}


    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}