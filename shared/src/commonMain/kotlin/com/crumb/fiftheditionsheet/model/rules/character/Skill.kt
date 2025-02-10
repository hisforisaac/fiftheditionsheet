package com.crumb.fiftheditionsheet.model.rules.character

sealed class Skill(val governingAbility: Ability)
data object Athletics: Skill(Dexterity)
data object Acrobatics: Skill(Dexterity)
data object SleightOfHand: Skill(Dexterity)
data object Stealth: Skill(Dexterity)
data object Arcana: Skill(Intelligence)
data object History: Skill(Intelligence)
data object Investigation: Skill(Intelligence)
data object Nature: Skill(Intelligence)
data object Religion: Skill(Intelligence)
data object AnimalHandling: Skill(Wisdom)
data object Insight: Skill(Wisdom)
data object Medicine: Skill(Wisdom)
data object Perception: Skill(Wisdom)
data object Survival: Skill(Wisdom)
data object Deception: Skill(Charisma)
data object Intimidation: Skill(Charisma)
data object Performance: Skill(Charisma)
data object Persuasion: Skill(Charisma)

fun allSkills() = listOf(
    Athletics,
    Acrobatics,
    SleightOfHand,
    Stealth,
    Arcana,
    History,
    Investigation,
    Nature,
    Religion,
    AnimalHandling,
    Insight,
    Medicine,
    Perception,
    Survival,
    Deception,
    Intimidation,
    Performance,
    Persuasion
)