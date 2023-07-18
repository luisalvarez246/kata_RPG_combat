package tests;

import characters.Knight;
import characters.Things;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ThingsTest
{
    Things  box = new Things(2000);
    Knight  geralt = new Knight();

    @Test
    public void Things_cannot_be_healed()
    {
        //arrange
        String  msg;
        //act
        box.setHealth(400);
        msg = geralt.heal(box);
        //assert
        assertEquals("Things cannot heal or be healed!", msg);
        assertEquals(400, box.getHealth());
    }

    @Test
    public void Things_cannot_heal()
    {
        //arrange
        String  msg;
        //act
        geralt.setHealth(400);
        msg = box.heal(geralt);
        //assert
        assertEquals("Things cannot heal or be healed!", msg);
        assertEquals(400, geralt.getHealth());
    }

    @Test
    public void Things_cannot_deal_damage()
    {
        //arrange
        String  msg;
        int     geraltHealth;
        //act
        geraltHealth = geralt.getHealth();
        msg = box.dealDamage(geralt);
        //assert
        assertEquals("Things cannot deal damage!", msg);
        assertEquals(geraltHealth, geralt.getHealth());
    }

    @Test
    public void Things_can_be_dealt_damage()
    {
        //arrange
        String  msg;
        int     boxHealth;
        //act
        msg = geralt.dealDamage(box);
        boxHealth = box.getHealth();
        //assert
        assertEquals("Object was dealt " + geralt.strength + " points of damage", msg);
        assertTrue(2000 > boxHealth);
    }

    @Test
    public void Things_do_not_belong_to_factions()
    {
        //arrange
        ArrayList<String> msg;
        //act
        msg = box.getFaction();
        //assert
        assertNull(msg);
    }

    @Test
    public void Things_cannot_be_added_or_leave_a_faction()
    {
        //arrange
        String  msg;
        String  factionToJoin;
        //act
        factionToJoin = "Hall of Fame";
        msg = box.joinFaction(factionToJoin);
        //assert
        assertEquals("Things are neutral and cannot belong to a faction", msg);
        //act2
        msg = box.leaveFaction(factionToJoin);
        //assert2
        assertEquals("Things do not belong to any faction", msg);
    }

    @Test
    public void Things_can_be_destroyed()
    {
        //arrange
        String  msg;
        int     boxHealth;
        //act
        geralt.setStrength(3000);
        msg = geralt.dealDamage(box);
        boxHealth = box.getHealth();
        //assert
        assertEquals("Object has been destroyed", msg);
        assertTrue(box.isDestroyed());
    }
}