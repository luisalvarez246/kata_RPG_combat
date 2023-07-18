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
        assertEquals(2, knight.getRange());
    }

    @Test
    public void cannot_attack_out_of_range_targets()
    {
        //arrange
        knight.setPosition(new int[]{2, 2});
        thief.setPosition(new int[]{1, 4});
        //act, assert
        assertEquals("Cannot attack, enemy out of range", knight.dealDamage(thief));
    }

    @Test
    public void can_attack_in_range_targets()
    {
        //arrange
        knight.setPosition(new int[]{2, 2});
        thief.setPosition(new int[]{0, 2});
        //act, assert
        assertEquals("Enemy has lost " + knight.strength + " health points", knight.dealDamage(thief));
    }
    @Test
    public void cannot_attack_a_destroyed_target()
    {
        //arrange
        String  msg;
        thief.setHealth(0);
        //act
        msg = knight.dealDamage(thief);
        //assert
        assertEquals("You better be careful with anthrax, leave the dead alone!", msg);
    }
}