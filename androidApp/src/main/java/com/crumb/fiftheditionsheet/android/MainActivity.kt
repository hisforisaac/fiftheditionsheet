package com.crumb.fiftheditionsheet.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.viewmodel.compose.viewModel
import com.crumb.fiftheditionsheet.model.rules.repository.CharacterSheetStateRepository
import com.crumb.fiftheditionsheet.viewmodel.CharacterSheetViewState
import com.crumb.fiftheditionsheet.viewmodel.DefaultSheetViewModel
import com.crumb.fiftheditionsheet.viewmodel.EmptyCharacterSheetViewState
import com.crumb.fiftheditionsheet.viewmodel.MainCharacterSheetViewState
import org.orbitmvi.orbit.compose.collectAsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val defaultSheetViewModel = viewModel<DefaultSheetViewModelHost>()
                    defaultSheetViewModel.defaultSheetViewModel.characterSheetViewState()
                    val state by defaultSheetViewModel.collectAsState()
                    CharacterView(state)
                }
            }
        }
    }
}

@Composable
fun CharacterView(state: CharacterSheetViewState) {
    when(state) {
        EmptyCharacterSheetViewState -> EmptyCharacterView()
        is MainCharacterSheetViewState -> DefaultCharacterView(state)
    }
}

@Composable
fun DefaultCharacterView(state: MainCharacterSheetViewState) {
    val characterState = state.characterState
    Column {
        Row(modifier = Modifier.fillMaxSize()) {
            Text(
                text = characterState.character.name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.weight(1f)
            )
            val levels = characterState.character.classLevels
                .groupBy { it.className }
                .entries
                .joinToString { "${it.key} ${it.value.size}" }

            BasicText(
                text = levels,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.weight(1f),
            )
        }
        Row(modifier = Modifier.fillMaxSize()) {
            Text(
                text = characterState.character.name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.weight(1f)
            )
            val levels = characterState.character.classLevels
                .groupBy { it.className }
                .entries
                .joinToString { "${it.key} ${it.value.size}" }

            BasicText(
                text = levels,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.weight(1f),
            )
        }

    }
}

@Composable
fun EmptyCharacterView() {
}
