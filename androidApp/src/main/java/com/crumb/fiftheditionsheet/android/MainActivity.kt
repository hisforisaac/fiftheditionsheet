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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.crumb.fiftheditionsheet.Greeting
import com.crumb.fiftheditionsheet.model.rules.character.AbilityScores
import com.crumb.fiftheditionsheet.model.rules.character.Character
import com.crumb.fiftheditionsheet.model.rules.character.CharacterClass
import com.crumb.fiftheditionsheet.model.rules.character.CharacterState
import com.crumb.fiftheditionsheet.model.rules.character.CreatureSize
import com.crumb.fiftheditionsheet.model.rules.character.Inventory
import com.crumb.fiftheditionsheet.model.rules.character.Languages
import com.crumb.fiftheditionsheet.model.rules.character.Race
import com.crumb.fiftheditionsheet.model.rules.character.Skills
import com.crumb.fiftheditionsheet.model.rules.character.Spells
import com.crumb.fiftheditionsheet.model.rules.general.D8

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DefaultCharacterView()
                }
            }
        }
    }
}

fun testCharacter() = Character(
    name = "Crumb",
    height = 65,
    weight = 115,
    speed = 60,
    race = Race("Elf"),
    age = 113,
    languages = Languages(),
    hitPointMax = 63,
    hitDiceQuantity = 1,
    hitDie = D8,
    abilityScores = AbilityScores(
        dexterity = 14,
        strength = 9,
        charisma = 18,
        constitution = 10,
        wisdom = 14,
        intelligence = 12
    ),
    spells = Spells(),
    size = CreatureSize(),
    classLevels = (1..7).map { CharacterClass("Bard", ) }.plus(CharacterClass("Cleric")),
    skills = Skills(emptyList())
)

@Composable
@Preview
fun DefaultCharacterView() {
    val character = testCharacter()
    val characterState = CharacterState(
        character = character,
        inventory = Inventory(),
        currentHitPoints = 63,
        armorClass = 14
    )
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
                autoSize
            )
        }

    }
}