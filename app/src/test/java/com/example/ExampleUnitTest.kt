package com.example

import com.example.data.model.AppLanguage
import com.example.data.repository.ProgramRepository
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @Test
  fun testProgramRepositoryDaysAndDelegatesCount() {
    assertTrue("Program days should not be empty", ProgramRepository.programDays.isNotEmpty())
    assertEquals("Delegation should contain exactly 39 participants", 39, ProgramRepository.participantsList.size)
  }

  @Test
  fun testBilingualProgramDay() {
    val day = ProgramRepository.programDays.first()
    assertFalse("Arabic title must not be blank", day.localizedTitle(true).isBlank())
    assertFalse("French title must not be blank", day.localizedTitle(false).isBlank())
    assertNotEquals("Arabic and French titles should differ", day.localizedTitle(true), day.localizedTitle(false))
  }

  @Test
  fun testMapLandmarksIntegrity() {
    val landmarks = ProgramRepository.mapLandmarks
    assertTrue("Map should have landmarks for Niger delegation", landmarks.size >= 8)
    landmarks.forEach { landmark ->
      assertTrue("Latitude should be within Ghardaïa region", landmark.latitude in 31.0..34.0)
      assertTrue("Longitude should be within Ghardaïa region", landmark.longitude in 2.0..5.0)
      assertFalse("Landmark name (AR) should not be blank", landmark.localizedName(true).isBlank())
      assertFalse("Landmark name (FR) should not be blank", landmark.localizedName(false).isBlank())
    }
  }

  @Test
  fun testAppLanguageEnum() {
    val ar = AppLanguage.ARABIC
    val fr = AppLanguage.FRENCH
    assertTrue(ar.isArabic)
    assertFalse(fr.isArabic)
  }

  @Test
  fun testParticipantsPrivacy() {
    val participants = ProgramRepository.participantsList
    assertEquals(39, participants.size)
    participants.forEach { p ->
      assertTrue("Full name should not be blank", p.fullName.isNotBlank())
      assertTrue("Specialty should not be blank", p.specialty.isNotBlank())
      assertTrue("Gender should be M or F", p.gender == "M" || p.gender == "F")
    }
  }
}
