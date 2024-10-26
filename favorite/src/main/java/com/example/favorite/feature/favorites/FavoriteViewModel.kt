import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.entity.Favorite
import com.example.core.domain.usecase.GetFavoritesUseCase

class FavoriteViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    val favoriteList: LiveData<List<Favorite>> = getFavoritesUseCase.execute().asLiveData()
}