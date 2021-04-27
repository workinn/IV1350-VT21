package se.kth.iv1350.danielhenning.dto;

public class ClubMemberDTO {

  private String name;
  private String memberID;

  public ClubMemberDTO(String name, String memberID) {
    this.name = name;
    this.memberID = memberID;
  }

  public String getName() {
    return name;
  }

  public String getMemberID() {
    return memberID;
  }
}
