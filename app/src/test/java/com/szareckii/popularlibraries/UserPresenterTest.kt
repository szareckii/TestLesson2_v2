package com.szareckii.popularlibraries

import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.presenter.UserPresenter
import com.szareckii.popularlibraries.mvp.view.UserView
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router

class UserPresenterTest {

    private lateinit var presenter: UserPresenter

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var user: GithubUser

    @Mock
    private lateinit var userViewState: UserView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = UserPresenter(router, user)
        presenter.attachView(userViewState)
    }

    @Test
    fun backClick_routerExit_Test() {
        presenter.backClick()
        verify(router, times(1)).exit()
    }

    @Test
    fun backClick_returnTrue_Test() {
        assertTrue(presenter.backClick())
    }

    @Test
    fun onFirstViewAttach_Test() {
        val login = "login1"
        presenter.onFirstViewAttach()
        userViewState.setUserLogin(login)
        verify(userViewState, times(1)).setUserLogin(login)
    }
}