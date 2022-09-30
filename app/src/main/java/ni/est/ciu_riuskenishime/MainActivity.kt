package ni.est.ciu_riuskenishime


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ni.est.ciu_riuskenishime.dataadapter.ProductoAdapter
import ni.est.ciu_riuskenishime.databinding.ActivityMainBinding
import ni.est.ciu_riuskenishime.dataclass.Producto
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var listaProd = ArrayList<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniciar()
    }

    private fun limpiar(){
        with(binding){
            etID.setText("")
            etNombreProd.setText("")
            etPrecio.setText("")
            etID.requestFocus()
        }
    }

    private fun agregarProd(){
        with(binding){
            try{
                val id: Int = etID.text.toString().toInt()
                val nombre: String = etNombreProd.text.toString()
                val precio: Double = etPrecio.text.toString().toDouble()
                val prod = Producto(id, nombre, precio)
                listaProd.add(prod)
            }catch (ex: Exception){
                Toast.makeText(this@MainActivity, "Error: ${ex.toString()}",
                    Toast.LENGTH_SHORT).show()
            }

            rcvLista.layoutManager = LinearLayoutManager(this@MainActivity)
            rcvLista.adapter = ProductoAdapter(listaProd,
                {producto -> onItemSelected(producto)},
                {position -> borrarProd(position)},
                {position -> editarProd(position)})
            limpiar()
        }
    }


    private fun iniciar(){
        binding.btnAgregar.setOnClickListener {
            agregarProd()
        }
        binding.btnLimpiar.setOnClickListener {
            limpiar()
        }
    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

    fun borrarProd(position: Int) {
            with(binding) {
                listaProd.removeAt(position)
                rcvLista.adapter?.notifyItemRemoved(position)
                limpiar()
            }
    }

    fun editarProd(position: Int){
        try {
            with(binding) {
                val id: Int = etID.text.toString().toInt()
                val nombre: String = etNombreProd.text.toString()
                val precio: Double = etPrecio.text.toString().toDouble()
                val prod = Producto(id, nombre, precio)
                listaProd.set(position, prod)
                rcvLista.adapter?.notifyItemChanged(position)
            }
        }catch (ex: Exception){
            Toast.makeText(this@MainActivity, "Error: ${ex.toString()}", Toast.LENGTH_SHORT).show()
        }
    }

    fun onItemSelected(producto: Producto){
        with(binding){
            etID.text = producto.id.toString().toEditable()
            etNombreProd.text = producto.nombre.toEditable()
            etPrecio.text = producto.precio.toString().toEditable()
        }
    }
}