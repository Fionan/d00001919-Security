package security.Feistal;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import eu.lemondreams.security.Feistal.Block;
import org.junit.Test;

public class BlockTest {

    @Test
    public void testRotate() {
        Block block = new Block(true, true, false, false);
        Block rotatedBlock = block.rotate();
        boolean[] expectedValues = {false, false, true, true};

        assertArrayEquals(expectedValues, rotatedBlock.values);
    }

    @Test
    public void testLeft() {
        Block block = new Block(true, false, true, false);
        Block leftBlock = block.left();
        boolean[] expectedValues = {true, false};
        assertArrayEquals(expectedValues, leftBlock.values);
    }

    @Test
    public void testRight() {
        Block block = new Block(true, false, true, false);
        Block rightBlock = block.right();
        boolean[] expectedValues = {true, false};
        assertArrayEquals(expectedValues, rightBlock.values);
    }

    @Test
    public void testXOR() {
        Block rightSide = new Block(true, true,false,false);
        Block key = new Block(true, true,false,true);
        boolean[] expectedValues = {false, false, false, true};


        assertArrayEquals(expectedValues, rightSide.XOR(key).values);
    }

    @Test
    public void testJoin() {
        Block a = new Block(true, false);
        Block b = new Block(true, true);
        Block result = new Block(true, false, true, true);
        assertEquals(result, a.join(a, b));
    }

    @Test
    public void testShiftLeft() {
        Block block = new Block(true, false, false, false);
        Block shiftedBlock = block.shift(1, Block.Direction.LEFT);
        boolean[] expectedValues = {false, false, false, true};
        assertArrayEquals(expectedValues, shiftedBlock.values);
    }

    @Test
    public void testShiftRight() {
        Block block = new Block(true, false, true, false);
        Block shiftedBlock = block.shift(1, Block.Direction.RIGHT);
        boolean[] expectedValues = {false, true, false, true};
        assertArrayEquals(expectedValues, shiftedBlock.values);
    }
}
