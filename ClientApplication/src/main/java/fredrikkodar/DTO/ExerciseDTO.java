package fredrikkodar.DTO;

public class ExerciseDTO {

    private long id;
    private String name;

    public ExerciseDTO(String name) {this.name = name;}
    public ExerciseDTO() {}

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