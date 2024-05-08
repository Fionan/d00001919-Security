package eu.lemondreams.security.Feistal;

import java.util.Arrays;

public class Block {
   public enum Direction{
        RIGHT,LEFT;

    }
    public boolean[] values;
    int length;


    public Block(String s) {
       values = new boolean[s.length()];
    length = values.length;

        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            values[i] = c == '1';


        }
    }




    public Block(boolean... values) {

        this.length = values.length;
        this.values = values;

    }


    public Block rotate() {


        Block left = left();
        Block right = right();


        return join(right, left);
    }

    public Block left() {

        int mid = length / 2;

        boolean[] left = new boolean[mid];
        System.arraycopy(values, 0, left, 0, mid);

        return new Block(left);


    }


    public Block right() {
        int mid = length / 2;

        boolean[] rightValues = new boolean[length - mid];

        if (length - mid >= 0) System.arraycopy(values, mid, rightValues, mid - mid, length - mid);

        return new Block(rightValues);
    }


    public Block XOR(Block key) {

        boolean[] xord = new boolean[length];

        for (int i = length-1; i >= 0; i--) {
            if (key.values[i] == values[i]) {

                xord[i] = false;

            } else {
                xord[i] = true;
            }


        }


        return new Block(xord);
    }


    public static Block join(Block a, Block b) {
        int combinedLength = a.length + b.length;

        boolean[] combinedValues = new boolean[combinedLength];

        // Copy values from block a
        for (int i = 0; i < a.length; i++) {
            combinedValues[i] = a.values[i];
        }

        // Copy values from block b
        for (int i = 0; i < b.length; i++) {
            combinedValues[a.length + i] = b.values[i];
        }

        return new Block(combinedValues);
    }

   public Block shift(int rotations,Direction direction) {



        String blockAsString=this.toString();
       for (int i = 0; i < rotations; i++) {
           if(direction==Direction.LEFT){

               blockAsString = blockAsString.substring(1)+blockAsString.charAt(0);

           }else{

               blockAsString = blockAsString.charAt(blockAsString.length()-1)+blockAsString.substring(0,length-1);

           }


       }




        return new Block(blockAsString);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Block block = (Block) o;

        if (length != block.length) return false;
        return Arrays.equals(values, block.values);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(values);
        result = 31 * result + length;
        return result;
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (boolean b : values) {
            if (b) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
    return  sb.toString();

    }


}
