package com.example.cookbook.ui.theme

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cookbook.R
import com.example.cookbook.data.LocalCookBookData
import com.example.cookbook.model.CookBook
import com.example.cookbook.utils.CookBookContentType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CookBookApp(
    windowSize: WindowWidthSizeClass
) {
    val viewModel: CookBookViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val contentType = when (windowSize){
        WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> CookBookContentType.ListOnly
        WindowWidthSizeClass.Expanded -> CookBookContentType.ListAndDetail
        else -> CookBookContentType.ListOnly
    }

    Scaffold(
        topBar = {
            CookBookAppBar(
                isShowingListPage = uiState.isShowingListPage,
                onBackButtonClick = { viewModel.navigateToListPage() },
            )
        }
    ) { innerPadding ->
        if (contentType == CookBookContentType.ListAndDetail){
            CookBookListAndDetails(
                recipes = uiState.recipesList,
                onClick = {
                    viewModel.updateCurrentRecipe(it)
                },
                selectedRecipe = uiState.currentRecipe,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding))
        } else {
            if (uiState.isShowingListPage) {
                CookBookList(
                    recipes = uiState.recipesList,
                    onClick = {
                        viewModel.updateCurrentRecipe(it)
                        viewModel.navigateToDetailPage()
                    },
                    modifier = Modifier.padding((innerPadding))
                )
            } else {
                CookBookDetail(
                    selectedRecipe = uiState.currentRecipe,
                    modifier = Modifier.padding((innerPadding)),
                    onBackPressed = {
                        viewModel.navigateToListPage()
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CookBookAppBar(
    onBackButtonClick: () -> Unit,
    isShowingListPage: Boolean,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text =
            if (!isShowingListPage) {
                stringResource(R.string.detail_fragment_label)
            } else {
                stringResource(R.string.list_fragment_label)
            },
                style = MaterialTheme.typography.displayLarge
            )
        },
        navigationIcon = if (!isShowingListPage) {
            {
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        } else {
            { Box() {} }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CookBookListItem(
    recipe: CookBook,
    onItemClick: (CookBook) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(recipe) }
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
        ) {
            CookBookListImageItem(
                recipe = recipe,
            )
            Box(
                modifier = modifier
                    .background(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5F))
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
            ) {
                Text(
                    text =  stringResource(recipe.titleResourceId),
                    textAlign = TextAlign.Right,
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
private fun CookBookListImageItem(
    recipe: CookBook,
    modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(recipe.imageResourceId),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun CookBookList(
    recipes: List<CookBook>,
    onClick: (CookBook) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier
    ) {
        items(recipes, key = { recipe -> recipe.id }) { recipe ->
            CookBookListItem(
                recipe = recipe,
                onItemClick = onClick
            )
        }
    }
}

@Composable
fun CookBookTextDetail(
    textTitle: String,
    textIngredients: String,
    textRecipeDetails:String,
    modifier: Modifier = Modifier
){
    Card(
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        modifier = modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Text(
            text = textTitle,
            style = MaterialTheme.typography.displaySmall.copy(
                shadow = Shadow(
                    offset = Offset(10f, 10f),
                    blurRadius = 10f
                )
            ),
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )
        Spacer(Modifier.padding(4.dp))
        Row(modifier = modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(R.string.servings),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSecondary,
            )
            Spacer(Modifier.padding(horizontal = 50.dp))
            Text(
                text = stringResource(R.string.time),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSecondary,
            )
        }
        Spacer(Modifier.padding(16.dp))
        Text(
            text = stringResource(R.string.ingredients_name),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            textDecoration = TextDecoration.Underline,
            modifier = modifier.padding(start = 12.dp)
        )
        Spacer(Modifier.padding(8.dp))
        Text(
            text = textIngredients,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = modifier.padding(start = 20.dp)
        )
        Spacer(Modifier.padding(16.dp))
        Text(
            text = stringResource(R.string.steps),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            textDecoration = TextDecoration.Underline,
            modifier = modifier.padding(start = 12.dp)
        )
        Spacer(Modifier.padding(8.dp))
        Text(
            text = textRecipeDetails,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = modifier.padding(start = 20.dp, end = 16.dp, bottom = 12.dp)
        )
    }
}


@Composable
private fun CookBookDetail(
    selectedRecipe: CookBook,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(state = scrollState)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Box {
            Image(
                painter = painterResource(selectedRecipe.imageBanner),
                contentDescription = null,
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.FillWidth
            )
        }
        Spacer(Modifier.padding(8.dp))
        CookBookTextDetail(
            textTitle = stringResource(selectedRecipe.titleResourceId),
            textIngredients = stringResource(selectedRecipe.ingredients),
            textRecipeDetails = stringResource(selectedRecipe.recipeDetails))
    }
}


@Composable
fun CookBookListAndDetails(
    recipes: List<CookBook>,
    onClick: (CookBook) -> Unit,
    selectedRecipe: CookBook,
    modifier: Modifier = Modifier
){
    Row(modifier = Modifier.fillMaxSize()) {
        CookBookList(
            recipes = recipes,
            onClick = onClick,
            modifier = modifier.weight(2f),
        )

        val activity = LocalContext.current as Activity
        CookBookDetail(
            selectedRecipe = selectedRecipe,
            onBackPressed = { activity.finish() },
            modifier = modifier.weight(3f))
    }
}

@Preview()
@Composable
fun CookBookListItemPreview() {
    CookBookTheme {
        CookBookListItem(
            recipe = LocalCookBookData.defaultRecipe,
            onItemClick = {}
        )
    }
}

@Preview()
@Composable
fun CookBookListPreview() {
    CookBookTheme() {
        Surface {
            CookBookList(
                recipes = LocalCookBookData.getRecipesData(),
                onClick = {}
            )
        }
    }
}
