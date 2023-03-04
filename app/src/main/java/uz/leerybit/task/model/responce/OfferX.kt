package uz.leerybit.task.model.responce

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OfferX(
    val attributes: List<AttributeX>,
    val brand: String,
    val category: String,
    val id: Int,
    val image: Image,
    val merchant: String,
    val name: String
): Parcelable