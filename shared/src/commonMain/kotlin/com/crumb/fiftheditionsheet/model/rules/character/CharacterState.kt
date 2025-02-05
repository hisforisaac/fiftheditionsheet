package com.crumb.fiftheditionsheet.model.rules.character

data class CharacterState(
    val character: Character,
    val inventory: Inventory,
    val currentHitPoints: Int,
    val armorClass: Int,
)
