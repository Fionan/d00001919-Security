package eu.lemondreams.security.RSA.DiffieHelman;

public class User{

    String name;
    double p;
    double g;

    long secret;

    double received =0;


    public User(String name, double g, double p, long secret) {
        this.name = name;
        this.p = p;
        this.g = g;
        this.secret = secret;
    }

    public  double diffie_hellman(){

        double val = Math.pow(g,secret);

        return val% p;

    }

    public void setReceived(double received) {
        this.received = received;
    }

    public void sendToUser(User u,double val){
        u.setReceived(val);

    }


    public double checkDiffie_hellman(){
        double chk = Math.pow(received,secret);
        double calc = chk % p;

        return calc;


    }
}
