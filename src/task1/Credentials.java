package task1;

public class Credentials { // implementata respectand principiul incapsularii
    private String email;
    private String parola;

    public Credentials(String email, String parola) {
        this.email = email;
        this.parola = parola;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setParola(String parola) {
        this.parola = parola;
    }
    public String getEmail() {
        return email;
    }
    public String getParola() {
        return parola;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "email='" + email + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }
}
