package com.javaprojekt.finalversionjavaproject.combat;

import com.javaprojekt.finalversionjavaproject.entity.Enemy;
import com.javaprojekt.finalversionjavaproject.entity.Entity;
import com.javaprojekt.finalversionjavaproject.entity.Player;
import com.javaprojekt.finalversionjavaproject.main.Background;

public class StartCombat extends Entity {
    Player player;
    Enemy enemy;

    public StartCombat(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public void commenceBattle() {
    }

    // Other combat-related methods
}
