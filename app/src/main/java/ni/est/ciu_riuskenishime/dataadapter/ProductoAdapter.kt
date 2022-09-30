package ni.est.ciu_riuskenishime.dataadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ni.est.ciu_riuskenishime.databinding.ItemlistaBinding
import ni.est.ciu_riuskenishime.dataclass.Producto

class ProductoAdapter(val listProd: List<Producto>,
                      private  val onclickListener: (Producto) -> Unit,
                      private val onClickDelete: (Int) -> Unit,
                      private val onClickUpdate: (Int) -> Unit
):
    RecyclerView.Adapter<ProductoAdapter.ProductoHolder>(){
        inner class ProductoHolder(val binding: ItemlistaBinding) :
                RecyclerView.ViewHolder(binding.root) {
            fun cargar(
                producto: Producto,
                onClickListener: (Producto) -> Unit,
                onClickDelete: (Int) -> Unit,
                onClickUpdate: (Int) -> Unit)
            {
                with(binding) {
                    tvCodProd.text = producto.id.toString()
                    tvNombreProd.text = producto.nombre
                    tvPrecioProd.text = producto.precio.toString()
                    itemView.setOnClickListener { onClickListener(producto) }
                    binding.btnBorrar.setOnClickListener { onClickDelete(adapterPosition)}
                    binding.btnEditar.setOnClickListener { onClickUpdate(adapterPosition)}

                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoHolder {
        val binding = ItemlistaBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return ProductoHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductoHolder, position: Int) {
        holder.cargar(listProd[position], onclickListener, onClickDelete, onClickUpdate)

    }

    override fun getItemCount(): Int = listProd.size


}
