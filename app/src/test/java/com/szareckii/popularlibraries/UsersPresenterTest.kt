package com.szareckii.popularlibraries

import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.model.repo.GithubUsersRepo
import com.szareckii.popularlibraries.mvp.presenter.UsersPresenter
import com.szareckii.popularlibraries.mvp.view.UsersView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router

class UsersPresenterTest {

    private lateinit var presenter: UsersPresenter

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var usersRepo: GithubUsersRepo

    @Mock
    private lateinit var userListPresenter: UsersPresenter.UserListPresenter

    @Mock
    private lateinit var usersViewState: UsersView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = UsersPresenter(router, usersRepo)
        presenter.attachView(usersViewState)
//        userListPresenter = presenter.userListPresenter
    }

    @Test
    fun loadData_usersViewState_Test() {
        presenter.loadData()
        verify(usersViewState, times(2)).updateUsersList()
    }

    @Test
    fun loadData_updateUsersList1_Test() {
        val usersRepositories = listOf(
            GithubUser("login1"),
            GithubUser("login2"),
            GithubUser("login3"),
        )
        val usersFromPresenter = mutableListOf<GithubUser>()
        `when`(usersRepo.getUsers()).thenReturn(usersRepositories)
        `when`(userListPresenter.users).thenReturn(usersFromPresenter)
        presenter.loadData()
        verify(userListPresenter).users.clear()
    }

    @Test
    fun backClick_Test() {
        presenter.backClick()
        verify(router, times(1)).exit()
    }

}

