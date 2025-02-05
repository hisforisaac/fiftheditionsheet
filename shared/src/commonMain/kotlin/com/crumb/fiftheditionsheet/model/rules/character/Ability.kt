package com.crumb.fiftheditionsheet.model.rules.character

sealed class Ability
data object Charisma: Ability()
data object Constitution: Ability()
data object Dexterity: Ability()
data object Intelligence: Ability()
data object Wisdom: Ability()
data object Strength: Ability()

