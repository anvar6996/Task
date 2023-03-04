package uz.leerybit.task.ui.screens.products

import android.view.ViewGroup
import com.bumptech.glide.Glide
import uz.leerybit.task.databinding.ItemProductBinding
import uz.leerybit.task.model.responce.OfferX
import uz.leerybit.task.ui.base.BaseRecyclerAdapter
import uz.leerybit.task.ui.base.BaseViewHolder
import uz.leerybit.task.ui.base.ItemClickListener
import uz.leerybit.task.ui.base.ItemPositionClickListener
import uz.leerybit.task.utils.extentions.inflater

class ProductsAdapter : BaseRecyclerAdapter<OfferX, ItemProductBinding, ProductViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
    return ProductViewHolder.create(parent)
  }
}

class ProductViewHolder(binding: ItemProductBinding) :
  BaseViewHolder<OfferX, ItemProductBinding>(binding) {

  companion object {
    fun create(parent: ViewGroup): ProductViewHolder {
      return ProductViewHolder(ItemProductBinding.inflate(parent.inflater))
    }
  }

  override fun binding(
    item: OfferX,
    itemClickListener: ItemClickListener<OfferX>?,
    positionItemClickListener: ItemPositionClickListener<OfferX>?
  ) {
    binding.apply {
      Glide.with(productImage.context).load(item.image.url).into(productImage)
      productName.text = item.name
      description.text = item.merchant
      brendName.text = item.brand
      parent.setOnClickListener {
        itemClickListener?.invoke(item)
      }

    }
  }
}