package tests;

import characters.Character;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest
{
    Character character = new Character();

    // Iteration 1
    @Test
    public void characters_when_created_have_health_starting_at_1000()
    {
       assertTrue(character.getHealth() >= 1000);
    }

    @Test
    public void characters_when_created_have_level_starting_at_1()
    {
       assertTrue(character.getLevel() >= 1);
    }

    @Test
    public void characters_when_created_start_alive()
    {
       assertTrue(character.isAlive());
    }

    /* @Test Pending Test
    public void characters_can_deal_damage_to_characters()
    {
       assertTrue(character.isAlive());
    }*/

    @Test
    public void when_damage_received_exceeds_current_health_health_becomes_0_and_the_character_dies()
    {
        character.receiveDamage(character.getHealth());
        assertFalse(character.isAlive());
    }

    /* @Test Pending Test
    public void characters_can_heal_other_characters()
    {
       assertTrue(character.isAlive());
    }*/

    @Test
    public void dead_characters_cannot_be_healed()
    {
        //arrange
        String msg;
        character.receiveDamage(character.getHealth());
        //act
        msg = character.heal(character);
        //assert
        assertEquals("Dead characters cannot be healed!", msg);
    }

    @Test
    public void characters_with_1000_HP_or_more_cannot_be_healed()
    {
        //arrange
        String msg;
        //act
        msg = character.heal(character);
        //assert
        assertEquals("Cannot heal a character with health >= 1000", msg);
    }

    @Test
    public void healing_cannot_raise_HP_above_1000()
    {
        //arrange
        String  msg;
        int     receivedHealth;
        character.receiveDamage(200);
        //act
        receivedHealth = 1000 - character.getHealth();
        msg = character.receiveHealth(400);
        //asser
        assertEquals(1000, character.getHealth());
        assertEquals("Character received " + receivedHealth + " points of health!", msg);
    }

    @Test
    public void healing_increases_HP_by_HPUP()
    {
        //arrange
        String  msg;
        int     receivedHealth;
        character.receiveDamage(500);
        //act
        receivedHealth = 999 - character.getHealth();
        msg = character.receiveHealth(receivedHealth);
        //asser
        assertEquals("Character recovered " + receivedHealth + " HP", msg);
    }

    // Iteration 2

    Character testSubjectA = new Character();
    Character testSubjectB = new Character();

    @Test
    public void A_character_cannot_deal_damage_to_itself()
    {
        //arrange
        String msg;
        //act
        msg = testSubjectA.dealDamage(testSubjectA);
        //assert
        assertEquals("Error, a Character cannot damage itself", msg);
    }

    @Test
    public void A_character_can_only_heal_itself()
    {
        //arrange
        String msg;
        //act
        msg = testSubjectA.heal(testSubjectB);
        //assert
        assertEquals("Error, a Character can only heal itself or an ally", msg);
    }

    @Test
    public void If_the_target_is_5_or_more_Levels_above_the_attacker_Damage_is_cut_in_half()
    {
        //arrange
        String  msg;
        int     strength;

        testSubjectA.setLevel(1);
        strength = 400;
        testSubjectA.setStrength(strength);
        testSubjectB.setLevel(6);
        //act
        msg = testSubjectA.dealDamage(testSubjectB);
        //assert
        assertEquals("Enemy has lost " + (strength / 2) + " health points", msg);
    }

    @Test
    public void If_the_target_is_5_or_more_Levels_below_the_attacker_Damage_is_doubled()
    {
        //arrange
        String  msg;
        int     strength;

        testSubjectA.setLevel(6);
        strength = 400;
        testSubjectA.setStrength(strength);
        testSubjectB.setLevel(1);
        //act
        msg = testSubjectA.dealDamage(testSubjectB);
        //assert
        assertEquals("Enemy has lost " + (strength * 2) + " health points", msg);
    }

    @Test
    public void newly_created_characters_belong_to_no_faction()
    {
        //arrange
        ArrayList<String> result = testSubjectA.getFaction();
        ArrayList<String> expected = new ArrayList<>();
        //act, assert
        assertEquals(expected, result);
    }

    @Test
    public void A_Character_may_join_one_or_more_factions()
    {
        //arrange
        testSubjectA.joinFaction("Triforce Heroes");
        testSubjectA.joinFaction("Ice Climbers");
        //act, assert
        assertEquals(2, testSubjectA.getFaction().size());
        assertEquals("Triforce Heroes", testSubjectA.getFaction().get(0));
    }

    @Test
    public void A_Character_may_leave_one_or_more_factions()
    {
        //arrange
        String      msg;
        String[]    setFaction = {"Triforce Heroes", "Ice Climbers"};
        testSubjectA.joinFaction(setFaction[0]);
        testSubjectA.joinFaction(setFaction[1]);
        //act
        msg = testSubjectA.leaveFaction(setFaction[1]);
        // assert
        assertEquals("Character is no longer part of " + setFaction[1], msg);
        assertEquals(1, testSubjectA.getFaction().size());
        //act2
        msg = testSubjectA.leaveFaction(setFaction[0]);
        //assert2
        assertEquals("Character is no longer part of " + setFaction[0], msg);
        assertEquals(0, testSubjectA.getFaction().size());
    }

    @Test
    public void characters_that_belong_to_the_same_faction_are_allies()
    {
        //arrange
        boolean     isAlly;
        String[]    setFaction = {"Triforce Heroes", "Ice Climbers"};
        testSubjectA.joinFaction(setFaction[0]);
        testSubjectB.joinFaction(setFaction[1]);
        testSubjectB.joinFaction(setFaction[0]);
        //act
        isAlly = testSubjectA.isAlly(testSubjectB.getFaction());
        //assert
        assertTrue(isAlly);
        //act2
        testSubjectB.leaveFaction(setFaction[0]);
        isAlly = testSubjectA.isAlly(testSubjectB.getFaction());
        //assert2
        assertFalse(isAlly);
    }

    @Test
    public void allies_cannot_deal_damage_to_one_another()
    {
        //arrange
        int         targetHealth;
        String      msg;
        String[]    setFaction = {"Triforce Heroes", "Ice Climbers"};

        testSubjectA.joinFaction(setFaction[0]);
        testSubjectB.joinFaction(setFaction[1]);
        testSubjectB.joinFaction(setFaction[0]);
        //act
        testSubjectB.setHealth(1200);
        targetHealth = testSubjectB.getHealth();
        msg = testSubjectA.dealDamage(testSubjectB);
        //assert
        assertEquals("Friendly fire will not be tolerated!", msg);
        assertEquals(1200, targetHealth);
    }

    @Test
    public void allies_can_heal_one_another()
    {
        //arrange
        int         targetHealth;
        String      msg;
        String[]    setFaction = {"Triforce Heroes", "Ice Climbers"};

        testSubjectA.joinFaction(setFaction[0]);
        testSubjectB.joinFaction(setFaction[1]);
        testSubjectB.joinFaction(setFaction[0]);
        //act
        testSubjectB.setHealth(500);
        msg = testSubjectA.heal(testSubjectB);
        targetHealth = testSubjectB.getHealth();
        //assert
        assertEquals("Character recovered " + testSubjectA.getSelfHeal() + " HP", msg);
        assertEquals(600, targetHealth);
    }

    @Test
    public void character_can_still_heal_themselves()
    {
        //arrange
        int         targetHealth;
        String      msg;

        //act
        testSubjectA.setHealth(500);
        msg = testSubjectA.heal(testSubjectA);
        targetHealth = testSubjectA.getHealth();
        //assert
        assertEquals("Character recovered " + testSubjectA.getSelfHeal() + " HP", msg);
        assertEquals(600, targetHealth);
    }
}