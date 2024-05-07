package eu.lemondreams.security.feistal;

public class Key extends Block {
    int rounds;


    public Key(String s,int rounds){
        super(s);
        this.rounds=rounds;
    }



}
