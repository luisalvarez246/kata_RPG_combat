package tests;

import characters.Archer;
import characters.Knight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArcherTest
{
    Archer  archer = new Archer();
    Knight  knight = new Knight();

    @Test
    public void archer_max_range_is_20()
    {
        //act, assert
        assertTrue(archer instanceof Archer);
        assertEquals(20, archer.range);
    }

    @Test
    public void cannot_attack_out_of_range_targets()
    {
        //arrange
        archer.setPosition(new int[]{0, 5});
        knight.setPosition(new int[]{6, 20});
        //act, assert
        assertEquals("Cannot attack, enemy out of range", archer.dealDamage(knight));
        knight.setPosition(new int[]{1, 5});
        assertEquals("Cannot attack, enemy out of range", archer.dealDamage(knight));
    }

    @Test
    public void can_attack_in_range_targets()
    {
        //arrange
        archer.setPosition(new int[]{0, 5});
        knight.setPosition(new int[]{6,19});
        //act, assert
        assertEquals("Enemy has lost " + archer.strength + " health points", archer.dealDamage(knight));
        knight.setPosition(new int[]{2, 5});
        assertEquals("Enemy has lost " + archer.strength + " health points", archer.dealDamage(knight));
    }

}