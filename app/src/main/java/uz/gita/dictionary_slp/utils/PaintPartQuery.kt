package uz.gita.dictionary_slp.utils

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import uz.gita.dictionary_slp.R

fun String.paintWord(query: String, context: Context): Spannable {
    var startWord = 0
    var endWord = query.length
    var startWordType = this.indexOf("[")
    var endWordType = this.indexOf("]")

    val spannable = SpannableString(this)
    if (startWord != -1 && endWord != -1) spannable.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.purple_500)), startWord, endWord, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    if (startWordType != -1 && endWordType != -1) spannable.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.myColor)), startWordType + 1, endWordType - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

    return spannable
}