package se.kth.iv1350.danielhenning.dto;

import  org.testng.annotations.Test;

//import static org.junit.jupiter.api.Assertions.*;

/**
 * ClubMemberDTOTest
 */
public class ClubMemberDTOTest {
  private ClubMemberDTO member;

  @BeforeEach
  public void setUp() {
    member = new ClubMemberDTO("Daniel", "1337");
  }

  @AfterEach
  public void tearDown() {
    member = null;
  }

  @Test
  public void testName() {
    String expString = "Daniel";
    String result = member.getName();
    assertEquals(expString, result, "The name is not equal");
  }
  
}