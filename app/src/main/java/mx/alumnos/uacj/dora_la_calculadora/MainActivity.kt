package mx.alumnos.uacj.dora_la_calculadora

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.alumnos.uacj.dora_la_calculadora.ui.theme.Dora_la_calculadoraTheme

data class BotonModelo(val id: String, var boton: String) {}

class arrayOf {

}

var hileras_de_botones_a_dibujar = arrayOf(
    arrayOf(
        BotonModelo("9","9"),
        BotonModelo("8","8"),
        BotonModelo("7","7"),
    ),
    arrayOf(
        BotonModelo("6","6"),
        BotonModelo("5","5"),
        BotonModelo("4","4"),
    ),
    arrayOf(
        BotonModelo("3","3"),
        BotonModelo("2","2"),
        BotonModelo("1","1"),
    ),
    arrayOf(
        BotonModelo("btn_punto","btn_punto"),
        BotonModelo("0","0"),
        BotonModelo("btn_operacion","btn_operacion"),
    )

)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Dora_la_calculadoraTheme {
                    Calculadora()
                }
            }
        }
    }

@Composable
fun Calculadora() {
    var pantalla_calculadora = remember { mutableStateOf("0") }

    fun pulsar_boton(id: String){
        Log.v("BOTONES-INTERFAZ", "se ha pulsado el boton ${id} de la interfaz")
        when(id){
            "9" -> {
                pantalla_calculadora.value = pantalla_calculadora.value + "9"
            }
            " " -> ""
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center) {
        Text(text = "Aqui van numeritos", modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .background(Color.Blue)
            .height(50.dp),
            textAlign = TextAlign.Right,
        )

        Column(modifier = Modifier.fillMaxSize().background(Color.DarkGray)) {
            for(fila_de_botones in hileras_de_botones_a_dibujar){
                Row(horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()){

                        for(boton_a_dibujar in fila_de_botones){
                            Boton(boton_a_dibujar.boton, alPulsar = {
                                pulsar_boton(boton_a_dibujar)
                            })}
                }
            }
        }
    }
}

@Composable
fun Boton(etiqueta: String, alPulsar: () -> Unit = {}) {
    Button(onClick = alPulsar) {
        Image(
            painter = painterResource(R.drawable.images),
            contentDescription = "Una foto de perfil del conde contar",
            modifier = Modifier.size(25.dp)
        )

        Text(
            etiqueta, modifier = Modifier
                .background(Color.Green),
            textAlign = TextAlign.Center,
            color = Color.Red
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Dora_la_calculadoraTheme {
        Calculadora()
    }
}


@Preview(showBackground = true)
@Composable
fun mostrar_boton() {
    Dora_la_calculadoraTheme {
        Boton("4")
    }
}
