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
    System.out.println("Start up!");
    memberHandler = new MemberHandler();
  }

  @AfterEach
  public void tearDown() {
    System.out.println("Tear down!");
    memberHandler = null;
  }

  @Test
  public void getMemberWithEmptyString() {
    System.out.println("getMemberWithEmptyString!");

    ClubMemberDTO expected = null;
    ClubMemberDTO actual = memberHandler.getMember("");

    assertEquals(expected, actual);
  }

  @Test
  public void getMemberWithNotExistingString() {
    System.out.println("getMemberWithNotExistingString!");

    ClubMemberDTO expected = null;
    ClubMemberDTO actual = memberHandler.getMember("100");

    assertEquals(expected, actual);
  }

  @Test
  public void getMember1337() {
    System.out.println("getMember1337!");

    ClubMemberDTO expectedMember = new ClubMemberDTO("Daniel", "1337");
    ClubMemberDTO actualMember = memberHandler.getMember("1337");

    String expected = expectedMember.getMemberID();
    String actual = actualMember.getMemberID();

    assertEquals(expected, actual);

    expected = expectedMember.getName();
    actual = actualMember.getName();

    assertEquals(expected, actual);
  }

  @Test
  public void getMember1() {
    System.out.println("getMember1!");

    ClubMemberDTO expectedMember = new ClubMemberDTO("Henning", "1");
    ClubMemberDTO actualMember = memberHandler.getMember("1");

    String expected = expectedMember.getMemberID();
    String actual = actualMember.getMemberID();

    assertEquals(expected, actual);
    
    expected = expectedMember.getName();
    actual = actualMember.getName();

    assertEquals(expected, actual);
  }
  
}
