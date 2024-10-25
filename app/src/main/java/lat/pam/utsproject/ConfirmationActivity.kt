package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmation)

        // Menangkap data dari Intent yang dikirim dari OrderActivity
        val foodName = intent.getStringExtra("food_name") ?: "Unknown Food"
        val servings = intent.getStringExtra("servings") ?: "Unknown Servings"
        val orderingName = intent.getStringExtra("ordering_name") ?: "Unknown Name"
        val notes = intent.getStringExtra("notes") ?: "No additional notes"

        // Menampilkan data di TextView yang sesuai
        val tvFoodName: TextView = findViewById(R.id.tvFoodName)
        val tvServings: TextView = findViewById(R.id.tvServings)
        val tvOrderingName: TextView = findViewById(R.id.tvOrderingName)
        val tvNotes: TextView = findViewById(R.id.tvNotes)

        tvFoodName.text = "Food Name: $foodName"
        tvServings.text = "Number of Servings: $servings"
        tvOrderingName.text = "Ordering Name: $orderingName"
        tvNotes.text = "Additional Notes: $notes"

        // Tombol kembali ke menu
        val backToMenuButton: Button = findViewById(R.id.backtoMenu)
        backToMenuButton.setOnClickListener {
            // Kembali ke menu utama (ListFoodActivity)
            val intent = Intent(this, ListFoodActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish() // Menutup activity confirmation setelah kembali ke menu
        }

        // Menangani insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
