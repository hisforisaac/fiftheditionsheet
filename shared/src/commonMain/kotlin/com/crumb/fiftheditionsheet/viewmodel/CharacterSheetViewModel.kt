package com.crumb.fiftheditionsheet.viewmodel

import com.crumb.fiftheditionsheet.model.rules.character.Character
import com.crumb.fiftheditionsheet.model.rules.character.CharacterState
import com.crumb.fiftheditionsheet.model.rules.character.IncompleteCharacter
import com.crumb.fiftheditionsheet.model.rules.character.ValidCharacterState
import kotlinx.coroutines.flow.Flow
import org.orbitmvi.orbit.ContainerHost

class CharacterSheetViewModel(
    private val characterStateRepository: Flow<CharacterState>,
    host: ContainerHost<CharacterSheetViewState, CharacterSheetSideEffects>
): ContainerHost<CharacterSheetViewState, CharacterSheetSideEffects> by host {
    fun openCharacterSheet() = intent {
        characterStateRepository.collect { characterState ->
            reduce {
                when(characterState){
                    is IncompleteCharacter -> EmptyCharacterSheetViewState
                    is ValidCharacterState -> reduceValidCharacterState(characterState)
                }
            }
        }
    }
}

fun reduceValidCharacterState(
    state: ValidCharacterState
): MainCharacterSheetViewState = MainCharacterSheetViewState(state.character.levelDescription(), state)

private fun Character.levelDescription(): String =
    classLevels
        .groupBy { it.className }
        .entries
        .joinToString { "${it.key} ${it.value.size}" }
