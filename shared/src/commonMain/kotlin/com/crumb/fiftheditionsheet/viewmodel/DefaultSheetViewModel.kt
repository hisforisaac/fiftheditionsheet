package com.crumb.fiftheditionsheet.viewmodel

import kotlinx.coroutines.CoroutineScope
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

class DefaultSheetViewModel(scope: CoroutineScope): ContainerHost<CharacterSheetState, CharacterSheetSideEffects> {
    override val container = scope.container<CharacterSheetState, CharacterSheetSideEffects>(EmptyCharacterSheet)

}