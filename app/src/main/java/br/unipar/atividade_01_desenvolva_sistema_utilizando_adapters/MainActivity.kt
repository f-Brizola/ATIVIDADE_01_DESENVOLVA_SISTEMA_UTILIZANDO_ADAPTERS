package br.unipar.atividade_01_desenvolva_sistema_utilizando_adapters

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {


    private val listaDeTarefas = mutableListOf<Tarefa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edNome = findViewById<EditText>(R.id.edNome)
        val edEscolha = findViewById<EditText>(R.id.edEscolha)
        val btnInserir = findViewById<Button>(R.id.btnInserir)
        val listaTarefas = findViewById<ListView>(R.id.listaTarefas)
        val total = findViewById<TextView>(R.id.txtTotal)
        val btnZerar = findViewById<Button>(R.id.btnZerar)

        val adapter = TarefaAdapter(this, listaDeTarefas)
        var cont = 0

        listaTarefas.adapter = adapter

        btnInserir.setOnClickListener {

            val nomeAluno = edNome.text.toString()
            val escolhaAluno = edEscolha.text.toString()
            val dataAtual = SimpleDateFormat("dd/mm", Locale.getDefault()).format(Date())

            if (nomeAluno.isNotEmpty() && escolhaAluno.isNotEmpty()){

                val novaTarefa = Tarefa(nomeAluno, escolhaAluno , dataAtual)

                // atualiza a tela
                listaDeTarefas.add(novaTarefa)
                adapter.notifyDataSetChanged()

                cont ++


                total.setText("Alunos $cont")

            }
        }

        btnZerar.setOnClickListener{

            val edNome = findViewById<EditText>(R.id.edNome)
            val edEscolha = findViewById<EditText>(R.id.edEscolha)
            val total = findViewById<TextView>(R.id.txtTotal)


            edNome.setText("")
            edEscolha.setText("")
            total.setText("")
            listaDeTarefas.clear();
            adapter.notifyDataSetChanged()
            cont = 0

        }

        listaTarefas.setOnItemLongClickListener{ _,_,positon,_ ->
            val removeTarefa = listaDeTarefas.removeAt(positon)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Tarefa Removida", Toast.LENGTH_LONG).show()
            true
        }


    }

    // Outra forma de criar função para o botão
    fun limparValores(view: View){
        val edNome = findViewById<EditText>(R.id.edNome)
        val edEscolha = findViewById<EditText>(R.id.edEscolha)
        val total = findViewById<TextView>(R.id.txtTotal)

        edNome.setText("")
        edEscolha.setText("")
        total.setText("")

    }


}