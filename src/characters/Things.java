package characters;

import java.util.ArrayList;

public class Things extends Character
{
    boolean destroyed;
    public Things(int setHealth)
    {
        health = setHealth;
        faction = null;
        destroyed = false;
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
            destroyed = true;
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

    public boolean isDestroyed()
    {
        return (destroyed);
    }
}
