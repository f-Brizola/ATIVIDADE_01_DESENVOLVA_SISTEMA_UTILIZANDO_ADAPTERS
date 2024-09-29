package br.unipar.atividade_01_desenvolva_sistema_utilizando_adapters

import android.content.Context
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView

class TarefaAdapter (

    private val context : Context,
    private val listaTarefa : MutableList <Tarefa>) : ArrayAdapter<Tarefa>(context,0,listaTarefa){

        override fun getView (position: Int, convertView: View?, parent: ViewGroup): View {

            val tarefa = listaTarefa.get(position)

            val view = LayoutInflater.from(context).inflate(R.layout.item_tarefa, parent, false)

            val aluno = view.findViewById<TextView>(R.id.txtAluno)
            val escolha = view.findViewById<TextView>(R.id.txtEscolha)
            val data = view.findViewById<TextView>(R.id.txtData)

            aluno.text = tarefa.nome
            escolha.text = tarefa.escolha
            data.text = tarefa.data

            return view

        }
    }