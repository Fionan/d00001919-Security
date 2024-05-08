package eu.lemondreams.security.Feistal;

public class SubKey extends Block{



   final Key MASTER_KEY;


    public SubKey(Key MASTER_KEY, String chosen){

        this.values = new boolean[chosen.length()];
        this.length = values.length;
        this.MASTER_KEY = MASTER_KEY;
        for (int i = 0; i < chosen.length(); i++) {



            //Account for Zero based indexing
            int chosenValue = Character.getNumericValue(chosen.charAt(i)) -1 ;

            values[i] = MASTER_KEY.values[chosenValue];


        }

    }

    public SubKey(Key MASTER_KEY, int[] chosen) {

        this.values = new boolean[chosen.length];
        this.length = values.length;
        this.MASTER_KEY = MASTER_KEY;

        for (int i = 0; i < chosen.length; i++) {
            int chosenValue = chosen[i]-1;


            values[i] = MASTER_KEY.values[chosenValue];


        }


    }





}
