package se.kth.iv1350.danielhenning.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;

public class MemberHandlerTest {

  private MemberHandler memberHandler;

  @BeforeEach
  public void startUp() {
    memberHandler = new MemberHandler();
  }

  @AfterEach
  public void tearDown() {
    memberHandler = null;
  }

  @Test
  public void getMemberWithEmptyString() {
    ClubMemberDTO expected = null;
    ClubMemberDTO actual = memberHandler.getMember("");

    assertEquals(expected, actual, "The given ClubMemberDTO is not what was expected: ");
  }

  @Test
  public void getMemberWithNotExistingString() {
    ClubMemberDTO expected = null;
    ClubMemberDTO actual = memberHandler.getMember("100");

    assertEquals(expected, actual, "The given ClubMemberDTO is not what was expected: ");
  }

  @Test
  public void getMember1337() {
    ClubMemberDTO expectedMember = new ClubMemberDTO("Daniel", "1337");
    ClubMemberDTO actualMember = memberHandler.getMember("1337");

    String expected = expectedMember.getMemberID();
    String actual = actualMember.getMemberID();

    assertEquals(expected, actual, "The given MemberID is not what was expected: ");

    expected = expectedMember.getName();
    actual = actualMember.getName();

    assertEquals(expected, actual, "The given Name is not what was expected: ");
  }

  @Test
  public void getMember1() {
    ClubMemberDTO expectedMember = new ClubMemberDTO("Henning", "1");
    ClubMemberDTO actualMember = memberHandler.getMember("1");

    String expected = expectedMember.getMemberID();
    String actual = actualMember.getMemberID();

    assertEquals(expected, actual, "The given MemberID is not what was expected: ");
    
    expected = expectedMember.getName();
    actual = actualMember.getName();

    assertEquals(expected, actual, "The given Name is not what was expected: ");
  }
}
