package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)

        // Menangkap data dari Intent
        val foodName = intent.getStringExtra("food_name") ?: "No Food Name"
        val foodDescription = intent.getStringExtra("food_description") ?: "No Description Available"

        // Menemukan TextView di layout untuk menampilkan nama makanan
        val foodNameTextView: TextView = findViewById(R.id.tvFoodName)
        foodNameTextView.text = foodName

        // Mengambil input dari EditText lainnya
        val etServings: EditText = findViewById(R.id.etServings)
        val etName: EditText = findViewById(R.id.etName)
        val etNotes: EditText = findViewById(R.id.etNotes)
        val btnOrder: Button = findViewById(R.id.btnOrder)

        // Set OnClickListener untuk tombol "Place Order"
        btnOrder.setOnClickListener {
            val servings = etServings.text.toString()
            val orderingName = etName.text.toString()
            val notes = etNotes.text.toString()

            if (servings.isEmpty() || orderingName.isEmpty()) {
                // Menampilkan pesan error jika input tidak lengkap
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
            } else {
                // Menampilkan pesan sukses
                Toast.makeText(this, "Order placed for $servings servings of $foodName by $orderingName", Toast.LENGTH_LONG).show()

                // Membuat Intent untuk mengarahkan ke ConfirmationActivity
                val intent = Intent(this, ConfirmationActivity::class.java)
                intent.putExtra("food_name", foodName)
                intent.putExtra("servings", servings)
                intent.putExtra("ordering_name", orderingName)
                intent.putExtra("notes", notes)
                startActivity(intent)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
