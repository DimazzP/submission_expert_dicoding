import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.core.data.local.datastore.SettingPreferences
import com.example.submission_navigation.feature.main.MainViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    private lateinit var pref: SettingPreferences

    @Mock
    private lateinit var themeSettingsObserver: Observer<Boolean>

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel(pref)
    }

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset main dispatcher after the test
        mainThreadSurrogate.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test saveThemeSetting`() = runBlockingTest {
        // Given
        val isDarkModeActive = true

        // When
        mainViewModel.saveThemeSetting(isDarkModeActive)

        // Then
        Mockito.verify(pref).saveThemeSetting(isDarkModeActive)
        Mockito.verifyNoMoreInteractions(pref)
    }
}
