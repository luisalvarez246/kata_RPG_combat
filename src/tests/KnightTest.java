package tests;

import characters.Character;
import characters.Knight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest
{
    Knight knight = new Knight();
    Character thief = new Character();

    @Test
    public void knight_max_range_is_2()
    {
        //act, assert
        assertTrue(knight instanceof Knight);
        assertEquals(2, knight.range);
    }

    @Test
    public void cannot_attack_out_of_range_targets()
    {
        //arrange
        knight.setPosition(new int[]{0, 5});
        thief.setPosition(new int[]{1, 7});
        //act, assert
        assertEquals("Cannot attack, enemy out of range", knight.dealDamage(thief));
    }

    @Test
    public void can_attack_in_range_targets()
    {
        //arrange
        knight.setPosition(new int[]{0, 5});
        thief.setPosition(new int[]{1, 6});
        //act, assert
        assertEquals("Enemy has lost " + knight.strength + " health points", knight.dealDamage(thief));
    }
}