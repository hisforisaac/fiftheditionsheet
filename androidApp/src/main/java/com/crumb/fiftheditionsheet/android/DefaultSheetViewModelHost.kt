package com.crumb.fiftheditionsheet.android

import androidx.lifecycle.ViewModel
import com.crumb.fiftheditionsheet.model.rules.repository.CharacterSheetStateRepository
import com.crumb.fiftheditionsheet.viewmodel.CharacterSheetSideEffects
import com.crumb.fiftheditionsheet.viewmodel.CharacterSheetViewState
import com.crumb.fiftheditionsheet.viewmodel.CharacterSheetViewModel
import com.crumb.fiftheditionsheet.viewmodel.EmptyCharacterSheetViewState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class DefaultSheetViewModelHost(): ViewModel(),
    ContainerHost<CharacterSheetViewState, CharacterSheetSideEffects> {
    override val container = container<CharacterSheetViewState, CharacterSheetSideEffects>(EmptyCharacterSheetViewState)
    val characterSheetViewModel by lazy { CharacterSheetViewModel(CharacterSheetStateRepository(), this) }
}