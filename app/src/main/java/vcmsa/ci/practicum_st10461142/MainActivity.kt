package vcmsa.ci.practicum_st10461142

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // Arrays to store data
    private val songs = mutableListOf<String>()
    private val artists = mutableListOf<String>()
    private val ratings = mutableListOf<Int>()
    private val comments = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inputs and Buttons
        val songTitleInput = findViewById<EditText>(R.id.songTitleInput)
        val artistNameInput = findViewById<EditText>(R.id.artistNameInput)
        val ratingInput = findViewById<EditText>(R.id.ratingInput)
        val commentInput = findViewById<EditText>(R.id.commentInput)
        val addToPlaylistButton = findViewById<Button>(R.id.addToPlaylistButton)
        val viewPlaylistButton = findViewById<Button>(R.id.viewPlaylistButton)
        val exitButton = findViewById<Button>(R.id.exitButton)

        // Add item to arrays
        addToPlaylistButton.setOnClickListener {
            try {
                val item = songTitleInput.text.toString()
                val category = artistNameInput.text.toString()
                val quantity = ratingInput.text.toString().toInt()
                val comment = commentInput.text.toString()

                // Input validation
                if (item.isEmpty() || category.isEmpty() || comment.isEmpty()) {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                songs.add(item)
                artists.add(category)
                ratings.add(quantity)
                comments.add(comment)

                // Clear input fields
                songTitleInput.text.clear()
                artistNameInput.text.clear()
                ratingInput.text.clear()
                commentInput.text.clear()

                Toast.makeText(this, "Song added to Playlist!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Enter a valid number for Rating", Toast.LENGTH_SHORT).show()
            }
        }

            // Navigate to second screen
            viewPlaylistButton.setOnClickListener {
                val intent = Intent(this, DetailedView::class.java)
                intent.putStringArrayListExtra("songs", ArrayList(songs))
                intent.putStringArrayListExtra("artists", ArrayList(artists))
                intent.putIntegerArrayListExtra("ratings", ArrayList(ratings))
                intent.putStringArrayListExtra("comments", ArrayList(comments))
                startActivity(intent)
            }

            // Exit app
            exitButton.setOnClickListener {
                finish()
            }
        }
    }
