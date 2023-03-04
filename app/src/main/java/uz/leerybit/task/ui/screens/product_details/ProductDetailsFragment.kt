package uz.leerybit.task.ui.screens.product_details

import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import uz.leerybit.task.R
import uz.leerybit.task.databinding.FragmentProductDetailBinding
import uz.leerybit.task.ui.base.BaseFragment

@AndroidEntryPoint
class ProductDetailsFragment : BaseFragment(R.layout.fragment_product_detail) {
  private val attributeAdapter = ProductAttibuteAdapter()
  private val bind by viewBinding(FragmentProductDetailBinding::bind)
  private val args: ProductDetailsFragmentArgs by navArgs()


  override fun initialize() {
    super.initialize()
    bind.apply {
      recyklerviewAttribute.adapter = attributeAdapter
      recyklerviewAttribute.layoutManager = LinearLayoutManager(requireContext())
      attributeAdapter.submitList(args.data.attributes)
      Glide.with(productImage.context).load(args.data.image.url).into(productImage)
      productName.text = args.data.name
      description.text = args.data.merchant
      brendName.text = args.data.brand
    }
  }

}