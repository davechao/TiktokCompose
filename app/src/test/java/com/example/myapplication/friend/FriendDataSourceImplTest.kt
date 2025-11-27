package com.example.myapplication.friend

import com.example.myapplication.api.ApiService
import com.example.myapplication.datasource.FriendDataSourceImpl
import com.example.myapplication.model.FriendItem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class FriendDataSourceImplTest {

    private val api = mockk<ApiService>()
    private lateinit var dataSource: FriendDataSourceImpl

    @Before
    fun setup() {
        dataSource = FriendDataSourceImpl(api)
    }

    @Test
    fun `getFriends should return list from api`() = runTest {
        val mockList = listOf(
            FriendItem(
                id = 1,
                login = "dave",
                name = "Dave",
                avatarUrl = "",
                htmlUrl = "",
                blog = "",
                location = "Taiwan",
                email = "dave@gamil.com",
                bio = "",
                followers = 100,
                following = 200,
            )
        )

        // Given
        coEvery { api.getUsers() } returns mockList

        // When
        val result = dataSource.getUsers()

        // Then
        assertEquals(mockList, result)
        coVerify { api.getUsers() }
    }
}