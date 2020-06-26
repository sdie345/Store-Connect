package dev.swote.storeconnect.dialogs

import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

fun String.openMessage(title: String, activity: AppCompatActivity,
                       listener: DialogInterface.OnClickListener = DialogInterface.OnClickListener { dialog, _ ->
                           dialog.dismiss() })
{
    val dialog = AlertDialog.Builder(activity)
    dialog.setTitle(title)
    dialog.setMessage(this)
    dialog.setPositiveButton("확인", listener)
    dialog.setNegativeButton("취소", listener)
    dialog.show()
}