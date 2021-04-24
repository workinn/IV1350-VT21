package integration;

import DTO.ClubMemberDTO;

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
