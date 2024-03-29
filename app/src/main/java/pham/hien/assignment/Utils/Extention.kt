package ppham.hien.assignment.Utils


import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

fun <R> CoroutineScope.executeAsyncTask(
    onPreExecute: () -> Unit,
    doInBackground: () -> R,
    onPostExecute: (R) -> Unit,
) = launch {
    onPreExecute()
    val result =
        withContext(Dispatchers.IO) { // runs in background thread without blocking the Main
            doInBackground()
        }
    onPostExecute(result)
}

fun <R> CoroutineScope.launch(
    onPreExecute: () -> Unit,
    doInBackground: () -> R,
    onPostExecute: (R) -> Unit,
) = launch {
    onPreExecute()
    val result =
        withContext(Dispatchers.IO) { // runs in background thread without blocking the Main
            doInBackground()
        }
    onPostExecute(result)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}
