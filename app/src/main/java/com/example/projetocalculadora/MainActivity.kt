package com.example.projetocalculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultado = findViewById<TextView>(R.id.resultado)
        val expressao = findViewById<TextView>(R.id.expressao)

        //numeros

        val numero_zero = findViewById<TextView>(R.id.numero_zero)
        val numero_um = findViewById<TextView>(R.id.numero_um)
        val numero_dois = findViewById<TextView>(R.id.numero_dois)
        val numero_tres = findViewById<TextView>(R.id.numero_tres)
        val numero_quatro = findViewById<TextView>(R.id.numero_quatro)
        val numero_cinco = findViewById<TextView>(R.id.numero_cinco)
        val numero_seis = findViewById<TextView>(R.id.numero_seis)
        val numero_sete = findViewById<TextView>(R.id.numero_sete)
        val numero_oito = findViewById<TextView>(R.id.numero_oito)
        val numero_nove = findViewById<TextView>(R.id.numero_nove)

        val numeros = arrayOf(numero_zero, numero_um, numero_dois, numero_tres, numero_quatro, numero_cinco, numero_seis, numero_sete, numero_oito, numero_nove)

        numeros.forEachIndexed { index, textView ->
            textView.setOnClickListener {
                acrescentarExpressao(index.toString(), true, expressao, resultado)
            }
        }

        //Operadores

        val adicao = findViewById<TextView>(R.id.adicao)
        val multiplicacao = findViewById<TextView>(R.id.multiplicacao)
        val subtracao = findViewById<TextView>(R.id.subtracao)
        val divisao =     findViewById<TextView>(R.id.divisao)
        val porcentagem = findViewById<TextView>(R.id.porcentagem)

        val operacoes = arrayOf(adicao, subtracao, multiplicacao, divisao, porcentagem)

        operacoes.forEach { botao ->
            botao.setOnClickListener {
                val operador = when (botao.id) {
                    R.id.adicao -> "+"
                    R.id.subtracao -> "-"
                    R.id.multiplicacao -> "*"
                    R.id.divisao -> "/"
                    R.id.porcentagem -> "%"
                    else -> ""
                }
                acrescentarExpressao(operador, false, expressao, resultado)
            }
        }


        val limpar = findViewById<TextView>(R.id.limpar)
        val backspace = findViewById<TextView>(R.id.backspace)
        val ponto = findViewById<TextView>(R.id.ponto)
        val igual = findViewById<ImageView>(R.id.igual)

        ponto.setOnClickListener{acrescentarExpressao(".",true,expressao,resultado)}


        limpar.setOnClickListener{
            expressao.text = ""
            resultado.text = ""
        }

        backspace.setOnClickListener{
            try {
                val string = expressao.text.toString()
                if (string.isBlank()){
                    expressao.text = string.substring(0,string.length-1)
                }
                resultado.text = ""
            }catch (e: Exception){

            }
        }
        
        igual.setOnClickListener{
            try {
                val expressao = ExpressionBuilder(expressao.text.toString()).build()
                val resultadoFinal = expressao.evaluate( )
                val longResult = resultadoFinal.toLong()

                if (resultadoFinal==longResult.toDouble()){
                    resultado.text = longResult.toString()
                }else{
                    resultado.text = resultadoFinal.toString()
                }
            }catch (e: Exception){

            }
        }
    }

    fun acrescentarExpressao(string : String, limpar_dados: Boolean, expressao: TextView, resultado: TextView){

        if(resultado.text.isNotEmpty()){
            expressao.text = ""
        }

        if(limpar_dados){
            resultado.text = ""
            expressao.append(string)
        }else{
            expressao.append(resultado.text)
            expressao.append(string)
            resultado.text = ""
        }


    }
}