package com.crumb.fiftheditionsheet.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.crumb.fiftheditionsheet.model.rules.character.SkillModifiers
import com.crumb.fiftheditionsheet.model.rules.repository.characterState
import com.crumb.fiftheditionsheet.viewmodel.CharacterSheetViewState
import com.crumb.fiftheditionsheet.viewmodel.EmptyCharacterSheetViewState
import com.crumb.fiftheditionsheet.viewmodel.MainCharacterSheetViewState
import com.crumb.fiftheditionsheet.viewmodel.reduceValidCharacterState
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
                    defaultSheetViewModel.characterSheetViewModel.openCharacterSheet()
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

@Preview
@Composable
fun DefaultViewPreview() {
    val state = characterState()
    val viewState = reduceValidCharacterState(state)
    DefaultCharacterView(viewState)
}

@Composable
fun DefaultCharacterView(state: MainCharacterSheetViewState) {
    val characterState = state.characterState
    Column(Modifier.fillMaxSize()) {
        Row() {
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
        SkillsView(characterState.skillModifiers)
    }
}

@Composable
fun SkillsView(skills: SkillModifiers) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        skills.entries
            .sortedBy { it.value }
            .forEach {
                item {
                    Box(
                        modifier = Modifier.height(32.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        val modifierText = if(it.value > 0) "+${it.value}" else it.value.toString()
                        Text(
                            "${it.key}: $modifierText",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
            }
        }
    }

}
@Composable
fun EmptyCharacterView() {
}
