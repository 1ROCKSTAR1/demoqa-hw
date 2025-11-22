package api;

public class UserCreateData {

    private String name;
    private String job;

    public UserCreateData(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public UserCreateData(){
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}
