package se.kth.iv1350.danielhenning.dto;

/**
 * The ClubMemberDTO class represents a Data Transfer Object
 * of a person who is member of the stores club. Being 
 * member of the stores club might give you extra discounts.
 */
public final class ClubMemberDTO {

  private String name;
  private String memberID;

  /**
   * Creates a new instance of the class ClubMemberDTO
   * @param name is the name of the Club Member
   * @param memberID is the ID of the ClubMember
   */
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
