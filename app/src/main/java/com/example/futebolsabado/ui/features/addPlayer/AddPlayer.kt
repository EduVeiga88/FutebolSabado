package com.example.futebolsabado.ui.features.addPlayer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.futebolsabado.ui.theme.FutebolSabadoTheme

@Composable
fun AddPlayerScreen(
    onBack:() -> Unit,
    onSaved:() -> Unit,
    viewModel: AddPlayerViewModel = hiltViewModel()
) {
    val state = viewModel.uiState

    AddPlayerContent(
        name = state.name,
        jogosText = state.jogosText,
        vitoriasText = state.vitoriasText,
        golosText = state.golosText,
        onNameChange = viewModel::onNameChange,
        onJogosChange = viewModel::onJogosChange,
        onVitoriasChange = viewModel::onVitoriasChange,
        onGolosChange = viewModel::onGolosChange,
        canSave = state.canSave,
        onSaved = {
            viewModel.savePlayer {

                onSaved()
            }
        }
    )
}



@Composable
fun AddPlayerContent(
    name: String,
    jogosText: String,
    vitoriasText: String,
    golosText: String,
    onSaved: () -> Unit,
    onNameChange: (String) -> Unit,
    onJogosChange: (String) -> Unit,
    onVitoriasChange: (String) -> Unit,
    onGolosChange: (String) -> Unit,
    canSave: Boolean
) {
    Scaffold {padding->
        Column(
            modifier = Modifier
                .padding(20.dp)
                .padding(padding)
                .imePadding()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = name,
                onValueChange = onNameChange,
                placeholder = {
                    Text(text = "Nome")
                }

            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = jogosText,
                onValueChange = onJogosChange,
                enabled = true,
                placeholder = {
                    Text(text = "Jogos")
                }

            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = vitoriasText,
                onValueChange = onVitoriasChange,
                placeholder = {
                    Text(text = "Vitórias")
                }

            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = golosText,
                onValueChange = onGolosChange,
                placeholder = {
                    Text(text = "Golos")
                }

            )
            Button(
                onClick = onSaved,
                enabled = name.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Guardar",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
private fun AddPlayerPreview() {
    FutebolSabadoTheme {
        AddPlayerContent(
            name = "",
            jogosText = "",
            vitoriasText = "",
            golosText = "",
            onNameChange = {},
            onJogosChange = {},
            onVitoriasChange = {},
            onGolosChange = {},
            canSave = false,
            onSaved = {}
        )
    }
}