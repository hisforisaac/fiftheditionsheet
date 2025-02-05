package com.crumb.fiftheditionsheet.model.rules.character

data class Skills(
    val proficiencies: List<SkillProficiency>
)

data class SkillProficiency(val level: Int, val skill: Skill)
