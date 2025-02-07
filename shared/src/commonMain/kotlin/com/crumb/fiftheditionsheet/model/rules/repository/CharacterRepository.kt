package com.crumb.fiftheditionsheet.model.rules.repository

import com.crumb.fiftheditionsheet.model.rules.character.AbilityScores
import com.crumb.fiftheditionsheet.model.rules.character.Character
import com.crumb.fiftheditionsheet.model.rules.character.CharacterClass
import com.crumb.fiftheditionsheet.model.rules.character.CharacterState
import com.crumb.fiftheditionsheet.model.rules.character.CreatureSize
import com.crumb.fiftheditionsheet.model.rules.character.Inventory
import com.crumb.fiftheditionsheet.model.rules.character.Languages
import com.crumb.fiftheditionsheet.model.rules.character.Race
import com.crumb.fiftheditionsheet.model.rules.character.Skills
import com.crumb.fiftheditionsheet.model.rules.character.Spells
import com.crumb.fiftheditionsheet.model.rules.character.ValidCharacterState
import com.crumb.fiftheditionsheet.model.rules.general.D8
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

fun CharacterSheetStateRepository(): Flow<CharacterState> = flowOf(characterState())

private fun characterState() = ValidCharacterState(
    character = testCharacter(),
    inventory = Inventory(),
    currentHitPoints = 63,
    armorClass = 14
)

private fun testCharacter() = Character(
    name = "Crumb",
    height = 65,
    weight = 115,
    speed = 60,
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
    classLevels = (1..7).map { CharacterClass("Bard", ) }.plus(CharacterClass("Cleric")),
    skills = Skills(emptyList())
)