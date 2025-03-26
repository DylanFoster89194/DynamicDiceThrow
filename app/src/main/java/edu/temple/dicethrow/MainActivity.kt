package edu.temple.dicethrow

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.res.Configuration



/*
Our DieThrow application has been refactored to move the dieRoll() logic
into the ViewModel instead of the Fragment.
Study the DieViewModel, ButtonFragment, and DieFragment classes to
see the changes.

Follow the requirements below to have this app function
in both portrait and landscape configurations.
The Activity layout files for both Portrait and Landscape are already provided
*/

class MainActivity : AppCompatActivity(), ButtonFragment.ButtonInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* TODO 1: Load fragment(s)
            - Show _only_ ButtonFragment if portrait
            - Show _both_ fragments if Landscape
        */

        if (savedInstanceState == null) {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                // Portrait mode: Show only ButtonFragment
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, ButtonFragment())
                    .commit()
            } else {
                // Landscape mode: Show both ButtonFragment and DieFragment
                supportFragmentManager.beginTransaction()
                    .replace(R.id.buttonFragmentContainer, ButtonFragment())
                    .replace(R.id.dieFragmentContainer, DieFragment())
                    .commit()
            }
        }
    }

    /* TODO 2: Switch fragments if die rolled and in portrait (no need to switch fragments if Landscape) */
    override fun buttonClicked() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Replace ButtonFragment with DieFragment when button is clicked
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, DieFragment())
                .addToBackStack(null) // Allows user to go back
                .commit()
        }
    }
}