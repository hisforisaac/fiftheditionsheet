package com.crumb.fiftheditionsheet.viewmodel

import com.crumb.fiftheditionsheet.model.rules.CharacterSheet

sealed class CharacterSheetState
data object EmptyCharacterSheet: CharacterSheetState()
