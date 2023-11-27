
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(
    title: String,
    onBackClick: () -> Unit,
    showEdit: Boolean = false,
    onEditClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = title,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
        navigationIcon = {
            // Back button
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            // Edit button which can be optionally displayed
            if (showEdit) {
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit"
                    )
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}
