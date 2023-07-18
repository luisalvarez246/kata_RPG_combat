package characters;

import java.util.ArrayList;

public class Things extends Character
{
    public Things(int setHealth)
    {
        health = setHealth;
        faction = null;
        setDestroyed(false);
    }

    @Override
    public String receiveDamage(int damage)
    {
        if (damage < health)
        {
            health = health - damage;
            return ("Object was dealt " + damage + " points of damage");
        }
        else
        {
            health = 0;
            setDestroyed(true);
            return ("Object has been destroyed");
        }
    }

    @Override
    public String joinFaction(String setValue)
    {
        return ("Things are neutral and cannot belong to a faction");
    }

    @Override
    public String leaveFaction(String setValue)
    {
        return ("Things do not belong to any faction");
    }
}
