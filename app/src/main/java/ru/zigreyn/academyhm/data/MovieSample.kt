package ru.zigreyn.academyhm.data

import ru.zigreyn.academyhm.R
import ru.zigreyn.academyhm.model.Actor
import ru.zigreyn.academyhm.model.Movie

object MovieSample {
    val movies =
        listOf(
            Movie(
                "Avengers: End Game",
                "Action, Adventure, Fantasy",
                4,
                125,
                "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos\\' actions and restore balance to the universe.",
                137,
                false,
                "13+",
                R.drawable.poster_end_game,
                listOf(
                    Actor("Robert Downey Jr.", R.drawable.downey),
                    Actor("Chris Evans", R.drawable.evans),
                    Actor("Mark Ruffalo", R.drawable.ruffalo),
                    Actor("Chris Hemsworth", R.drawable.hemsworth)
                )
            ),

            Movie(
                "Star Wars: The Force Awakens",
                "Action, Adventure, Science Fiction, Fantasy",
                4,
                15164,
                "Thirty years after defeating the Galactic Empire, Han Solo and his allies face a new threat from the evil Kylo Ren and his army of Stormtroopers.",
                136,
                false,
                "13+",
                R.drawable.poster_star_wars,
                listOf(
                    Actor("Harrison Ford", R.drawable.harrison_ford),
                    Actor("Mark Hamill", R.drawable.mark_hamill),
                    Actor("Carrie Fisher", R.drawable.carrie_fisher),
                    Actor("Daisy Ridley", R.drawable.daisy_ridley),
                    Actor("John Boyega", R.drawable.john_boyega)
                )
            ),

            Movie(
                "Ford vs Ferrari",
                "Drama, History",
                4,
                3876,
                "American car designer Carroll Shelby and the British-born driver Ken Miles work together to battle corporate interference, the laws of physics, and their own personal demons to build a revolutionary race car for Ford Motor Company and take on the dominating race cars of Enzo Ferrari at the 24 Hours of Le Mans in France in 1966.",
                132,
                true,
                "13+",
                R.drawable.poster_ford_vs_ferrari,
                listOf(
                    Actor("Christian Bale", R.drawable.christian_bale),
                    Actor("Matt Damon", R.drawable.matt_damon),
                    Actor("Caitriona Balfe", R.drawable.caitriona_balfe),
                    Actor("Josh Lucas", R.drawable.josh_lucas),
                    Actor("Noah Jupe", R.drawable.noah_jupe),
                    Actor("Jon Bernthal", R.drawable.jon_bernthal)
                )
            ),

            Movie(
                "Tenet",
                "Action, Thriller, Science Fiction",
                4,
                2627,
                "Armed with only one word - Tenet - and fighting for the survival of the entire world, the Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                150,
                false,
                "13+",
                R.drawable.poster_tenet,
                listOf(
                    Actor("John David Washington", R.drawable.john_david_washington),
                    Actor("Robert Pattinson", R.drawable.robert_pattinson),
                    Actor("Elizabeth Debicki", R.drawable.elizabeth_debicki),
                    Actor("Kenneth Branagh", R.drawable.kenneth_branagh),
                    Actor("Dimple Kapadia", R.drawable.dimple_kapadia)
                )
            )
        )
}