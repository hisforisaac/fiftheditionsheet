package com.crumb.fiftheditionsheet.model.rules.general

sealed class Die
data object D2: Die()
data object D4: Die()
data object D6: Die()
data object D8: Die()
data object D10: Die()
data object D12: Die()
data object D20: Die()
data object D100: Die()
