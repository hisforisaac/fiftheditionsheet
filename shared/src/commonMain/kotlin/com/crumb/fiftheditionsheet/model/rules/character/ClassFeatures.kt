package com.crumb.fiftheditionsheet.model.rules.character

import com.crumb.fiftheditionsheet.model.rules.general.Die
import com.crumb.fiftheditionsheet.model.rules.general.ToolType
import com.crumb.fiftheditionsheet.model.rules.general.WeaponType

data class ClassFeatures(
    val hitDiePerLevel: Die,
    val hitPointBaseAtFirstLevel: Int,
    val hitPointModifierAbility: Ability,
    val savingThrows: Set<Ability>,
    val skillProficiencyCount: Int,
    val skillProficiencyChoices: Set<Skills>,
    val weaponProficiency: Set<WeaponType>,
    val toolProficiency: Set<ToolType>
)
