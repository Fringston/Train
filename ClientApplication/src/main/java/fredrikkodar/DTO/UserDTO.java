package fredrikkodar.DTO;


public class UserDTO {


    private long id;
    private String name;
    private String password;

    public UserDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public UserDTO() {}

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password +
                '}';
    }
}
