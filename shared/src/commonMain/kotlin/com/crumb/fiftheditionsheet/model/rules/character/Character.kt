package com.crumb.fiftheditionsheet.model.rules.character

import com.crumb.fiftheditionsheet.model.rules.general.Die

data class Character(
    val name: String,
    val height: Int,
    val weight: Int,
    val baseSpeed: Int,
    val race: Race,
    val age: Int,
    val languages: Languages,
    val hitPointMax: Int,
    val hitDiceQuantity: Int,
    val hitDie: Die,
    val abilityScores: AbilityScores,
    val spells: Spells,
    val size: CreatureSize,
    val classLevels: List<ClassLevel>,
    val skills: Skills
)
