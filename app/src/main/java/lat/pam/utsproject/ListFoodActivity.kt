package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class ListFoodActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodList: List<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_food)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Menyiapkan data makanan
        foodList = listOf(
            Food("Batagor", "Batagor asli enak dari Bandung", R.drawable.batagor),
            Food("Black Salad", "Salad segar yang dibuat secara langsung", R.drawable.black_salad),
            Food("Cappucino", "Kopi cappucino asli yang dibuat dari Kopi Arabica", R.drawable.cappuchino)
        )

        recyclerView.adapter = object : RecyclerView.Adapter<FoodViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_layout_food, parent, false)
                return FoodViewHolder(view)
            }

            override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
                val food = foodList[position]
                holder.foodName.text = food.name
                holder.foodDescription.text = food.description
                holder.foodImage.setImageResource(food.imageResource)

                holder.itemView.setOnClickListener {
                    val intent = Intent(this@ListFoodActivity, OrderActivity::class.java)
                    intent.putExtra("food_name", food.name)
                    intent.putExtra("food_description", food.description)
                    startActivity(intent)
                }
            }

            override fun getItemCount(): Int = foodList.size
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // ViewHolder di dalam ListFoodActivity tanpa kelas baru
    inner class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodName: TextView = view.findViewById(R.id.foodName)
        val foodDescription: TextView = view.findViewById(R.id.foodDescription)
        val foodImage: ImageView = view.findViewById(R.id.foodImage)
    }
}
