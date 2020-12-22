package android.exempel.todolist


import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.media.tv.TvTrackInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_tod.view.*

class TodoAdapter( private val todos: MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>()  {


    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_tod,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemChanged(todos.size -1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->

            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG

        } else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            tvTodoTitle.text = curTodo.title
            cdDone.isChecked = curTodo.isChecked

            toggleStrikeThrough(tvTodoTitle, curTodo.isChecked)

            cdDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }
}