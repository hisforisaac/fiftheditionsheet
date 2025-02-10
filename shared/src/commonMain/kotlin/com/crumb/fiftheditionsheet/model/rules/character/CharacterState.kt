package com.crumb.fiftheditionsheet.model.rules.character
sealed class CharacterState
data class IncompleteCharacter(val name: String): CharacterState()

data class ValidCharacterState(
    val character: Character,
    val inventory: Inventory,
    val currentHitPoints: Int,
    val armorClass: Int,
    val skillModifiers: SkillModifiers,
): CharacterState()
