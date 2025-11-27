package com.example.myapplication.friend

import com.example.myapplication.datasource.FriendDataSource
import com.example.myapplication.model.FriendItem
import com.example.myapplication.repository.FriendRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FriendRepositoryImplTest {

    private val dataSource = mockk<FriendDataSource>()
    private lateinit var repository: FriendRepositoryImpl

    @Before
    fun setup() {
        repository = FriendRepositoryImpl(dataSource)
    }

    @Test
    fun `fetchFriends should call dataSource getUsers`() = runTest {
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
        coEvery { dataSource.getUsers() } returns mockList

        // When
        val result = repository.fetchFriends()

        // Then
        assertEquals(mockList, result)
        coVerify { dataSource.getUsers() }
    }

    @Test
    fun `fetchFriend should call dataSource getUser`() = runTest {
        val mockItem = FriendItem(
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

        // Given
        coEvery { dataSource.getUser(5) } returns mockItem

        // When
        val result = repository.fetchFriend(5)

        // Then
        assertEquals(mockItem, result)
        coVerify { dataSource.getUser(5) }
    }
}