package com.crumb.fiftheditionsheet.viewmodel

import com.crumb.fiftheditionsheet.model.rules.character.ValidCharacterState

sealed class CharacterSheetViewState
data object EmptyCharacterSheetViewState: CharacterSheetViewState()
data class MainCharacterSheetViewState(
    val levelDescription: String,
    val characterState: ValidCharacterState,
): CharacterSheetViewState()