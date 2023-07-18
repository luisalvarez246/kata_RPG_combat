package characters;

import java.util.Arrays;

public class Knight extends Character
{
    public int     range;
    public Knight()
    {
        range = 2;
        strength = strength + (level * 50);
    }

    public int isInRange(int[] enemyPosition)
    {
        int enemyAbsolutePosition;
        int selfAbsolutePosition;

        enemyAbsolutePosition = Arrays.stream(enemyPosition).sum();
        selfAbsolutePosition = Arrays.stream(position).sum();
        if (enemyAbsolutePosition - selfAbsolutePosition <= range)
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
