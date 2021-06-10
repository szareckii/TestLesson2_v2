package com.szareckii.popularlibraries

import com.szareckii.popularlibraries.mvp.presenter.MainPresenter
import com.szareckii.popularlibraries.mvp.view.MainView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router

class MainPresenterTest {

    private lateinit var presenter: MainPresenter

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var mainViewState: MainView

//    val response1 = mock(Screens.UsersScreen::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(router)
        presenter.attachView(mainViewState)
    }

    @Test
    fun backClick_routerExit_Test() {
        presenter.backClick()
        verify(router, times(1)).exit()
    }

//    @Test
//    fun onFirstViewAttach_Test() {
//        presenter.onFirstViewAttach()
//        verify(router, times(1)).replaceScreen(response1)
//    }
}