package com.example.healthtracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthtracker.ui.login.LoginFragment
import com.example.healthtracker.ui.main.MainActivity
import com.facebook.CallbackManager
import com.facebook.login.LoginResult
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)

class LoginFragmentTest {

    @Mock
    private lateinit var mockFragment: LoginFragment

    @Mock
    private lateinit var mockCallbackManager: CallbackManager

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun onCreateView_shouldInitializeViews() {
        // Arrange
        val mockInflater = mock(LayoutInflater::class.java)
        val mockContainer = mock(ViewGroup::class.java)
        val mockSavedInstanceState = mock(Bundle::class.java)
        val mockView = mock(View::class.java)
        `when`(mockInflater.inflate(anyInt(), any(), anyBoolean())).thenReturn(mockView)

        // Act
        val result = mockFragment.onCreateView(mockInflater, mockContainer, mockSavedInstanceState)

        // Assert
        assertEquals(mockView, result)
        // Add more assertions here to verify that the views are correctly initialized in the onCreateView method
    }

    @Test
    fun onSuccess_shouldLaunchMainActivityWithExpectedExtras() {
        // Arrange
        val mockLoginResult = mock(LoginResult::class.java)
        val mockIntent = mock(Intent::class.java)
        val mockContext = mock(Context::class.java)
        `when`(mockFragment.requireContext()).thenReturn(mockContext)
        `when`(mockContext.startActivity(mockIntent)).thenReturn(null)

        // Act
        mockFragment.getFacebookData(mockLoginResult)

        // Assert
        val argumentCaptor = ArgumentCaptor.forClass(Intent::class.java)
        verify(mockContext).startActivity(argumentCaptor.capture())
        val capturedIntent = argumentCaptor.value
        assertEquals(MainActivity::class.java, capturedIntent.component?.className)
        assertEquals("expected_email@example.com", capturedIntent.getStringExtra("email"))
        assertEquals("expected_name", capturedIntent.getStringExtra("name"))
    }
}
