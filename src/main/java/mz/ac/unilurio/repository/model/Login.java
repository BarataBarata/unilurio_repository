package mz.ac.unilurio.repository.model;

public class Login {

    public static String email;
    public  static  String password;


    public Login( String email,String password) {
        this.password=password;
        this.email=email;
    }

    public Login() {

    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return ""+email+""+password;
    }
}
