package vcmsa.ci.practicum_st10461142

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedView : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

            val songs = intent.getStringArrayListExtra("songs") ?: arrayListOf()
            val artists = intent.getStringArrayListExtra("artists") ?: arrayListOf()
            val ratings = intent.getIntegerArrayListExtra("ratings") ?: arrayListOf()
            val comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()

            val fullList = findViewById<TextView>(R.id.displayTextView)
            val backButton = findViewById<Button>(R.id.backButton)
            val displayBtn = findViewById<Button>(R.id.displayBtn)

            // Show complete list
            displayBtn.setOnClickListener {
                val allSongs = StringBuilder()
            for (i in songs.indices) {
                allSongs.append("• ${songs[i]} (${artists[i]}) - Rat: ${ratings[i]} - ${comments[i]}\n")
            }
                fullList.text = "Playlist:\n$allSongs"


            //Calculate and Display average Rating



            backButton.setOnClickListener {
                finish()
                }
            }
        }
    }
