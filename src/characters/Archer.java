package characters;

import java.util.Arrays;

public class Archer extends Character
{
    public int     range;
    public Archer()
    {
        range = 20;
        strength = strength + (level * 50);
    }

    public int isInRange(int[] enemyPosition)
    {
        int enemyAbsolutePosition;
        int selfAbsolutePosition;
        int result;

        enemyAbsolutePosition = Arrays.stream(enemyPosition).sum();
        selfAbsolutePosition = Arrays.stream(position).sum();
        result = enemyAbsolutePosition - selfAbsolutePosition;
        if ((result <= range) && (result > 1))
            return (1);
        return (0);
    }

    public String dealDamage(Character enemy)
    {
        if (isInRange(enemy.position) == 0)
            return ("Cannot attack, enemy out of range");
        return (super.dealDamage(enemy));
    }
}
