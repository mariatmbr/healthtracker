import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.healthtracker.databinding.RecipeItemViewBinding
import com.example.healthtracker.model.Recipe


class RecipesListAdapter(private var recipes: List<Recipe>) :
    RecyclerView.Adapter<RecipesListAdapter.ViewHolder>() {

    class ViewHolder(val binding: RecipeItemViewBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            RecipeItemViewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val recipe = recipes[position];
        viewHolder.binding.recipeNameView.text = recipe.title;
        viewHolder.binding.readyTextView.text = "Ready in ${recipe.readyInMinutes} min";
        Glide.with(viewHolder.itemView.context).load(recipe.image).centerCrop()
            .into(viewHolder.binding.recipeImageView)
        viewHolder.itemView.setOnClickListener {
            it.context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(recipe.sourceUrl)
                )
            )
        }
    }

    override fun getItemCount() = recipes.size

    fun setRecipes(recipes: List<Recipe>) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }
}