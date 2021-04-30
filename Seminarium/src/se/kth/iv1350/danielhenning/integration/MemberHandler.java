package se.kth.iv1350.danielhenning.integration;

import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;
/**
 * The MemberHandler class represents the Club Member System.
 * This system may be an External System in the future. In
 * that case, this class will represent the interface between
 * the System Under Development and the External System.
 * A customer may be a Club Member which in turn may make
 * the customer eligible for more discounts
 */
public class MemberHandler {

  /**
   * Create a new instance of the class MemberHandler
   */
  public MemberHandler() {
  }

  /**
   * The method getMember retrieves a Club Member from a database
   * of all Club Members. Right now the method contains hard coded
   * Club Members instead of a database
   * @param customerID is an identifier of a customer. May be a name or a social security number
   * @return a ClubMemberDTO of the retrieved Club Member. May return null if a Club Member is not found
   */
  public ClubMemberDTO getMember(String customerID){

    ClubMemberDTO member;

    if(customerID == "Daniel") {
      member = new ClubMemberDTO("Daniel", "1337");
    } else {
      member = null;
    }
    return member;
  }
}
