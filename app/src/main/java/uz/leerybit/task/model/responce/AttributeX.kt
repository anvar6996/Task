package uz.leerybit.task.model.responce

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AttributeX(
  val name: String, val value: String
) : Parcelable