package characters;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Character
{
    int                 health;
    int                 level;
    boolean             alive;
    public int          strength;
    int                 selfHeal;
    int[]               position = {0, 0};
    int                 range;
    private boolean     destroyed;
    String              msg;
    ArrayList<String>   faction = new ArrayList<>();
    SecureRandom        rand = new SecureRandom();

    public Character()
    {
        int randHealth = rand.nextInt(1) + 1000;
        int randLevel = rand.nextInt(1) + 1;

        health = randHealth;
        level = randLevel;
        alive = true;
        selfHeal = 100;
    }

    public String receiveDamage(int damage)
    {
        if (damage < health)
        {
            health = health - damage;
            return ("Enemy has lost " + damage + " health points");
        }
        else
        {
            health = 0;
            alive = false;
            return ("Enemy has been killed");
        }
    }

    public String receiveHealth(int healthUp)
    {
        if (health + healthUp > 1000)
        {
            int receivedHealth = 1000 - health;
            health = 1000;
            return("Character received " + receivedHealth + " points of health!");
        }
        else
        {
            health = health + healthUp;
            return ("Character recovered " + healthUp + " HP");
        }
    }

    public int isInRange(int[] enemyPosition)
    {
        int distance;

        distance = Math.abs(enemyPosition[0] - position[0]) + Math.abs(enemyPosition[1] - position[1]);
        if ((this instanceof Archer) && (distance == 1))
            return (0);
        if (distance <= range)
            return (1);
        return (0);
    }
    public int calculateDamage(int enemyLevel)
    {
        if (enemyLevel - level >= 5)
            return (strength / 2);
        else if (level - enemyLevel >= 5)
            return (strength * 2);
        else
            return (strength);
    }

    public int canDealDamage(Character target)
    {
        if (!alive)
        {
            msg = "This is not a zombie's game, thus the dead cannot attack";
            return (0);
        }
        if (!target.alive)
        {
            msg = "You better be careful with anthrax, leave the dead alone!";
            return (0);
        }
        if ((target instanceof Things) && (target.destroyed))
        {
            msg = "You should go see a shrink dude, leave it with the damned thing";
            return (0);
        }
        if (this instanceof Things)
        {
            msg = "Things cannot deal damage!";
            return (0);
        }
        if (this == target)
        {
            msg = "Error, a Character cannot damage itself";
            return (0);
        }
        if (isAlly(target.getFaction()))
        {
            msg = "Friendly fire will not be tolerated!";
            return (0);
        }
        if (isInRange(target.position) == 0)
        {
            msg = "Cannot attack, enemy out of range";
            return (0);
        }
        return (1);
    }

    public String dealDamage(Character target)
    {
        int damage;

        if (canDealDamage(target) == 0)
            return (msg);
        damage = calculateDamage(target.level);
        return (target.receiveDamage(damage));
    }

    public int isHealingPossible(Character target)
    {
        if ((target instanceof Things) || (this instanceof Things))
        {
            msg = "Things cannot heal or be healed!";
            return (0);
        }
        else if (!target.alive)
        {
            msg = "Dead characters cannot be healed!";
            return (0);
        }
        else if ((!isAlly(target.getFaction())) && (this != target))
        {
            msg = "Error, a Character can only heal itself or an ally";
            return (0);
        }
        else if (target.health >= 1000)
        {
            msg = "Cannot heal a character with health >= 1000";
            return (0);
        }
        return (1);
    }
    public String heal(Character target)
    {
        if (isHealingPossible(target) == 0)
            return (msg);
        return (target.receiveHealth(selfHeal));
    }

    public String joinFaction(String setValue)
    {
        faction.add(setValue);
        return ("Character is now member of " + setValue + " and has swore to defend his crew");
    }

    public String leaveFaction(String setValue)
    {
        for (int i = 0; i < faction.size(); i++)
        {
            if (Objects.equals(setValue, faction.get(i)))
            {
                faction.remove(i);
                return ("Character is no longer part of " + setValue);
            }
        }
        return ("Character was not a member of " + setValue);
    }

    public boolean isAlly(ArrayList<String> targetFactions)
    {
        for (String s : faction)
        {
            for (String targetFaction : targetFactions)
            {
                if (Objects.equals(s, targetFaction))
                    return (true);
            }
        }
        return (false);
    }

    public void updateStatus()
    {
        if (health <= 0 && (this instanceof Things))
            destroyed = true;
        else if (health <= 0)
            alive = false;
    }

    public int getHealth()
    {
        return (health);
    }

    public int getLevel()
    {
        return (level);
    }

    public boolean isAlive()
    {
        return (alive);
    }

    public ArrayList<String> getFaction()
    {
        return(faction);
    }

    public int getSelfHeal()
    {
        return (selfHeal);
    }

    public int getRange()
    {
        return (range);
    }

    public void setStrength(int setValue)
    {
        strength = setValue;
    }

    public void setLevel(int setValue)
    {
        level = setValue;
    }

    public void setPosition(int[] setValue)
    {
        position = setValue;
    }

    public void setHealth(int setValue)
    {
        health = setValue;
        if (health <= 0)
            updateStatus();
    }

    public void setDestroyed(boolean setValue)
    {
        destroyed = setValue;
    }

    public boolean isDestroyed()
    {
        return (destroyed);
    }
}
