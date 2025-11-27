package com.example.myapplication.friend

import com.example.myapplication.model.FriendItem
import com.example.myapplication.repository.FriendRepository
import com.example.myapplication.usecase.FetchFriendUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FetchFriendUseCaseTest {

    private val repository = mockk<FriendRepository>()
    private lateinit var useCase: FetchFriendUseCase

    @Before
    fun setup() {
        useCase = FetchFriendUseCase(repository)
    }

    @Test
    fun `invoke should return friend from repository`() = runTest {
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
        coEvery { repository.fetchFriend(33) } returns mockItem

        // When
        val result = useCase(33)

        // Then
        assertEquals(mockItem, result)
        coVerify { repository.fetchFriend(33) }
    }
}