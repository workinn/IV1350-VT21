package se.kth.iv1350.danielhenning.integration;

import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;

public class MemberHandler {

  public MemberHandler() {

  }

  public ClubMemberDTO getMember(String customerID){
    /*
    Gets Club Member from External Member System
    */

    ClubMemberDTO member;

    if(customerID == "Daniel") {
      member = new ClubMemberDTO("Daniel", "1337");
    } else {
      member = null;
    }

    return member;
  }
}
