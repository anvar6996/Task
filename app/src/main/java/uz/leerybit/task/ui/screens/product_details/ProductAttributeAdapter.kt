package uz.leerybit.task.ui.screens.product_details

import android.view.ViewGroup
import uz.leerybit.task.databinding.ItemAttributeBinding
import uz.leerybit.task.model.responce.AttributeX
import uz.leerybit.task.ui.base.BaseRecyclerAdapter
import uz.leerybit.task.ui.base.BaseViewHolder
import uz.leerybit.task.ui.base.ItemClickListener
import uz.leerybit.task.ui.base.ItemPositionClickListener
import uz.leerybit.task.utils.extentions.inflater

class ProductAttibuteAdapter :
  BaseRecyclerAdapter<AttributeX, ItemAttributeBinding, ProductViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
    return ProductViewHolder.create(parent)
  }
}

class ProductViewHolder(binding: ItemAttributeBinding) :
  BaseViewHolder<AttributeX, ItemAttributeBinding>(binding) {

  companion object {
    fun create(parent: ViewGroup): ProductViewHolder {
      return ProductViewHolder(ItemAttributeBinding.inflate(parent.inflater))
    }
  }

  override fun binding(
    item: AttributeX,
    itemClickListener: ItemClickListener<AttributeX>?,
    positionItemClickListener: ItemPositionClickListener<AttributeX>?
  ) {
    binding.apply {
      value.text = "${item.name} :${item.value}"
    }
  }
}