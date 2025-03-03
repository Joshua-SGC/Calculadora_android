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
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.alumnos.uacj.dora_la_calculadora.ui.theme.Dora_la_calculadoraTheme

data class BotonModelo(val id: String, var boton: String, var operacion_Aritmeticas: OperacionesAritmeticas = OperacionesAritmeticas.Ninguna) {
    val numero: Any?
}

enum class EstadosCalculadora{
    CuandoEstaEnCero,
    AgregandoNumeros,
    SeleccionandoOperacion,
    MostrandoResultado
}

enum class OperacionesAritmeticas{
    Ninguna, //Esta es la opcion por default
    Suma,
    Resta,
    Multiplicacion,
    Division,
    Resultado
}

var hileras_de_botones_a_dibujar = arrayOf(
    arrayOf(
        BotonModelo("9","9", OperacionesAritmeticas.Multiplicacion),
        BotonModelo("8","8"),
        BotonModelo("7","7", OperacionesAritmeticas.Division),
    ),
    arrayOf(
        BotonModelo("6","6"),
        BotonModelo("5","5", OperacionesAritmeticas.Resultado),
        BotonModelo("4","4"),
    ),
    arrayOf(
        BotonModelo("3","3", OperacionesAritmeticas.Suma),
        BotonModelo("2","2"),
        BotonModelo("1","1", OperacionesAritmeticas.Resta),
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
    var estado_de_la_calculadora = remember { mutableStateOf(EstadosCalculadora.CuandoEstaEnCero) }
    var operacion_seleccionada = remember { mutableStateOf(OperacionesAritmeticas.Ninguna) }

    fun pulsar_boton(boton: BotonModelo){
        Log.v("BOTONES-INTERFAZ", "se ha pulsado el boton ${boton.id} de la interfaz")
        Log.v("OPERACION_SELECCIONADA", "la operacion seleccionada es ${operacion_seleccionada.value}")

        when(estado_de_la_calculadora.value) {
            EstadosCalculadora.CuandoEstaEnCero -> {
                if(boton.id == "boton_0") {
                    return
                }else if(boton.id == "boton_punto"){
                    pantalla_calculadora.value = pantalla_calculadora.value + boton.numero
                    return
                }
                pantalla_calculadora.value = boton.numero
                estado_de_la_calculadora.value = EstadosCalculadora.AgregandoNumeros
            }

            EstadosCalculadora.AgregandoNumeros -> {
                if(boton.id == "boton_operacion"){
                    estado_de_la_calculadora.value = EstadosCalculadora.SeleccionandoOperacion
                    return
                }

                pantalla_calculadora.value = pantalla_calculadora.value + boton.numero
            }

            EstadosCalculadora.SeleccionandoOperacion -> {
                if(boton.operacion_aritmeticas != OperacionesAritmeticas.Ninguna){
                    operacion_seleccionada.value = boton.operacion_Aritmeticas
                    estado_de_la_calculadora.value = EstadosCalculadora.CuandoEstaEnCero

                    pantalla_calculadora.value = "0"
                    return
                }
                estado_de_la_calculadora.value = EstadosCalculadora.AgregandoNumeros
            }

            EstadosCalculadora.MostrandoResultado -> TODO()
        }

        if(boton.id == "boton_operacion"){
            estado_de_la_calculadora.value =
        }
        else{
            pantalla_calculadora.value = boton.id
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Aqui van numeritos", modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.DarkGray)
            .height(50.dp),
            textAlign = TextAlign.Right,
        )

        /*Column(modifier = Modifier.fillMaxSize().background(Color.DarkGray)) {
            for(fila_de_botones in hileras_de_botones_a_dibujar){
                Row(horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()){

                        for(boton_a_dibujar in fila_de_botones){
                            Boton(boton_a_dibujar.boton, alPulsar = {
                                pulsar_boton(boton_a_dibujar)
                            })}
                }
            }
        }*/
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)) {
            for(boton_a_dibujar in fila_de_botones){
                when(estado_de_la_calculadora.value){
                    EstadosCalculadora.SeleccionandoOperacion
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
