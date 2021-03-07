public class Personage {

    /*
    * Class Personage создает персонажа с присвоением  id, а так же name;
    * */

    private int id;
    private String name;

    public Personage(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Personage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
