package com.crumb.fiftheditionsheet.model.rules.repository

import com.crumb.fiftheditionsheet.model.rules.character.Ability
import com.crumb.fiftheditionsheet.model.rules.character.AbilityScores
import com.crumb.fiftheditionsheet.model.rules.character.Character
import com.crumb.fiftheditionsheet.model.rules.character.ClassLevel
import com.crumb.fiftheditionsheet.model.rules.character.CharacterState
import com.crumb.fiftheditionsheet.model.rules.character.Charisma
import com.crumb.fiftheditionsheet.model.rules.character.Constitution
import com.crumb.fiftheditionsheet.model.rules.character.CreatureSize
import com.crumb.fiftheditionsheet.model.rules.character.Dexterity
import com.crumb.fiftheditionsheet.model.rules.character.Intelligence
import com.crumb.fiftheditionsheet.model.rules.character.Inventory
import com.crumb.fiftheditionsheet.model.rules.character.Languages
import com.crumb.fiftheditionsheet.model.rules.character.Race
import com.crumb.fiftheditionsheet.model.rules.character.Skill
import com.crumb.fiftheditionsheet.model.rules.character.Skills
import com.crumb.fiftheditionsheet.model.rules.character.Spells
import com.crumb.fiftheditionsheet.model.rules.character.Strength
import com.crumb.fiftheditionsheet.model.rules.character.ValidCharacterState
import com.crumb.fiftheditionsheet.model.rules.character.Wisdom
import com.crumb.fiftheditionsheet.model.rules.character.allSkills
import com.crumb.fiftheditionsheet.model.rules.general.D8
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

fun CharacterSheetStateRepository(): Flow<CharacterState> = flowOf(characterState())

fun characterState(): ValidCharacterState {
    val character = testCharacter()
    return ValidCharacterState(
        character = character,
        inventory = Inventory(),
        currentHitPoints = 63,
        armorClass = 14,
        skillModifiers = character.skillModifiers()
    )
}

private fun testCharacter() = Character(
    name = "Crumb",
    height = 65,
    weight = 115,
    baseSpeed = 60,
    race = Race("Elf"),
    age = 113,
    languages = Languages(),
    hitPointMax = 63,
    hitDiceQuantity = 1,
    hitDie = D8,
    abilityScores = AbilityScores(
        dexterity = 14,
        strength = 9,
        charisma = 18,
        constitution = 10,
        wisdom = 14,
        intelligence = 12
    ),
    spells = Spells(),
    size = CreatureSize(),
    classLevels = (1..7).map { ClassLevel("Bard", ) }.plus(ClassLevel("Cleric")),
    skills = Skills(emptyList())
)

fun Character.proficiencyBonus() = when(val level = classLevels.size) {
    in 1..4 -> 2
    in 5..16 -> 3
    in 17..Int.MAX_VALUE -> 6
    else -> throw IllegalStateException("Invalid clvl $level")
}
fun Character.skillModifiers(): Map<Skill, Int> {
    val proficiencies = skills.proficiencies.associate {(level, skill) -> skill to level }

    val proficiencyBonus = proficiencyBonus()
    val (dex, str, cha, con, wis, int) = abilityScores
    return allSkills().associateWith {
        val skillProficiencyBonus = proficiencies.getOrElse(it) { 0 } * proficiencyBonus

        when (it.governingAbility) {
            Charisma -> cha.toModifier()
            Constitution -> con.toModifier()
            Dexterity -> dex.toModifier()
            Intelligence -> int.toModifier()
            Strength -> str.toModifier()
            Wisdom -> wis.toModifier()
        }.plus(skillProficiencyBonus)
    }

}

private fun Int.toModifier() = minus(10).floorDiv(2)
