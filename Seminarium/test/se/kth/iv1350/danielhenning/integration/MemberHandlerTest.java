package se.kth.iv1350.danielhenning.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;

public class MemberHandlerTest {

  private MemberHandler memberHandler;

  @BeforeEach
  public void startUp() {
    System.out.println("Start Up!");
    memberHandler = new MemberHandler();
  }

  @AfterEach
  public void tearDown() {
    System.out.println("Tear Down!");
    memberHandler = null;
  }

  @Test
  public void getMemberWithEmptyString() {

    System.out.println("Test!");

    ClubMemberDTO expectedMember = new ClubMemberDTO("Daniel", "1337");
    ClubMemberDTO actualMember = memberHandler.getMember("1337");

    String expected = expectedMember.getMemberID();
    String actual = actualMember.getMemberID();

    assertEquals(expected, actual);
  }
  
}
