package uz.gita.dictionary_slp.presenter.ui.adapter

import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.dictionary_slp.R
import uz.gita.dictionary_slp.databinding.ItemDictionaryBinding
import uz.gita.dictionary_slp.utils.closeAnimation
import uz.gita.dictionary_slp.utils.hideKeyboard
import uz.gita.dictionary_slp.utils.openAnimation
import uz.gita.dictionary_slp.utils.paintWord

class DictionaryAdapter : RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder>() {
    var active: Int? = null
    var cursor: Cursor? = null
    var query: String? = null
    private var isRememberListener: ((Int, Int) -> Unit)? = null

    inner class DictionaryViewHolder(private val binding: ItemDictionaryBinding) : RecyclerView.ViewHolder(binding.root) {
        private var _height = 0

        init {
            binding.remember.setOnClickListener {
                cursor!!.moveToPosition(absoluteAdapterPosition)
                if (cursor!!.getInt(3) == 0) isRememberListener!!.invoke(1, (cursor!!.getInt(0)))
                else isRememberListener!!.invoke(0, (cursor!!.getInt(0)))
            }
            binding.parent.setOnClickListener {
                itemView.context.hideKeyboard(it)
                val height = it.measuredHeight

                if (_height == 0) {
                    _height = height
                    active = absoluteAdapterPosition
                    cursor!!.moveToPosition(absoluteAdapterPosition)
                    binding.subTitle.openAnimation()
                } else {
                    _height = 0
                    active = absoluteAdapterPosition
                    cursor!!.moveToPosition(absoluteAdapterPosition)
                    binding.subTitle.closeAnimation()
                }

            }
        }

        fun bind() {
            cursor?.let { cur ->
                cur.moveToPosition(absoluteAdapterPosition)
                binding.subTitle.text = cur.getString(2)

                binding.title.text = if (query == null) cur.getString(1) else cur.getString(1).trim().paintWord(query!!, itemView.context)

                if (cur.getInt(3) == 0) binding.remember.setImageResource(R.drawable.ic_bookmark)
                else binding.remember.setImageResource(R.drawable.ic_bookmark_select)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryAdapter.DictionaryViewHolder =
        DictionaryViewHolder(ItemDictionaryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        cursor?.let { return it.count }
        return 0
    }

    override fun getItemViewType(position: Int): Int = position
    override fun getItemId(position: Int): Long = position.toLong()

    fun setIsRememberListener(block: ((Int, Int) -> Unit)) {
        isRememberListener = block
    }
}