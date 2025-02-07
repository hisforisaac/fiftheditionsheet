package com.crumb.fiftheditionsheet.viewmodel

import com.crumb.fiftheditionsheet.model.rules.character.Character
import com.crumb.fiftheditionsheet.model.rules.character.CharacterState
import com.crumb.fiftheditionsheet.model.rules.character.IncompleteCharacter
import com.crumb.fiftheditionsheet.model.rules.character.ValidCharacterState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

class DefaultSheetViewModel(
    private val characterStateRepository: Flow<CharacterState>,
    host: ContainerHost<CharacterSheetViewState, CharacterSheetSideEffects>
): ContainerHost<CharacterSheetViewState, CharacterSheetSideEffects> by host {
    fun characterSheetViewState() = intent {
        characterStateRepository.collect { characterState ->
            reduce {
                when(characterState){
                    is IncompleteCharacter -> EmptyCharacterSheetViewState
                    is ValidCharacterState -> MainCharacterSheetViewState(characterState.character.levelDescription(), characterState)
                }
            }
        }
    }
}

private fun Character.levelDescription(): String =
    classLevels
        .groupBy { it.className }
        .entries
        .joinToString { "${it.key} ${it.value.size}" }
